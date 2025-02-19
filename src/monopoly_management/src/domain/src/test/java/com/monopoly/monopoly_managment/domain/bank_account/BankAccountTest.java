package com.monopoly.monopoly_managment.domain.bank_account;

import com.monopoly.monopoly_managment.domain.bank_account.entities.Transaction;
import com.monopoly.monopoly_managment.domain.bank_account.events.ValidatedFounds;
import com.monopoly.monopoly_managment.domain.bank_account.values.Amount;
import com.monopoly.monopoly_managment.domain.bank_account.values.Balance;
import com.monopoly.monopoly_managment.domain.bank_account.values.Destiny;
import com.monopoly.monopoly_managment.domain.bank_account.values.Origin;
import com.monopoly.monopoly_managment.domain.bank_account.values.TransactionId;
import com.monopoly.monopoly_managment.domain.bank_account.values.Type;
import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
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
    bankAccount = new BankAccount();
    ownerId = new OwnerId("owner-123");
    bankAccount.setOwnerId(ownerId);
    bankAccount.setBalance(new Balance(1000.0));
    bankAccount.setTransactions(new ArrayList<>());

    transaction = new Transaction(
      TransactionId.of("transaction-123"),
      Amount.of(200.0),
      Type.of(TypeEnum.DEPOSIT),
      Destiny.of("destiny-123"),
      Origin.of("origin-123")
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
    bankAccount.plusBalance(transaction);
    assertEquals(1200.0, bankAccount.getBalance().getValue());
  }

  @Test
  void minusBalance() {
    bankAccount.minusBalance(transaction);
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
      Amount.of(2000.0),
      Type.of(TypeEnum.RETIREMENT),
      Destiny.of("destiny-124"),
      Origin.of("origin-124")
    );
    assertThrows(IllegalArgumentException.class, () -> bankAccount.validateTransaction(withdrawal));
  }

  @Test
  void registerTransaction() {
    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      transaction.getIdentity(),
      transaction.getType().getValue(),
      transaction.getAmount(),
      transaction.getOrigin().getValue(),
      transaction.getDestiny().getValue()
    );
    assertEquals(1, bankAccount.getTransactions().size());
  }

  @Test
  void cancelTransaction() {
    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      transaction.getIdentity(),
      transaction.getType().getValue(),
      transaction.getAmount(),
      transaction.getOrigin().getValue(),
      transaction.getDestiny().getValue()
    );
    assertEquals(1, bankAccount.getTransactions().size());

    bankAccount.canceledTransaction(
      ownerId,
      transaction.getIdentity(),
      transaction.getType().getValue(),
      transaction.getAmount().getValue(),
      transaction.getOrigin().getValue(),
      transaction.getDestiny().getValue()
    );
    assertEquals(0, bankAccount.getTransactions().size());
  }

  @Test
  void cancelNonExistentTransaction() {
    assertThrows(IllegalArgumentException.class, () -> bankAccount.canceledTransaction(
      ownerId,
      TransactionId.of("non-existent-transaction"),
      transaction.getType().getValue(),
      transaction.getAmount().getValue(),
      transaction.getOrigin().getValue(),
      transaction.getDestiny().getValue()
    ));
  }

  @Test
  void registerAndCancelDepositTransaction() {
    Transaction depositTransaction = new Transaction(
      TransactionId.of("transaction-125"),
      Amount.of(300.0),
      Type.of(TypeEnum.DEPOSIT),
      Destiny.of("destiny-125"),
      Origin.of("origin-125")
    );

    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      depositTransaction.getIdentity(),
      depositTransaction.getType().getValue(),
      depositTransaction.getAmount(),
      depositTransaction.getOrigin().getValue(),
      depositTransaction.getDestiny().getValue()
    );
    assertEquals(1, bankAccount.getTransactions().size());
    assertEquals(1300.0, bankAccount.getBalance().getValue());

    bankAccount.canceledTransaction(
      ownerId,
      depositTransaction.getIdentity(),
      depositTransaction.getType().getValue(),
      depositTransaction.getAmount().getValue(),
      depositTransaction.getOrigin().getValue(),
      depositTransaction.getDestiny().getValue()
    );
    assertEquals(0, bankAccount.getTransactions().size());
    assertEquals(1000.0, bankAccount.getBalance().getValue());
  }

  @Test
  void registerAndCancelRetirementTransaction() {
    Transaction retirementTransaction = new Transaction(
      TransactionId.of("transaction-126"),
      Amount.of(100.0),
      Type.of(TypeEnum.RETIREMENT),
      Destiny.of("destiny-126"),
      Origin.of("origin-126")
    );

    bankAccount.registeredTransaction(
      bankAccount.getIdentity(),
      ownerId,
      retirementTransaction.getIdentity(),
      retirementTransaction.getType().getValue(),
      retirementTransaction.getAmount(),
      retirementTransaction.getOrigin().getValue(),
      retirementTransaction.getDestiny().getValue()
    );
    assertEquals(1, bankAccount.getTransactions().size());
    assertEquals(900.0, bankAccount.getBalance().getValue());

    bankAccount.canceledTransaction(
      ownerId,
      retirementTransaction.getIdentity(),
      retirementTransaction.getType().getValue(),
      retirementTransaction.getAmount().getValue(),
      retirementTransaction.getOrigin().getValue(),
      retirementTransaction.getDestiny().getValue()
    );
    assertEquals(0, bankAccount.getTransactions().size());
    assertEquals(1000.0, bankAccount.getBalance().getValue());
  }

  @Test
  void testValidatedFounds() {

    bankAccount.getTransactions().add(transaction);

    bankAccount.validatedFounds(transaction.getIdentity(), transaction.getAmount().getValue(), transaction.getType().getValue());

    assertEquals(1000.0, bankAccount.getBalance().getValue());
  }

  @Test
  void testNotValidatedFounds() {

    bankAccount.getTransactions().add(transaction);

    bankAccount.notValidatedFounds(transaction.getIdentity(), transaction.getAmount().getValue(), transaction.getType().getValue());

    assertEquals(0, bankAccount.getTransactions().size());
  }

  @Test
  void registerTransactionWithInsufficientFunds() {
    // Arrange
    Transaction withdrawal = new Transaction(
      TransactionId.of("transaction-124"),
      Amount.of(2000.0),
      Type.of(TypeEnum.RETIREMENT),
      Destiny.of("destiny-124"),
      Origin.of("origin-124")
    );

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      bankAccount.registeredTransaction(
        bankAccount.getIdentity(),
        ownerId,
        withdrawal.getIdentity(),
        withdrawal.getType().getValue(),
        withdrawal.getAmount(),
        withdrawal.getOrigin().getValue(),
        withdrawal.getDestiny().getValue()
      );
    });

    assertEquals("Insufficient funds", exception.getMessage());
  }

  @Test
  void testFrom() {

    String identity = "bankAccount-123";
    OwnerId ownerId = new OwnerId("owner-123");
    Balance initialBalance = new Balance(1000.0);
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction(
      TransactionId.of("transaction-123"),
      Amount.of(200.0),
      Type.of(TypeEnum.DEPOSIT),
      Destiny.of("destiny-123"),
      Origin.of("origin-123")
    ));
    List<DomainEvent> domainEvents = new ArrayList<>();
    domainEvents.add(new ValidatedFounds("transaction-123", 200.0, TypeEnum.DEPOSIT));

    BankAccount bankAccount = BankAccount.from(identity, ownerId, initialBalance, transactions, domainEvents);

    assertNotNull(bankAccount);
    assertEquals(identity, bankAccount.getIdentity().getValue());
    assertEquals(ownerId, bankAccount.getOwnerId());
    assertEquals(1000.0, bankAccount.getBalance().getValue());
    assertEquals(2, bankAccount.getTransactions().size());
    assertEquals("transaction-123", bankAccount.getTransactions().get(0).getIdentity().getValue());
  }
}
