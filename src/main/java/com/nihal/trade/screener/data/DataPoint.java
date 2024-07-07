package com.nihal.trade.screener.data;

import java.time.Instant;

public class DataPoint {

    private final double high;
    private final double low;
    private final double start;
    private final double end;
    private final Instant time;
    private final long volume;
    private final long openInterest;
    
    public DataPoint(double high, double low, double start, double end, Instant time, long volume, long openInterest) {
        this.high = high;
        this.low = low;
        this.start = start;
        this.end = end;
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

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
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

    
}
