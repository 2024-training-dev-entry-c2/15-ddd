package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

import java.util.List;

public class Parties implements IValueObject {
  private final OwnerId ownerId;
  private final List<OwnerId> tenantId;

  private Parties(OwnerId ownerId, List<OwnerId> tenantId) {
    this.ownerId = ownerId;
    this.tenantId = tenantId;
    validate();
  }

  private Parties(OwnerId ownerId) {
    this.ownerId = ownerId;
    this.tenantId = null;
    validate();
  }

  public static Parties of(OwnerId ownerId) {
    return new Parties(ownerId);
  }

  public OwnerId getOwnerId() {
    return ownerId;
  }

  public List<OwnerId> getTenantId() {
    return tenantId;
  }

  @Override
  public void validate(){
    Validator.validateNull(this.ownerId, "Owner id");
  }
}
