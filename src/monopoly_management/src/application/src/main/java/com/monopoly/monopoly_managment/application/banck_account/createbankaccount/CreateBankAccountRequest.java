package com.monopoly.monopoly_managment.application.banck_account.createbankaccount;

import com.monopoly.shared.application.Request;

public class CreateBankAccountRequest extends Request {
  private String owner;

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public CreateBankAccountRequest(String owner) {
    super(null);
    this.owner = owner;
  }

  public CreateBankAccountRequest(){
    super(null);
  }

  public String getOwner() {
    return owner;
  }

}