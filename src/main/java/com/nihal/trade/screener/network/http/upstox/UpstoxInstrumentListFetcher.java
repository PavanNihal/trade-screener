package com.nihal.trade.screener.network.http.upstox;

import java.util.*;
import java.net.URL;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nihal.trade.screener.data.Instrument;
import com.nihal.trade.screener.data.Instrument.Exchange;
import com.nihal.trade.screener.data.Instrument.Segment;
import com.nihal.trade.screener.network.http.InstrumentListFetcher;
import com.nihal.trade.screener.repository.InstrumentRepository;

public class UpstoxInstrumentListFetcher extends InstrumentListFetcher {
    
    public UpstoxInstrumentListFetcher(InstrumentRepository instrumentRepository) {
        super(instrumentRepository);        
    }

    private static final String JSON_FILE_LINK = "https://assets.upstox.com/market-quote/instruments/exchange/NSE.json.gz";

    @Override
    public void fetchInstruments() {
        
        try {
            InputStream inputStream = new URL(JSON_FILE_LINK).openStream();
            try(GZIPInputStream inflatedStream = new GZIPInputStream(inputStream)) {
                byte[] content = inflatedStream.readAllBytes();
                String jsonString = new String(content);
                JsonArray jsonArray = new Gson().fromJson(jsonString, JsonArray.class);

                List<Instrument> instrumentList = new ArrayList<>();
                for(JsonElement element: jsonArray) {
                    JsonObject instrumentJson = element.getAsJsonObject();
                    Segment segment = Segment.valueOf(instrumentJson.get("segment").getAsString());

                    if(segment != Segment.NSE_EQ) {
                        continue;
                    }

                    String name = instrumentJson.get("name").getAsString();
                    Exchange exchange = Exchange.valueOf(instrumentJson.get("exchange").getAsString());
                    String isin = instrumentJson.get("isin").getAsString();
                    String instrumentType = instrumentJson.get("instrument_type").getAsString();
                    String instrumentKey = instrumentJson.get("instrument_key").getAsString();
                    int lotSize = instrumentJson.get("lot_size").getAsInt();
                    int freezeQuantity = instrumentJson.get("freeze_quantity").getAsInt();
                    String exchangeToken = instrumentJson.get("exchange_token").getAsString();
                    float tickSize = instrumentJson.get("tick_size").getAsFloat();
                    String tradingSymbol = instrumentJson.get("trading_symbol").getAsString();
                    String shortName = instrumentJson.get("short_name") != null ? instrumentJson.get("short_name").getAsString() : null;
                    String securityType = instrumentJson.get("security_type").getAsString();                        

                    instrumentList.add(new Instrument(segment, name, exchange, isin, instrumentType, instrumentKey, 
                                lotSize, freezeQuantity, exchangeToken, tickSize, tradingSymbol, shortName, securityType));
                }

            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            
        }
    }
}
