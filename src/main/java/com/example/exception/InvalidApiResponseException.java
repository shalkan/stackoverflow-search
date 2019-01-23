package com.example.exception;

/**
 * @author gilfanovrenat
 */
public class InvalidApiResponseException extends RuntimeException {

  public InvalidApiResponseException() {
  }

  public InvalidApiResponseException(String message) {
    super(message);
  }

  public InvalidApiResponseException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidApiResponseException(Throwable cause) {
    super(cause);
  }

  public InvalidApiResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
