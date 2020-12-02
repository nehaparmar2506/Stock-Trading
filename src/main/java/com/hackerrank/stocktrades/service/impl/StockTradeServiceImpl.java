package com.hackerrank.stocktrades.service.impl;

import com.hackerrank.stocktrades.exception.TradeNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import com.hackerrank.stocktrades.service.StockTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hackerrank.stocktrades.repository.StockTradeSpecification.typeEquals;
import static com.hackerrank.stocktrades.repository.StockTradeSpecification.userIdEquals;
import static com.hackerrank.stocktrades.util.Constants.STOCKTRADE_ID;
import static com.hackerrank.stocktrades.util.Constants.TRADE_WITH_ID_D_NOT_FOUND;

/**
 * Defines the service methods for Stock trade related requests.
 */
@Service
public class StockTradeServiceImpl implements StockTradeService {

    @Autowired
    private StockTradeRepository stockTradeRepository;

    /**
     * creates a given StockTrade entity in DB.
     * @param stockTrade Stock trade object to save.
     * @return Saved stock trade with id.
     */
    @Override
    public StockTrade createTrade(StockTrade stockTrade) {
        return stockTradeRepository.save(stockTrade);
    }

    /**
     * Get all the stock trades based on parameters.
     * @param type   String  Trade type (Optional).
     * @param userId Integer  user id (Optional).
     * @return tradeList List  List of filtered Stock Trades.
     */
    @Override
    public  List<StockTrade>  getTrades(String type, Integer userId)  {
        Specification<StockTrade> filterSpecification = this.getStockTradeFilterSpecification(type, userId);
        return stockTradeRepository
                .findAll(filterSpecification,
                 Sort.by(Sort.Direction.ASC, STOCKTRADE_ID));
    }

    private Specification<StockTrade> getStockTradeFilterSpecification(String type, Integer userId) {
        return Specification.where(type != null ? typeEquals(type) : null)
                .and(userId != null ? userIdEquals(userId) : null);
    }

    /**
     * Get the trade by id, throw custom exception if not found.
     * @param id    Integer     Trade id.
     * @return StockTrade  Stock Trade object.
     */
    @Override
    public StockTrade getTrade(Integer id){
       return stockTradeRepository
               .findById(id)
               .orElseThrow( () -> new TradeNotFoundException(String.format(TRADE_WITH_ID_D_NOT_FOUND, id)));

    }
}


