package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.service.StockTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Accepts all the requests related to resource StockTrade.
 */
@RestController
public class StockTradeRestController {

    @Autowired
    private StockTradeService stockTradeService;

    /**
     * Create the trade of the request if valid.
     * @param stockTrade StockTrade    trade Request
     * @return StockTrade              trade Response with ID
     */
    @PostMapping(path = "/trades",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockTrade> createTrade(@Valid @RequestBody StockTrade stockTrade) {
        final StockTrade response = stockTradeService.createTrade(stockTrade);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * Fetches all the trades available based on the passed parameters.
     * @param type   String  Optional filter to get the trades based on type.
     * @param userId int     Optional filter to get the trades based on userId.
     * @return tradeList List    List of All the StockTrades after filters applied.
     */
    @GetMapping(path = "/trades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockTrade>> getTrades(@RequestParam(required = false) String type,
                                                      @RequestParam(required = false) Integer userId) {
        final List<StockTrade> stockList = stockTradeService.getTrades(type, userId);
        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    /**
     * Get Stock Trades based on Trade identifier.
     * @param id Integer   Trade identifier.
     * @return Stock trade.
     */
    @RequestMapping(path = "/trades/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.GET})
    public ResponseEntity<StockTrade> getTradeById(@PathVariable Integer id){
        final StockTrade stockTrade = stockTradeService.getTrade(id);
        return new ResponseEntity<>(stockTrade, HttpStatus.OK);
    }

}