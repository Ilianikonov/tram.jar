package com.metro.tram.exception;

public class CodeIsOccupiedException extends RuntimeException {
    public CodeIsOccupiedException (String message) {
        super(message);
    }
}
