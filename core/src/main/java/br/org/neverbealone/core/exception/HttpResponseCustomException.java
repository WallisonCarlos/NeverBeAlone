package br.org.neverbealone.core.exception;

import org.springframework.http.HttpStatus;

public class HttpResponseCustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public HttpResponseCustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
	
}
