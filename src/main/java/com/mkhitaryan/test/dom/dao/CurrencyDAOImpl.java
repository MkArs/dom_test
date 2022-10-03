package com.mkhitaryan.test.dom.dao;

import com.mkhitaryan.test.dom.entity.Currency;
import com.mkhitaryan.test.dom.parser.DomParser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyDAOImpl implements CurrencyDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void updateCurrencies() {
        String url = "https://www.cbr.ru/scripts/XML_daily.asp";
        //DOM парсер
        DomParser parser = new DomParser();
        List<Currency> newCurrencies = parser.getCurrencyList(url);

        Session session = sessionFactory.getCurrentSession();

        for (Currency currency :
                newCurrencies) {
            session.saveOrUpdate(currency);
        }

        parser.clearCurrencyList();
    }

    @Override
    public List<Currency> fillCurrencyDropdown() {
        Session session = sessionFactory.getCurrentSession();
        List<Currency> allCurrencies = session.createQuery("from Currency", Currency.class).getResultList();
        return allCurrencies;
    }
}

