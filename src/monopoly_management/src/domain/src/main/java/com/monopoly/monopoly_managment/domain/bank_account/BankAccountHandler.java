package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.CompletedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.NotValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.events.RejectedTransaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.Destiny;
import com.monopoly.monopoly_managment.domain.bank_account.values.Origin;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.Type;
import com.monopoly.shared.domain.generic.DomainActionsContainer;
import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

import static java.util.spi.ToolProvider.findFirst;

public class BankAccountHandler extends DomainActionsContainer {
  private final BankAccount bankAccount;

  public BankAccountHandler(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    add(registerTransaction());
    add(cancelTransaction());
    add(notValidateFounds());
    add(validateFounds());
  }

  public Consumer<? extends DomainEvent> registerTransaction() {
    return (CompletedTransaction event) -> {
      try {
        Transaction transaction = new Transaction(
          TransactionId.of(event.getTransactionId()), Amount.of(event.getAmount()), Type.of(event.getType()), Destiny.of(event.getDestiny()), Origin.of(event.getOrigin())
        );
        bankAccount.validateTransaction(transaction);
        switch (transaction.getTransactionType()) {
          case DEPOSIT -> bankAccount.plusBalance(transaction);
          case RETIREMENT -> bankAccount.minusBalance(transaction);
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
      bankAccount.getTransactions().forEach(t -> System.out.println("Transacción en la lista: " + t.getIdentity().getValue()));

      Transaction transaction = bankAccount.getTransactions().stream()
        .filter(t -> t.getIdentity().equals(transactionId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(event.getTransactionId() + " not found"));

      bankAccount.getTransactions().remove(transaction);
      switch (transaction.getTransactionType()) {
        case DEPOSIT -> bankAccount.minusBalance(transaction);
        case RETIREMENT -> bankAccount.plusBalance(transaction);
      }
    };
  }

  public Consumer<? extends DomainEvent> notValidateFounds() {
    return (NotValidatedFounds event) -> {
      Transaction transaction = bankAccount.getTransactions().stream().filter(t -> t.getIdentity().equals(TransactionId.of(event.getTransactionId()))).findFirst().orElseThrow();
      bankAccount.getTransactions().remove(transaction);
    };
  }

  public Consumer<? extends DomainEvent> validateFounds() {
    return (ValidatedFounds event) -> {
      Transaction transaction = bankAccount.getTransactions().stream()
        .filter(t -> t.getIdentity().equals(TransactionId.of(event.getTransactionId())))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(event.getTransactionId() + " not found"));
      bankAccount.getTransactions().add(transaction);
    };
  }

}