package com.nihal.trade.screener.network.http;

import java.time.Instant;
import java.util.List;

import com.nihal.trade.screener.constants.TimeFrame;
import com.nihal.trade.screener.data.DataPoint;

public interface DataFetcher {
    List<DataPoint> fetchData(String symbol, Instant startDate, Instant endDate, TimeFrame timeFrame);
}
