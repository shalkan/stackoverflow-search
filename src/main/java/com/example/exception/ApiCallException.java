package com.example.exception;

/**
 * @author gilfanovrenat
 */

public class ApiCallException extends RuntimeException {

  public ApiCallException() {
  }

  public ApiCallException(String message) {
    super(message);
  }

  public ApiCallException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiCallException(Throwable cause) {
    super(cause);
  }

  public ApiCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}