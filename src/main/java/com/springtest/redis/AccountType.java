package com.springtest.redis;

public enum AccountType {
  Checking("checking"),
  Savings("savings"),
  Brokerage("brokerage"),
  Retirement("retirement"),
  CreditCard("credit_card"),
  DebitCard("debit_card");

  private String value;

  AccountType(String value) {
    this.value = value;
  }
}
