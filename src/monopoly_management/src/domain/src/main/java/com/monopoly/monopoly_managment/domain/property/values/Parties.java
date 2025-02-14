package com.monopoly.monopoly_managment.domain.property.values;

public class Parties {
  private final String ownerId;
  private final String tenantId;

  public Parties (String ownerId, String tenantId){
    if (ownerId == null || tenantId == null) {
      throw new IllegalArgumentException("Owner and tenant cannot be null");
    }
    this.ownerId = ownerId;
    this.tenantId = tenantId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getTenantId() {
    return tenantId;
  }
}
