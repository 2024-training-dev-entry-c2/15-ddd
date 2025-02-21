package com.monopoly.monopoly_managment.application.banck_account.createbankaccount;

import com.monopoly.shared.application.Request;

import java.util.List;

public class CreateBankAccountRequest extends Request {
  private final String owner;
  private final List<String> transactions;
  private final Double balance;

  CreateBankAccountRequest(String owner, List<String> transactions, Double balance) {
    super(null);
    this.owner = owner;
    this.transactions = transactions;
    this.balance = balance;
  }

  public String getOwnerId() {
    return CreateBankAccountRequest.this.owner;
  }

  public List<String> getTransactions() {
    return transactions;
  }

  public Double getBalance() {
    return balance;
  }



}