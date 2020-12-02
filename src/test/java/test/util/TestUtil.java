package test.util;

import com.hackerrank.stocktrades.model.StockTrade;


public class TestUtil {
    public static final String SELL = "sell";

    public static StockTrade getStockTrade(int id, String type, int userId) {
        StockTrade stockTrade = new StockTrade();
        stockTrade.setId(id);
        stockTrade.setType(type);
        stockTrade.setTimestamp(1831522701000L);
        stockTrade.setSymbol("XYZ");
        stockTrade.setShares(10);
        stockTrade.setPrice(123);
        stockTrade.setUserId(userId);
        return stockTrade;
    }

    public static  StockTrade getStockTrade(int id, String type, int userId, int share) {
        StockTrade stockTrade = new StockTrade();
        stockTrade.setId(id);
        stockTrade.setType(type);
        stockTrade.setTimestamp(1831522701000L);
        stockTrade.setSymbol("XYZ");
        stockTrade.setShares(share);
        stockTrade.setPrice(123);
        stockTrade.setUserId(userId);
        return stockTrade;
    }

    public static StockTrade getReqObject() {
        StockTrade stockTrade = new StockTrade();
        stockTrade.setType(SELL);
        stockTrade.setTimestamp(1831522701000L);
        stockTrade.setSymbol("XYZ");
        stockTrade.setShares(10);
        stockTrade.setPrice(123);
        stockTrade.setUserId(9);
        return stockTrade;
    }

}
