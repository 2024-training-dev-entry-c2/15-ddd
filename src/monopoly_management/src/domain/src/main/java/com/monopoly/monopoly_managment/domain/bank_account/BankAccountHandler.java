package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.shared.domain.generic.DomainActionsContainer;

public class BankAccountHandler extends DomainActionsContainer {
  private final BankAccount bankAccount;
  public BankAccountHandler(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public void registerTransaction(Transaction transaction, Balance balance) {
    try {
      bankAccount.validateTransaction(transaction);
      switch (transaction.getTransactionType()) {
        case DEPOSIT -> bankAccount.plusBalance(transaction);
        case RETIREMENT -> bankAccount.minusBalance(transaction);
        default -> throw new IllegalArgumentException("Invalid transaction type");
      }
      bankAccount.validatedFounds(
        bankAccount.getIdentity(), transaction.getAmount(), transaction.getType().getValue()
      );
      bankAccount.getTransactions().add(transaction);
      bankAccount.registeredTransaction(
        bankAccount.getIdentity(), bankAccount.getOwnerId(),
        transaction.getIdentity(), transaction.getType().getValue(), transaction.getAmount()
      );

    } catch (IllegalArgumentException e) {
      bankAccount.notValidatedFounds(
        bankAccount.getIdentity(), transaction.getAmount(), transaction.getType().getValue()
      );
      bankAccount.canceledTransaction(
        bankAccount.getOwnerId(), transaction.getIdentity(),
        transaction.getType().getValue(), transaction.getAmount()
      );
      throw new IllegalArgumentException("Insufficient funds");
    }
  }

  public void getBalance(){
    bankAccount.obtainedBalance(bankAccount.getIdentity(), bankAccount.getBalance().getValue());
  }
}
