package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CreatedBankAccount;
import com.monopoly.monopoly_managment.domain.bank_account.events.NotValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.shared.domain.generic.DomainActionsContainer;
import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.function.Consumer;


public class BankAccountHandler extends DomainActionsContainer {
  private final BankAccount bankAccount;

  public BankAccountHandler(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    add(createdBankAccount());
    add(registerTransaction());
    add(cancelTransaction());
    add(notValidateFounds());
    add(validateFounds());
  }

  public Consumer<? extends DomainEvent> createdBankAccount(){
    return (CreatedBankAccount event) -> {
      bankAccount.setBalance(new Balance(2000.0));
      bankAccount.setTransactions(new ArrayList<>());
      bankAccount.setOwnerId(event.getOwnerId());
    };
  }

  public Consumer<? extends DomainEvent> registerTransaction() {
    return (CompletedTransaction event) -> {
      try {
        Transaction transaction = new Transaction(
          TransactionId.of(event.getTransactionId()), event.getAmount(), event.getType(), event.getDestiny(), event.getOrigin()
        );
        switch (transaction.getTransactionType()) {
          case DEPOSIT -> bankAccount.plusBalance(event.getAmount());
          case RETIREMENT -> bankAccount.minusBalance(transaction.getAmount());
        }
        bankAccount.getTransactions().add(transaction);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Insufficient funds");
      }
    };
  }

  public Consumer<? extends DomainEvent> cancelTransaction() {
    return (RejectedTransaction event) -> {
      TransactionId transactionId = TransactionId.of(event.getTransactionId());
      System.out.println("Buscando transacción con ID: " + transactionId.getValue());
      bankAccount.getTransactions().forEach(t -> System.out.println("Transacción en la lista: " + TransactionId.of(t.getIdentity().getValue())));

      Transaction transaction = bankAccount.getTransactions().stream()
        .filter(t -> TransactionId.of(t.getIdentity().getValue()).equals(transactionId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(event.getTransactionId() + " not found"));

      if (transaction.getType() == TypeEnum.DEPOSIT) {
        bankAccount.plusBalance(transaction.getAmount());
      } else {
        bankAccount.minusBalance(transaction.getAmount());
      }

      bankAccount.getTransactions().remove(transaction);
      switch (event.getType()) {
        case DEPOSIT -> bankAccount.minusBalance(event.getAmount());
        case RETIREMENT -> bankAccount.plusBalance(event.getAmount());
      }
    };
  }

  public Consumer<? extends DomainEvent> notValidateFounds() {
    return (NotValidatedFounds event) -> {
      Transaction transaction = bankAccount.getTransactions().stream().filter(t -> TransactionId.of(t.getIdentity().getValue()).equals(TransactionId.of(event.getTransactionId()))).findFirst().orElseThrow();
      bankAccount.getTransactions().remove(transaction);
    };
  }

  public Consumer<? extends DomainEvent> validateFounds() {
    return (ValidatedFounds event) -> {
      Transaction transaction = bankAccount.getTransactions().stream()
        .filter(t -> TransactionId.of(t.getIdentity().getValue()).equals(TransactionId.of(event.getTransactionId())))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(event.getTransactionId() + " not found"));
      bankAccount.getTransactions().add(transaction);
    };
  }

}