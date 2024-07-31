package com.nihal.trade.screener.indicators;

import java.util.ArrayList;
import java.util.List;

import com.nihal.trade.screener.data.Candle;
import com.nihal.trade.screener.data.Point;

public class SimpleMovingAverage {

    public enum Source {
        OPEN,
        HIGH,
        LOW,
        CLOSE
    }

    public static List<Point> calculate(List<Candle> dataPoints, int windowSize, Source source) {
        if (windowSize > dataPoints.size()) {
            throw new IllegalArgumentException("Window size cannot be greater than data points size");
        }

        List<Point> smaList = new ArrayList<>();
        double sum = 0.0;

        for (int i = 0; i < dataPoints.size(); i++) {
            Candle candle = dataPoints.get(i);
            double point = mapData(candle, source);
            // Handle initial window
            if (i < windowSize) {
                sum += point;
                if (i == windowSize - 1) {
                    smaList.add(new Point(candle.getTime(), sum / windowSize));
                }
                continue;
            }

            // Update sum for sliding window
            sum -= mapData(dataPoints.get(i - windowSize), source);
            sum += mapData(dataPoints.get(i), source);

            // Calculate and add SMA for current point
            smaList.add(new Point(candle.getTime(), sum / windowSize));
        }

        return smaList;
    }

    private static double mapData(Candle candle, Source source) {
        double point;
        switch (source) {
            default:
            case CLOSE:
                point = candle.getClose();
                break;

            case HIGH:
                point = candle.getHigh();
                break;

            case LOW:
                point =candle.getLow();
                break;

            case OPEN:
                point = candle.getOpen();
                break;
        }

        return point;
    }
}
