package com.monopoly.monopoly_managment.application.property.modifyowner;

import com.monopoly.shared.application.Request;

public class ModifyOwnerRequest extends Request {
  private String previousOwnerId;
  private String ownerId;


  public ModifyOwnerRequest(String aggregateId, String previousOwnerId, String ownerId) {
    super(aggregateId);
    this.previousOwnerId = previousOwnerId;
    this.ownerId = ownerId;
  }

  public ModifyOwnerRequest() {
    super(null);
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }


  public String getPreviousOwnerId() {
    return previousOwnerId;
  }

  public void setPreviousOwnerId(String previousOwnerId) {
    this.previousOwnerId = previousOwnerId;
  }
}