package com.springtest.redis;

public enum TransactionType {
  Deposit("deposit"),
  Investment("investment"),
  Payment("payment"),
  Purchase("purchase"),
  Refund("refund"),
  Fee("fee"),
  Withdrawal("withdrawal");

  private String value;

  TransactionType(String value) {
    this.value = value;
  }
}
