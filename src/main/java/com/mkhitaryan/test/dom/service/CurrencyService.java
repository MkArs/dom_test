package com.mkhitaryan.test.dom.service;

import com.mkhitaryan.test.dom.entity.Currency;

import java.util.List;

public interface CurrencyService {
    public void updateCurrencies();
    public List<Currency> fillCurrencyDropdown();
}
