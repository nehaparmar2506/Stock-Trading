
package test.impl;


import com.hackerrank.stocktrades.exception.TradeNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import com.hackerrank.stocktrades.service.impl.StockTradeServiceImpl;
import test.util.TestUtil;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
class StockTradeServiceImplTest {

    public static final String SELL = "sell";
    private static final String BUY = "buy";
    @Mock
    private StockTradeRepository stockTradeRepository;

    @InjectMocks
    StockTradeServiceImpl stockTradeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createTrade() {
        StockTrade stockTradeObject = TestUtil.getReqObject();
        StockTrade stockTrade = TestUtil.getStockTrade(1, SELL, 9);
        Mockito.when(stockTradeRepository.save(any())).thenReturn(stockTrade);
        StockTrade stockTradeResult = stockTradeService.createTrade(stockTradeObject);
        assertEquals(stockTradeResult.getId(), stockTrade.getId());
    }


    @Test
    void getTrades() {
        List<StockTrade> list = createResultList();
        Mockito.when(stockTradeRepository.findAll(any(Specification.class), any(Sort.class))).thenReturn(list);
        List<StockTrade> tradeList = stockTradeService.getTrades(null, null);
        assertEquals(tradeList.size(), 2);
    }

    private List<StockTrade> createResultList() {
        List<StockTrade> list = new ArrayList<>();
        StockTrade stockTrade = TestUtil.getStockTrade(1, SELL, 18);
        list.add(stockTrade);
        stockTrade = TestUtil.getStockTrade(2, BUY, 9);
        list.add(stockTrade);
        return list;
    }

    @Test
    void getTradeById_found() {
        StockTrade stockTrade = TestUtil.getStockTrade(1, SELL, 9);
        Mockito.when(stockTradeRepository.findById(any())).thenReturn(Optional.of(stockTrade));
        stockTrade = stockTradeService.getTrade(1);
        assertEquals(stockTrade.getId(), 1);
    }

    @Test
    void getTradeById_notFound() {
        Mockito.when(stockTradeRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(TradeNotFoundException.class, () ->
                        stockTradeService.getTrade(1),
                "success");
        assertTrue(exception.getMessage().contains("1"));

    }


}
