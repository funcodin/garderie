package com.garderie.service.exception.model;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    public ServiceException(final String message ){
        super(message);
    }

    public ServiceException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ServiceException(final String message, final HttpStatus httpStatus, final Throwable cause) {
        super(message,cause);
        this.httpStatus = httpStatus;
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
