package com.nihal.trade.screener.network.http;

import java.util.List;

import com.nihal.trade.screener.data.DataPoint;

public interface DataFetcher {
    List<DataPoint> fetchData();
}
