package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CreatedBankAccount;
import com.monopoly.monopoly_managment.domain.bank_account.events.NotValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;

import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.shared.domain.generic.AggregateRoot;
import com.monopoly.shared.domain.generic.DomainEvent;


import java.util.ArrayList;
import java.util.List;

public class BankAccount extends AggregateRoot<BankAccountId> {
  private OwnerId owner;
  private List<Transaction> transactions;
  private Balance balance;

  // region Constructors
  public BankAccount(String ownerId) {
    super(new BankAccountId());
    this.owner = OwnerId.of(ownerId);
    this.transactions = new ArrayList<>();
    this.balance = new Balance(0.0);
    subscribe(new BankAccountHandler(this));
  }

  private BankAccount(BankAccountId identity, OwnerId ownerId) {
    super(identity);
    this.owner = ownerId;
    this.transactions = new ArrayList<>();
    this.balance = new Balance(0.0);
    subscribe(new BankAccountHandler(this));
  }
  // endregion
  // region Getters and Setters
  public OwnerId getOwnerId() {
    return owner;
  }

  public void setOwnerId(String ownerId) {
    this.owner = OwnerId.of(ownerId);
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

  public void createdBankAccount(String identity, String ownerID) {
    apply(new CreatedBankAccount(identity, ownerID));
  }

  public void registeredTransaction(BankAccountId accountId, OwnerId ownerId, TransactionId transactionId, TypeEnum type, Double amount, String origin, String destiny){
    apply(new CompletedTransaction(accountId.getValue(), ownerId.getValue(), transactionId.getValue(), type, amount, origin, destiny));
  }

  public void canceledTransaction(OwnerId ownerId, String transactionId, TypeEnum type, Double amount, String origin, String destiny){
    apply(new RejectedTransaction(ownerId.getValue(), transactionId, type, amount, origin, destiny));
  }

  public void validatedFounds(String transactionId, Double amount, TypeEnum type) {
    apply(new ValidatedFounds(transactionId, amount, type));
  }

  public void notValidatedFounds(String transactionId, Double amount, TypeEnum type) {
    apply(new NotValidatedFounds(transactionId, amount, type));
  }
  // endregion

  // region Public Methods

  public void plusBalance(Double amount){
    this.balance = new Balance(this.balance.getValue() + amount);
  }

  public void minusBalance(Double amount){
    this.balance = new Balance(this.balance.getValue() - amount);
  }

  public void validateTransaction(Transaction transaction){
    transaction.isEnoughFunds(transaction, this.balance);
    }
  // endregion

  public static BankAccount from(final String identity,final String ownerId, final List<DomainEvent> domainEvents) {
    BankAccount bankAccount = new BankAccount(BankAccountId.of(identity), OwnerId.of(ownerId));

    domainEvents.forEach(bankAccount::apply);
    return bankAccount;
  }
  }

