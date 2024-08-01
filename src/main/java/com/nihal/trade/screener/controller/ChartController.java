package com.nihal.trade.screener.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nihal.trade.screener.constants.TimeFrame;
import com.nihal.trade.screener.data.Candle;
import com.nihal.trade.screener.network.http.DataFetcher;
import com.nihal.trade.screener.network.http.InstrumentListFetcher;

@RestController
public class ChartController {

    DataFetcher dataFetcher;
    InstrumentListFetcher instrumentListFetcher;

    public ChartController(DataFetcher dataFetcher, InstrumentListFetcher instrumentListFetcher) {
        this.dataFetcher = dataFetcher;
        this.instrumentListFetcher = instrumentListFetcher;
    }
    
    @GetMapping("/data/historic")
    public List<Candle> getHistoricData() {
        String symbol = "NSE_EQ|INE009A01021";
        Instant startDate = LocalDate.parse("2024-01-01").atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endDate = LocalDate.parse("2024-05-01").atStartOfDay().toInstant(ZoneOffset.UTC);
        
        var data = dataFetcher.fetchData(symbol, startDate, endDate, TimeFrame.DAY);
        
        return data;
    }


    @GetMapping("data/list")
    public String updateList() {
        instrumentListFetcher.fetchInstruments();
        return "success";
    }
}
