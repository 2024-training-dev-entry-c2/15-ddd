package com.monopoly.monopoly_managment.application.property.assignedowner;

import com.monopoly.shared.application.Request;

public class AssignOwnerRequest extends Request {
  private final String ownerId;
  private final String propertyId;

  public AssignOwnerRequest(String aggregateId, String ownerId, String propertyId) {
    super(aggregateId);
    this.ownerId = ownerId;
    this.propertyId = propertyId;
  }
  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }
}
