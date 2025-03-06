package com.monopoly.monopoly_managment.application.banck_account.shared;

import com.monopoly.monopoly_managment.domain.bank_account.values.TypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BankAccountResponseTest {

  @Test
  void testTransaction() {
    String identity = "transaction-123";
    Double amount = 100.0;
    TypeEnum type = TypeEnum.DEPOSIT;
    String origin = "account-123";
    String destiny = "account-456";

    BankAccountResponse.Transaction transaction = new BankAccountResponse.Transaction(identity, amount, type, origin, destiny);

    assertNotNull(transaction);
    assertEquals(identity, transaction.getTransactionID());
    assertEquals(amount, transaction.getAmount());
    assertEquals(type, transaction.getType());
    assertEquals(origin, transaction.getOrigin());
    assertEquals(destiny, transaction.getDestiny());
  }
} 