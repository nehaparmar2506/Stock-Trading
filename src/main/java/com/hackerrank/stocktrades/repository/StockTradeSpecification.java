package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.data.jpa.domain.Specification;

import static com.hackerrank.stocktrades.util.Constants.STOCKTRADE_TYPE;
import static com.hackerrank.stocktrades.util.Constants.STOCKTRADE_USERID;

/**
 * Manages different implementation of Specification for query clauses for Stock Trade entity.
 */
public interface StockTradeSpecification {

    static Specification<StockTrade> typeEquals(String expression) {
        return (root, query, builder) -> builder.equal(root.get(STOCKTRADE_TYPE), expression);
    }

     static Specification<StockTrade> userIdEquals(Integer expression) {
        return (root, query, builder) -> builder.equal(root.get(STOCKTRADE_USERID), expression);
    }
}
