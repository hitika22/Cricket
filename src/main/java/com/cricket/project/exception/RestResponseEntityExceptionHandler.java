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
    public ResponseEntity<ErrorMessage> teamException(TeamException teamException, WebRequest webRequest)
    {
        String errorMessageText = teamException.getMessage();
        HttpStatus status;

        if (errorMessageText.contains("Max 2 teams allowed!")) {
            status = HttpStatus.FORBIDDEN;
        } else if (errorMessageText.contains("Player Already In Team") || errorMessageText.contains("Team Already Has 11 Players")) {
            status = HttpStatus.CONFLICT;
        } else {
           status = HttpStatus.NOT_FOUND;
        }

        ErrorMessage errorMessage = new ErrorMessage(status, errorMessageText);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<ErrorMessage> playerException(PlayerException playerException, WebRequest webRequest)
    {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,playerException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
