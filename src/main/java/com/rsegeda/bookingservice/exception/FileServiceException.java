/*
    Created by Roman Segeda on 05 February 2020
*/

package com.rsegeda.bookingservice.exception;

public class FileServiceException extends BookingServiceException {

  public FileServiceException(String message, Exception e) {
    super(message.concat(".. Details: ").concat(e.getMessage()));
  }
}
