package com.monopoly.monopoly_managment.application.property.assignedowner;

import com.monopoly.shared.application.Request;

public class AssignOwnerRequest extends Request {
  private String ownerId;
  private String propertyId;

  public AssignOwnerRequest(String aggregateId, String ownerId, String propertyId) {
    super(aggregateId);
    this.ownerId = ownerId;
    this.propertyId = propertyId;
  }

  public AssignOwnerRequest(){
    super(null);
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }
}
