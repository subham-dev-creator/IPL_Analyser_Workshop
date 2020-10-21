package com.training.workshop.iplanalyser.exception;

public class IPLAnalyserException extends Exception {
    public enum ExceptionType{
        INCORRECT_PATH;
    }

    ExceptionType type;

    public IPLAnalyserException(String msg, ExceptionType type) {
        super(msg);
        this.type = type;
    }
}
