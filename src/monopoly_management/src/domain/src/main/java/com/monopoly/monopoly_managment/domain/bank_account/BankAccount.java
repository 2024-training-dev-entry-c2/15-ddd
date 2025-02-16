package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.NotValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.events.ObtainedBalance;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.shared.domain.generic.AggregateRoot;

import java.util.ArrayList;
import java.util.List;

public class BankAccount extends AggregateRoot<BankAccountId> {
  private OwnerId ownerId;
  private List<Transaction> transactions;
  private Balance balance;

  // region Constructors
  public BankAccount(OwnerId ownerId, Balance initialBalance) {
    super(new BankAccountId());
    this.ownerId = ownerId;
    this.balance = initialBalance;
    this.transactions = new ArrayList<>();
  }

  private BankAccount(BankAccountId identity, OwnerId ownerId, Balance initialBalance, List<Transaction> transactions) {
    super(identity);
    this.ownerId = ownerId;
    this.balance = initialBalance;
    this.transactions = transactions;
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
    obtainedBalance(this.getIdentity().getValue(), balance.getValue());
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }
  // endregion

  // region Domain Actions
  public void registeredTransaction(String accountId, String ownerId, String transactionId, TypeEnum type, Double amount){
    apply(new CompletedTransaction(accountId, ownerId, transactionId, type, amount));
  }

  public void canceledTransaction(String ownerId, String transactionId, TypeEnum type, Double amount){
    apply(new RejectedTransaction(ownerId, transactionId, type, amount));
  }

  public void obtainedBalance(String accountId, Double amount){
    apply(new ObtainedBalance(accountId, amount));
  }

  public void validatedFounds(String accountId, Double amount, TypeEnum type){
    apply(new ValidatedFounds(accountId, amount, type));
  }

  public void notValidatedFounds(String accountId, Double amount, TypeEnum type){
    apply(new NotValidatedFounds(accountId, amount, type));
  }
  // endregion

  // region Public Methods
  public void registerTransaction(Transaction transaction){
    try{
      validateFunds(transaction);
      if (transaction.getType().getValue().equals(TypeEnum.DEPOSIT)){
        plusBalance(transaction);
      }
      else if (transaction.getType().getValue().equals(TypeEnum.RETIREMENT)){
        minusBalance(transaction);
      }
      this.transactions.add(transaction);
      registeredTransaction(this.getIdentity().getValue(), this.ownerId.getValue(), transaction.getIdentity().getValue(), transaction.getType().getValue(), transaction.getAmount().getValue());
    }catch (IllegalArgumentException e){
      canceledTransaction(this.ownerId.getValue(), transaction.getIdentity().getValue(), transaction.getType().getValue(), transaction.getAmount().getValue());
    }
  }
  // endregion

  // region Private Methods
  private void validateFunds(Transaction transaction){
      if(balance.getValue() < transaction.getAmount().getValue()){
        notValidatedFounds(this.getIdentity().getValue(), transaction.getAmount().getValue(), transaction.getType().getValue());
        throw new IllegalArgumentException("Insufficient funds");
      }
      validatedFounds(this.getIdentity().getValue(), transaction.getAmount().getValue(), transaction.getType().getValue());
  }

  private void plusBalance(Transaction transaction){
    this.balance = new Balance(this.balance.getValue() + transaction.getAmount().getValue());
  }

  private void minusBalance(Transaction transaction){
    this.balance = new Balance(this.balance.getValue() - transaction.getAmount().getValue());
  }
  // endregion
}