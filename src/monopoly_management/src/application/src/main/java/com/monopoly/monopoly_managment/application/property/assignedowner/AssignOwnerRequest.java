package com.monopoly.monopoly_managment.application.property.assignedowner;

import com.monopoly.shared.application.Request;

public class AssignOwnerRequest extends Request {
  private String ownerId;


  public AssignOwnerRequest(String aggregateId, String ownerId) {
    super(aggregateId);
    this.ownerId = ownerId;

  }

  public AssignOwnerRequest(){
    super(null);
  }

  public String getOwnerId() {
    return ownerId;
  }


  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

}
