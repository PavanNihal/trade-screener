package com.nihal.trade.screener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.nihal.trade.screener.data.Instrument;

@Component
public interface InstrumentRepository extends CrudRepository<Instrument, String>{
    
}
