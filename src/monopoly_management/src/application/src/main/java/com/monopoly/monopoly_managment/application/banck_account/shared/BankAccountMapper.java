package com.monopoly.monopoly_managment.application.banck_account.shared;
import com.monopoly.monopoly_managment.domain.bank_account.BankAccount;

public class BankAccountMapper {
  public static BankAccountResponse toResponse(BankAccount bankAccount) {
    return new BankAccountResponse(
      bankAccount.getIdentity().getValue(),
      bankAccount.getOwnerId().getValue(),
      bankAccount.getTransactions().stream().map(transaction -> new BankAccountResponse.Transaction(transaction.getIdentity().getValue(), transaction.getAmount(), transaction.getType(), transaction.getOrigin(), transaction.getDestiny())).toList(),
      bankAccount.getBalance().getValue()
    );
  }
}

