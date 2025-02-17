package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainActionsContainer;

public class BankAccountHandler extends DomainActionsContainer {
  private final BankAccount bankAccount;
  public BankAccountHandler(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public void registerTransaction(Transaction transaction, Balance balance){
    try{
      if (transaction.isEnoughFunds(transaction, balance)) {
        bankAccount.validatedFounds(bankAccount.getIdentity(), transaction.getAmount(), transaction.getType().getValue());
      }
      else{
        bankAccount.notValidatedFounds(bankAccount.getIdentity(), transaction.getAmount(), transaction.getType().getValue());
        throw new IllegalArgumentException("Insufficient funds");
      }
      if (transaction.getType().getValue().equals(TypeEnum.DEPOSIT)){
        bankAccount.plusBalance(transaction);
      }
      else if (transaction.getType().getValue().equals(TypeEnum.RETIREMENT)){
        bankAccount.minusBalance(transaction);
      }
      bankAccount.getTransactions().add(transaction);
      bankAccount.registeredTransaction(bankAccount.getIdentity(), bankAccount.getOwnerId(), transaction.getIdentity(), transaction.getType().getValue(), transaction.getAmount());
    }catch (IllegalArgumentException e){
      bankAccount.canceledTransaction(bankAccount.getOwnerId(), transaction.getIdentity(), transaction.getType().getValue(), transaction.getAmount());
    }
  }
}
