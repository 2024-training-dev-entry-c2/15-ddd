package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;

import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;

import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.monopoly_managment.domain.property.entities.Owner;
import com.monopoly.monopoly_managment.domain.property.values.Alias;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Portfolio;
import com.monopoly.monopoly_managment.domain.property.values.Token;
import com.monopoly.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
  private BankAccount bankAccount;
  private OwnerId ownerId;
  private Transaction transaction;

  @BeforeEach
  void setUp() {
    Owner owner = new Owner(OwnerId.of("owner-123"), Alias.of("Alias"), Token.of("Token"), Portfolio.of(List.of("property-123")), null);
    bankAccount = new BankAccount("owner-123");
    bankAccount.setOwnerId(owner.getIdentity().getValue());
    bankAccount.setBalance(new Balance(1000.0));
    bankAccount.setTransactions(new ArrayList<>());

    transaction = new Transaction(
      TransactionId.of("transaction-123"),
      200.0,
      TypeEnum.DEPOSIT,
      "destiny-123",
      "origin-123"
    );
  }

  @Test
  void createBankAccount() {
    assertNotNull(bankAccount);
    assertEquals(1000.0, bankAccount.getBalance().getValue());
    assertEquals(0, bankAccount.getTransactions().size());
  }

  @Test
  void plusBalance() {
    bankAccount.plusBalance(transaction.getAmount());
    assertEquals(1200.0, bankAccount.getBalance().getValue());
  }

  @Test
  void minusBalance() {
    bankAccount.minusBalance(transaction.getAmount());
    assertEquals(800.0, bankAccount.getBalance().getValue());
  }

  @Test
  void validateTransaction() {
    assertDoesNotThrow(() -> bankAccount.validateTransaction(transaction));
  }

  @Test
  void validateTransactionInsufficientFunds() {
    Transaction withdrawal = new Transaction(
      TransactionId.of("transaction-124"),
      2000.0,
      TypeEnum.RETIREMENT,
      "destiny-124",
      "origin-124"
    );
    assertThrows(IllegalArgumentException.class, () -> bankAccount.validateTransaction(withdrawal));
  }

  @Test
  void registerTransaction() {
    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      transaction.getIdentity(),
      transaction.getType(),
      transaction.getAmount(),
      transaction.getOrigin(),
      transaction.getDestiny()
    );
    assertEquals(1, bankAccount.getTransactions().size());
  }

  @Test
  void cancelTransaction() {
    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      transaction.getIdentity(),
      transaction.getType(),
      transaction.getAmount(),
      transaction.getOrigin(),
      transaction.getDestiny()
    );
    assertEquals(1, bankAccount.getTransactions().size());

    bankAccount.canceledTransaction(
      ownerId,
      transaction.getIdentity().getValue(),
      transaction.getType(),
      transaction.getAmount(),
      transaction.getOrigin(),
      transaction.getDestiny()
    );
    assertEquals(0, bankAccount.getTransactions().size());
  }

  @Test
  void cancelNonExistentTransaction() {
    assertThrows(IllegalArgumentException.class, () -> bankAccount.canceledTransaction(
      ownerId,
      TransactionId.of("non-existent-transaction").getValue(),
      transaction.getType(),
      transaction.getAmount(),
      transaction.getOrigin(),
      transaction.getDestiny()
    ));
  }

  @Test
  void registerAndCancelDepositTransaction() {
    Transaction depositTransaction = new Transaction(
      TransactionId.of("transaction-125"),
      300.0,
      TypeEnum.DEPOSIT,
      "destiny-125",
      "origin-125"
    );

    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      depositTransaction.getIdentity(),
      depositTransaction.getType(),
      depositTransaction.getAmount(),
      depositTransaction.getOrigin(),
      depositTransaction.getDestiny()
    );
    assertEquals(1, bankAccount.getTransactions().size());
    assertEquals(1300.0, bankAccount.getBalance().getValue());

    bankAccount.canceledTransaction(
      ownerId,
      depositTransaction.getIdentity().getValue(),
      depositTransaction.getType(),
      depositTransaction.getAmount(),
      depositTransaction.getOrigin(),
      depositTransaction.getDestiny()
    );
    assertEquals(0, bankAccount.getTransactions().size());
    assertEquals(1000.0, bankAccount.getBalance().getValue());
  }

  @Test
  void registerAndCancelRetirementTransaction() {
    Transaction retirementTransaction = new Transaction(
      TransactionId.of("transaction-126"),
      100.0,
      TypeEnum.RETIREMENT,
      "destiny-126",
      "origin-126"
    );

    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      retirementTransaction.getIdentity(),
      retirementTransaction.getType(),
      retirementTransaction.getAmount(),
      retirementTransaction.getOrigin(),
      retirementTransaction.getDestiny()
    );
    assertEquals(1, bankAccount.getTransactions().size());
    assertEquals(900.0, bankAccount.getBalance().getValue());

    bankAccount.canceledTransaction(
      ownerId,
      retirementTransaction.getIdentity().getValue(),
      retirementTransaction.getType(),
      retirementTransaction.getAmount(),
      retirementTransaction.getOrigin(),
      retirementTransaction.getDestiny()
    );
    assertEquals(0, bankAccount.getTransactions().size());
    assertEquals(1000.0, bankAccount.getBalance().getValue());
  }

  @Test
  void testValidatedFounds() {

    bankAccount.getTransactions().add(transaction);

    bankAccount.validatedFounds(transaction.getIdentity().getValue(), transaction.getAmount(), transaction.getType());

    assertEquals(1000.0, bankAccount.getBalance().getValue());
  }

  @Test
  void testNotValidatedFounds() {

    bankAccount.getTransactions().add(transaction);

    bankAccount.notValidatedFounds(transaction.getIdentity().getValue(), transaction.getAmount(), transaction.getType());

    assertEquals(0, bankAccount.getTransactions().size());
  }

  @Test
  void registerTransactionWithInsufficientFunds() {
    // Arrange
    Transaction withdrawal = new Transaction(
      TransactionId.of("transaction-124"),
      2000.0,
      TypeEnum.RETIREMENT,
      "destiny-124",
      "origin-124"
    );

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      bankAccount.registeredTransaction(
        bankAccount.getIdentity(),
        ownerId,
        withdrawal.getIdentity(),
        withdrawal.getType(),
        withdrawal.getAmount(),
        withdrawal.getOrigin(),
        withdrawal.getDestiny()
      );
    });

    assertEquals("Insufficient funds", exception.getMessage());
  }

  @Test
  void testFrom() {

    String identity = "bankAccount-123";
    Owner owner = new Owner(OwnerId.of("owner-123"), Alias.of("Alias"), Token.of("Token"), Portfolio.of(List.of("property-123")), null);
    Balance initialBalance = new Balance(1000.0);
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction(
      TransactionId.of("transaction-123"),
      200.0,
      TypeEnum.DEPOSIT,
      "destiny-123",
      "origin-123"
    ));
    List<DomainEvent> domainEvents = new ArrayList<>();
    domainEvents.add(new ValidatedFounds("transaction-123", 200.0, TypeEnum.DEPOSIT));

    BankAccount bankAccount = BankAccount.from(identity, ownerId.getValue(), domainEvents);

    assertNotNull(bankAccount);
    assertEquals(identity, bankAccount.getIdentity().getValue());
    assertEquals(owner.getIdentity(), bankAccount.getOwnerId());
    assertEquals(1000.0, bankAccount.getBalance().getValue());
    assertEquals(2, bankAccount.getTransactions().size());
    assertEquals("transaction-123", bankAccount.getTransactions().getFirst().getIdentity().getValue());
  }
}
