package com.nihal.trade.screener.network.http;

import org.springframework.stereotype.Component;

import com.nihal.trade.screener.repository.InstrumentRepository;

@Component
public abstract class InstrumentListFetcher {

    protected InstrumentRepository instrumentRepository;

    public InstrumentListFetcher(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }
    
    public abstract void fetchInstruments();
}
