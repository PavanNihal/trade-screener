package com.nihal.trade.screener.constants;

public enum TimeFrame {
    MINUTE("1minute"),
    HALF_HOUR("30minute"),
    DAY("day"),
    WEEK("week"),
    MONTH("month");

    private String value;

    TimeFrame(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
