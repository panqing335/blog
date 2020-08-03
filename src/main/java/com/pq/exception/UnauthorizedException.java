package com.pq.exception;

import org.springframework.stereotype.Component;

@Component
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
