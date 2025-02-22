package com.monopoly.monopoly_managment.application.property.removedowner;

import com.monopoly.shared.application.Request;

public class RemoveOwnerRequest extends Request {

  private final String ownerId;


  public RemoveOwnerRequest(String aggregateId, String ownerId) {
    super(aggregateId);

    this.ownerId = ownerId;

  }

  public String getOwnerId() {
    return ownerId;
  }
}
