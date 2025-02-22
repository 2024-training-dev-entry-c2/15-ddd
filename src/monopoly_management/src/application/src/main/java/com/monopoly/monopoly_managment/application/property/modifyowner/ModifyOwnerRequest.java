package com.monopoly.monopoly_managment.application.property.modifyowner;

import com.monopoly.shared.application.Request;

public class ModifyOwnerRequest extends Request {
  private final String previousOwnerId;
  private final String ownerId;
  private final String propertyId;

  public ModifyOwnerRequest(String aggregateId, String previousOwnerId,String ownerId, String propertyId) {
    super(aggregateId);
    this.previousOwnerId = previousOwnerId;
    this.ownerId = ownerId;
    this.propertyId = propertyId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public String getPreviousOwnerId() {
    return previousOwnerId;
  }
}
