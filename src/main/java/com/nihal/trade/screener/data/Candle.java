package com.nihal.trade.screener.data;

import java.time.Instant;

public class Candle {

    private final double high;
    private final double low;
    private final double open;
    private final double close;
    private final Instant time;
    private final long volume;
    private final long openInterest;
    
    public Candle(Instant time, double open, double high, double low, double close, long volume, long openInterest) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.time = time;
        this.volume = volume;
        this.openInterest = openInterest;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public Instant getTime() {
        return time;
    }

    public long getVolume() {
        return volume;
    }

    public long getOpenInterest() {
        return openInterest;
    }

    public String toString() {
        return "Open: " + open + " High: " + high + " Low: " + low + " Close:" + close + " Volume: " + volume + " Time: " + time;
    }

    
}
