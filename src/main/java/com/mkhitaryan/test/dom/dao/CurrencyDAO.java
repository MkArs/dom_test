package com.mkhitaryan.test.dom.dao;

import com.mkhitaryan.test.dom.entity.Currency;

import java.util.List;

public interface CurrencyDAO {
    public void updateCurrencies();
    public List<Currency> fillCurrencyDropdown();
}
