package com.skypro.SocksStock.service;

import com.skypro.SocksStock.dto.SocksDto;

public interface SocksStockService {

    int getQuantityOfSocksBy(String color, Integer cottonPart);

    void addSocks(SocksDto socksStockDto);

    void removeSocks(SocksDto socksStockDto);
}
