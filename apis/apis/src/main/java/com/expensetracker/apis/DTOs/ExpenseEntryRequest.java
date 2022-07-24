package com.expensetracker.apis.DTOs;

import java.text.MessageFormat;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "ExpenseEntryRequest")
public class ExpenseEntryRequest {

  @ApiModelProperty(name = "Expense Cause", example = "Course Fee")
  private String expenseCause;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  @ApiModelProperty(example = "01/01/2000", name = "date",
      notes = "Pass values in dd/MM/yyyy format")
  private LocalDate date;

  @Min(value = 0, message = "Amount can not be negative")
  @ApiModelProperty(name = "Amount", notes = "Positive values allowed only")
  private Double amount;

  public String toString() {
    return MessageFormat.format("expenseCause: {0}, date : {1}, amount: {2}", expenseCause, date,
        amount);
  }
}
