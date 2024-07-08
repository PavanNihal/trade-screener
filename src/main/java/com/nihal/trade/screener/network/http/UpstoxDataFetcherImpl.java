package com.nihal.trade.screener.network.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nihal.trade.screener.constants.TimeFrame;
import com.nihal.trade.screener.data.DataPoint;
import com.nihal.trade.screener.network.http.exceptions.FetchFailedException;

public class UpstoxDataFetcherImpl implements DataFetcher{

    @Override
    public List<DataPoint> fetchData(String symbol, Instant startDate, Instant endDate, TimeFrame timeFrame) throws FetchFailedException {

        URI uri;
        try {
            uri = constructURI(symbol, startDate, endDate, timeFrame);
        }
        catch(Exception ex) {
            throw new FetchFailedException(ex.getMessage());
        }
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // int statusCode = response.statusCode();            
        }        
        catch (IOException ex) {
            throw new FetchFailedException(ex.getMessage());
        }
        catch (InterruptedException ex) {
            throw new FetchFailedException(ex.getMessage());
        }    
        
        List<DataPoint> data = new ArrayList<>();

        if(response != null) {
            String body = response.body();
            System.out.println(body);
            JsonObject responseJson = new Gson().fromJson(body, JsonObject.class);

            if(responseJson.get("status").getAsString().equals("success")) {
                JsonObject arr = responseJson.getAsJsonObject("data");
                JsonArray candles = arr.getAsJsonArray("candles");
                for(var candle: candles) {                    
                    JsonArray candleData = candle.getAsJsonArray();

                    String dateTimeString = candleData.get(0).getAsString();
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // Handles timezone offset
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeString, formatter);
                    Instant instant = zonedDateTime.toInstant();

                    DataPoint dataPoint = new DataPoint(instant,
                                         candleData.get(1).getAsDouble(),
                                         candleData.get(2).getAsDouble(), 
                                         candleData.get(3).getAsDouble(), 
                                         candleData.get(4).getAsDouble(), 
                                         candleData.get(5).getAsLong(), 
                                         candleData.get(6).getAsLong());

                    data.add(dataPoint);
                }
            }
            else if(responseJson.get("status").getAsString().equals("error")) {
                System.out.println(body);
            }
        }


        return data;

    }

    private URI constructURI(String symbol, Instant startDate, Instant endDate, TimeFrame timeFrame) throws UnsupportedEncodingException {
        String baseUrl = "https://api.upstox.com/v2/historical-candle/";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.from(ZoneOffset.UTC));

        String encodedSymbol = URLEncoder.encode(symbol, StandardCharsets.UTF_8.toString());
        String uri = baseUrl + encodedSymbol + "/" + timeFrame.toString() + "/" + formatter.format(endDate) + "/" + formatter.format(startDate);

        return URI.create(uri);
    }
    
}
