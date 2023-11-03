package com.metro.tram.exception;

public class NotFoundTramRouteException extends RuntimeException{
    public NotFoundTramRouteException (String message) {
        super(message);
    }
}
