package test.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrades.model.StockTrade;
import test.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StockTradeRestControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;
    @Test
    public void testTradeCreationWithInvalidType() throws Exception {
         StockTrade request  = TestUtil.getStockTrade(1, "invalid", 123) ;
        MvcResult mvcResult = mockMvc.perform(post("/trades")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String error = mvcResult.getResponse().getContentAsString() ;
        Assertions.assertEquals(error, "[\"type: Invalid stock type value\"]");

    }

    @Test
    public void testTradeCreationWithInvalidStock() throws Exception {
        StockTrade request  = TestUtil.getStockTrade(1, "sell", 123, 101) ;
        MvcResult mvcResult = mockMvc.perform(post("/trades")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String error = mvcResult.getResponse().getContentAsString() ;
        Assertions.assertEquals(error, "[\"shares: Invalid no of shares\"]");

    }

}
