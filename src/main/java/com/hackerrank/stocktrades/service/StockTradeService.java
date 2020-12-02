package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.model.StockTrade;

import java.util.List;

/**
 * Declares service methods for all the actions related to Stock trade resource.
 */
public interface StockTradeService {

    StockTrade createTrade(StockTrade stockTrade) ;

    List<StockTrade> getTrades(String type, Integer userId) ;

    StockTrade getTrade(Integer id);
}
