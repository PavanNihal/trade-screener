package com.nihal.trade.screener.data;

public class Instrument {

    public enum Segment {
        NSE_EQ,
        NSE_INDEX,
        NSE_FO,
        NCD_FO,
        BSE_EQ,
        BSE_INDEX,
        BSE_FO,
        BCD_FO,
        MCX_FO
    }

    public enum Exchange {
        NSE,
        BSE,
        MCX
    }

    private Segment segment;
    private String name;
    private Exchange exchange;
    private String isin;
    private String instrumentType;
    private String key;
    private int lotSize;
    private int freezeQuantity;
    private String exchangeToken;
    private float tickSize;
    private String tradingSymbol;
    private String shortName;
    private String securityType;


    public Instrument(Segment segment, String name, Exchange exchange, String isin, String instrumentType, String key,
            int lotSize, int freezeQuantity, String exchangeToken, float tickSize, String tradingSymbol,
            String shortName, String securityType) {
        
        this.segment = segment;
        this.name = name;
        this.exchange = exchange;
        this.isin = isin;
        this.instrumentType = instrumentType;
        this.key = key;
        this.lotSize = lotSize;
        this.freezeQuantity = freezeQuantity;
        this.exchangeToken = exchangeToken;
        this.tickSize = tickSize;
        this.tradingSymbol = tradingSymbol;
        this.shortName = shortName;
        this.securityType = securityType;
    }


    public Segment getSegment() {
        return segment;
    }
    public void setSegment(Segment segment) {
        this.segment = segment;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Exchange getExchange() {
        return exchange;
    }
    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
    public String getIsin() {
        return isin;
    }
    public void setIsin(String isin) {
        this.isin = isin;
    }
    public String getInstrumentType() {
        return instrumentType;
    }
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public int getLotSize() {
        return lotSize;
    }
    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }
    public int getFreezeQuantity() {
        return freezeQuantity;
    }
    public void setFreezeQuantity(int freezeQuantity) {
        this.freezeQuantity = freezeQuantity;
    }
    public String getExchangeToken() {
        return exchangeToken;
    }
    public void setExchangeToken(String exchangeToken) {
        this.exchangeToken = exchangeToken;
    }
    public float getTickSize() {
        return tickSize;
    }
    public void setTickSize(float tickSize) {
        this.tickSize = tickSize;
    }
    public String getTradingSymbol() {
        return tradingSymbol;
    }
    public void setTradingSymbol(String tradingSymbol) {
        this.tradingSymbol = tradingSymbol;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getSecurityType() {
        return securityType;
    }
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    
}
