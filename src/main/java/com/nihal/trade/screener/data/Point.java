package com.nihal.trade.screener.data;

import java.time.LocalDateTime;

public class Point {
    private final LocalDateTime time;
    private final double value;
    
    public Point(LocalDateTime time, double value) {
        this.time = time;
        this.value = value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    
}
