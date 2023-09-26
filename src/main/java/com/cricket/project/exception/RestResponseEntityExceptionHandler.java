package com.cricket.project.exception;

import com.cricket.project.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TeamException.class)
    public ResponseEntity<ErrorMessage> teamLimitExceed(TeamException teamLimitExceededException, WebRequest webRequest)
    {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN,teamLimitExceededException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }
}
