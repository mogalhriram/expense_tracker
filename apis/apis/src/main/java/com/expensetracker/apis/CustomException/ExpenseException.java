package com.expensetracker.apis.CustomException;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ExpenseException extends Exception {
  private HttpStatus status;
  private Object responseObj;

  public ExpenseException(String errorMsg, HttpStatus status, Object responseObj) {
    super(errorMsg);
    this.status = status;
    this.responseObj = responseObj;
  }

}
