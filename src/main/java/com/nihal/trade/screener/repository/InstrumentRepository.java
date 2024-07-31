package com.nihal.trade.screener.repository;

import java.util.*;
import org.springframework.stereotype.Component;
import com.nihal.trade.screener.data.Instrument;

@Component
public interface InstrumentRepository {
    void refreshAllInstruments(List<Instrument> instrumentList);
}
