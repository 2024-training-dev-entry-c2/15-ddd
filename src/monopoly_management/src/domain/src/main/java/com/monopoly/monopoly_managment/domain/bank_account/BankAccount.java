package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.NotValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
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
  private OwnerId ownerId;
  private List<Transaction> transactions;
  private Balance balance;

  // region Constructors
  public BankAccount() {
    super(new BankAccountId());
    this.transactions = new ArrayList<>();
    this.balance = new Balance(0.0);
    subscribe(new BankAccountHandler(this));
  }

  private BankAccount(BankAccountId identity) {
    super(identity);
    this.transactions = new ArrayList<>();
    this.balance = new Balance(0.0);
    subscribe(new BankAccountHandler(this));
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
  public void registeredTransaction(BankAccountId accountId, OwnerId ownerId, TransactionId transactionId, TypeEnum type, Amount amount, String origin, String destiny){
    apply(new CompletedTransaction(accountId.getValue(), ownerId.getValue(), transactionId.getValue(), type, amount.getValue(), origin, destiny));
  }

  public void canceledTransaction(OwnerId ownerId, TransactionId transactionId, TypeEnum type, Double amount, String origin, String destiny){
    apply(new RejectedTransaction(ownerId.getValue(), transactionId.getValue(), type, amount, origin, destiny));
  }

  public void validatedFounds(TransactionId transactionId, Double amount, TypeEnum type) {
    apply(new ValidatedFounds(transactionId.getValue(), amount, type));
  }

  public void notValidatedFounds(TransactionId transactionId, Double amount, TypeEnum type) {
    apply(new NotValidatedFounds(transactionId.getValue(), amount, type));
  }
  // endregion

  // region Public Methods

  public void plusBalance(Transaction transaction){
    this.balance = new Balance(this.balance.getValue() + transaction.getAmount().getValue());
  }

  public void minusBalance(Transaction transaction){
    this.balance = new Balance(this.balance.getValue() - transaction.getAmount().getValue());
  }

  public void validateTransaction(Transaction transaction){
    transaction.isEnoughFunds(transaction, this.balance);
    }
  // endregion

  public static BankAccount from(final String identity, final OwnerId ownerId, final Balance initialBalance, final List<Transaction> transactions, final List<DomainEvent> domainEvents) {
    BankAccount bankAccount = new BankAccount(BankAccountId.of(identity));
    bankAccount.setOwnerId(ownerId);
    bankAccount.setBalance(initialBalance);
    bankAccount.setTransactions(transactions);

    domainEvents.forEach(bankAccount::apply);
    return bankAccount;
  }
  }

