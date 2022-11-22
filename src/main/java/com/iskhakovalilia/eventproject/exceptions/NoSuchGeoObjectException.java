package com.iskhakovalilia.eventproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NoSuchGeoObjectException is thrown when geoobjects can not be handled.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Could not find such country/city.")
public class NoSuchGeoObjectException extends RuntimeException {

    public NoSuchGeoObjectException(String message) {
        super(message);
    }

}