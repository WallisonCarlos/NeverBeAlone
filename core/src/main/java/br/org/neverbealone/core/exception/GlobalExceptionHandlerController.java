package br.org.neverbealone.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(HttpResponseCustomException.class)
    public void handleCustomException(HttpServletResponse res, HttpResponseCustomException e) throws IOException {
        LOG.error("ERROR", e);
        res.sendError(e.getHttpStatus().value(), e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res, AccessDeniedException e) throws IOException {
        LOG.error("ERROR", e);
        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(HttpServletResponse res, IllegalArgumentException e) throws IOException {
        LOG.error("ERROR", e);
        res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res, Exception e) throws IOException {
        LOG.error("ERROR", e);
        res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
    }
    
    @ExceptionHandler(NotFoundEntityException.class)
    public void handleException(HttpServletResponse res, NotFoundEntityException e) throws IOException {
        LOG.error("ERROR", e);
        res.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
		MethodArgumentNotValidException ex) {
    		LOG.error("ERROR", ex);
	    	Map<String, String> errors = new HashMap<>();
	    	ex.getBindingResult().getAllErrors().forEach((error) -> {
	    		String fieldName = ((FieldError) error).getField();
	    		String errorMessage = error.getDefaultMessage();
	    		errors.put(fieldName, errorMessage);
	    	});
	    return errors;
	}


}
