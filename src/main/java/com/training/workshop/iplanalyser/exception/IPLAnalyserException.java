package com.training.workshop.iplanalyser.exception;

public class IPLAnalyserException extends Exception {
    public enum ExceptionType{
        INCORRECT_PATH, INCORRECT_STATE, INCORRECT_DELIMITER, INCORRECT_CSV_HEADER;
    }

    ExceptionType type;

    public IPLAnalyserException(String msg, ExceptionType type) {
        super(msg);
        this.type = type;
    }
}
