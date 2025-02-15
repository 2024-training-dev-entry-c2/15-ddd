package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.shared.domain.generic.AggregateRoot;

import java.util.List;

public class BankAccount extends AggregateRoot<BankAccountId> {
  private OwnerId ownerId;
  private List<Transaction> transactions;
  private Balance balance;

  // region Constructors
  public BankAccount() {
    super(new BankAccountId());
  }

  private BankAccount(BankAccountId identity){
    super(identity);
  }
  // endregion
  // region Getters and Setters
  public OwnerId getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(OwnerId ownerId) {
    this.ownerId = ownerId;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Balance getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }
  // endregion

  // region Domain Actions
  public void registerTransaction(String accountId, String ownerId, String transactionId, TypeEnum type, Double amount){
    apply(new CompletedTransaction(accountId, ownerId, transactionId, type, amount));
  }

  public void cancelTransaction(String ownerId, String transactionId, TypeEnum type, Double amount){
    apply(new RejectedTransaction(ownerId, transactionId, type, amount));
  }
  // endregion

  // region Public Methods

  // endregion

  // region Private Methods

  // endregion
}
