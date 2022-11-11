package com.javamentor.qa.platform.webapp.controllers.rest.advice;

import com.javamentor.qa.platform.exception.AnswerException;
import com.javamentor.qa.platform.exception.ApiRequestException;
import com.javamentor.qa.platform.exception.ConstrainException;
import com.javamentor.qa.platform.exception.VoteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AnswerException.class)
    public ResponseEntity<Object> handlerAnswerException(AnswerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstrainException.class)
    public ResponseEntity<Object> handlerConstrainException(ConstrainException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VoteException.class)
    public ResponseEntity<Object> handlerVoteException(VoteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
