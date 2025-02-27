package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

import java.util.List;

public class Parties implements IValueObject {
  private final String ownerId;
  private final List<String> tenantId;

  private Parties(String ownerId, List<String> tenantId) {
    this.ownerId = ownerId;
    this.tenantId = tenantId;
    validate();
  }

  private Parties(String ownerId) {
    this.ownerId = ownerId;
    this.tenantId = null;
    validate();
  }

  public static Parties of(String ownerId) {
    return new Parties(ownerId);
  }

  public String getOwnerId() {
    return ownerId;
  }

  public List<String> getTenantId() {
    return tenantId;
  }

  @Override
  public void validate(){

  }
}
