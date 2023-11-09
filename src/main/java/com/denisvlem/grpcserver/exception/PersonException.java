package com.denisvlem.grpcserver.exception;

import java.time.Instant;

public class PersonException extends RuntimeException {

    private final Instant raisingTime;

    public PersonException(String message) {
        super(message);
        raisingTime = Instant.now();
    }

    public Instant getRaisingTime() {
        return raisingTime;
    }
}
