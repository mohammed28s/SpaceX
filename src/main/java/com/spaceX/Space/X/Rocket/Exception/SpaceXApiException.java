package com.spaceX.Space.X.Rocket.Exception;


import lombok.*;

@Data
public class SpaceXApiException extends RuntimeException {
    private final int statusCode;

    public SpaceXApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}