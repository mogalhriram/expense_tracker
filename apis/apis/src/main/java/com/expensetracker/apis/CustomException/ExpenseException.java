package com.expensetracker.apis.CustomException;

public class ExpenseException extends Exception {

  public ExpenseException(String errorMsg) {
    super(errorMsg);
  }

}
