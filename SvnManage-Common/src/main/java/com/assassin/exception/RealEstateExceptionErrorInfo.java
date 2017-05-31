package com.assassin.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RealEstateExceptionErrorInfo {

  private String message;
  private String exception;

  public RealEstateExceptionErrorInfo(String message, Exception ex) {
      this.message = message;
      if (ex != null) {
        this.exception = ex.getLocalizedMessage();
      }
  }

  public String getMessage() {
      return message;
  }

  public void setMessage(String message) {
      this.message = message;
  }
  
  public void setException(String exception) {
      this.exception = exception;
  }
  
  @JsonInclude(Include.NON_NULL)
  public String getException() {
      return exception;
  }
}
