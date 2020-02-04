/*
    Created by Roman Segeda on 04 February 2020
*/

package com.rsegeda.bookingservice.controller;

import com.rsegeda.bookingservice.exception.BookingServiceException;

import javax.validation.ValidationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, ValidationException.class})
  protected ResponseEntity<Object> handleValidation(RuntimeException ex, WebRequest request) {
    String bodyOfResponse =
        "There was a problem with the request's data validation. " + ex.getMessage();
    log.warn(bodyOfResponse, ex);
    return handleExceptionInternal
        (ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {BookingServiceException.class})
  protected ResponseEntity<Object> handleServiceException(RuntimeException ex, WebRequest request) {
    String bodyOfResponse =
        "There was a problem with the request's data processing. " + ex.getMessage();
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal
        (ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {RuntimeException.class})
  protected ResponseEntity<Object> handleGeneralIssue(
      RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "This should be application specific. " + ex.getMessage();
    log.error(bodyOfResponse, ex);
    return handleExceptionInternal
        (ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

}