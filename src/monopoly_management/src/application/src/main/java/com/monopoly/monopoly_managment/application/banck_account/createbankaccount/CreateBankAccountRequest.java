package com.monopoly.monopoly_managment.application.banck_account.createbankaccount;

import com.monopoly.shared.application.Request;

import java.util.List;

public class CreateBankAccountRequest extends Request {
  private final String owner;


  CreateBankAccountRequest(String owner) {
    super(null);
    this.owner = owner;
  }

  public String getOwnerId() {
    return CreateBankAccountRequest.this.owner;
  }

}