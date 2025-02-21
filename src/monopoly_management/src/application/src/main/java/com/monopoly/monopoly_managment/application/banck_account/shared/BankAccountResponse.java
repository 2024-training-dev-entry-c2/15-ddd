package com.monopoly.monopoly_managment.application.banck_account.shared;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;


import java.util.List;

public class BankAccountResponse {
  private final String bankAccountID;
  private final String ownerID;
  private final List<Transaction> transactions;
  private final Double balance;

  public BankAccountResponse(String bankAccountID, String ownerID, List<Transaction> transactions, Double balance) {
    this.bankAccountID = bankAccountID;
    this.ownerID = ownerID;
    this.transactions = transactions;
    this.balance = balance;
  }

  public String getBankAccountID() {
    return bankAccountID;
  }

  public String getOwnerID() {
    return ownerID;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public Double getBalance() {
    return balance;
  }

  public static class Transaction{
    private final String identity;
    private final Double amount;
    private final TypeEnum type;
    private final String origin;
    private final String destiny;

    public Transaction(String identity, Double amount, TypeEnum type, String origin, String destiny) {
      this.identity = identity;
      this.amount = amount;
      this.type = type;
      this.origin = origin;
      this.destiny = destiny;
    }

    public String getTransactionID() {
      return identity;
    }

    public Double getAmount() {
      return amount;
    }

    public TypeEnum getType() {
      return type;
    }

    public String getOrigin() {
      return origin;
    }

    public String getDestiny() {
      return destiny;
    }
  }
}
