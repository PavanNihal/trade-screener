package com.nihal.trade.screener.network.http.exceptions;

public class FetchFailedException extends RuntimeException {

    public FetchFailedException() {
        super();
      }
    
      public FetchFailedException(String message) {
        super(message);
      }
    
}
