package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.NotValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.events.ObtainedBalance;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.BankAccountId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
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
    obtainedBalance(this.getIdentity(), balance.getValue());
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }
  // endregion

  // region Domain Actions
  public void registeredTransaction(BankAccountId accountId, OwnerId ownerId, TransactionId transactionId, TypeEnum type, Amount amount){
    apply(new CompletedTransaction(accountId.getValue(), ownerId.getValue(), transactionId.getValue(), type, amount.getValue()));
  }

  public void canceledTransaction(OwnerId ownerId, TransactionId transactionId, TypeEnum type, Amount amount){
    apply(new RejectedTransaction(ownerId.getValue(), transactionId.getValue(), type, amount.getValue()));
  }

  public void obtainedBalance(BankAccountId accountId, Double amount){
    apply(new ObtainedBalance(accountId.getValue(), amount));
  }

  public void validatedFounds(BankAccountId accountId, Amount amount, TypeEnum type){
    apply(new ValidatedFounds(accountId.getValue(), amount.getValue(), type));
  }

  public void notValidatedFounds(BankAccountId accountId, Amount amount, TypeEnum type){
    apply(new NotValidatedFounds(accountId.getValue(), amount.getValue(), type));
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
      registeredTransaction(this.getIdentity(), this.ownerId, transaction.getIdentity(), transaction.getType().getValue(), transaction.getAmount());
    }catch (IllegalArgumentException e){
      canceledTransaction(this.ownerId, transaction.getIdentity(), transaction.getType().getValue(), transaction.getAmount());
    }
  }
  // endregion

  // region Private Methods
  private void validateFunds(Transaction transaction){
      if(balance.getValue() < transaction.getAmount().getValue()){
        notValidatedFounds(this.getIdentity(), transaction.getAmount(), transaction.getType().getValue());
        throw new IllegalArgumentException("Insufficient funds");
      }
      validatedFounds(this.getIdentity(), transaction.getAmount(), transaction.getType().getValue());
  }

  private void plusBalance(Transaction transaction){
    this.balance = new Balance(this.balance.getValue() + transaction.getAmount().getValue());
  }

  private void minusBalance(Transaction transaction){
    this.balance = new Balance(this.balance.getValue() - transaction.getAmount().getValue());
  }
  // endregion
}