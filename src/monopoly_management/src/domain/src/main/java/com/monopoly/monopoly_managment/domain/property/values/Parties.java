package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Parties implements IValueObject {
  private final PartsEnum ownerId;
  private final PartsEnum tenantId;

  private Parties(PartsEnum ownerId, PartsEnum tenantId) {
    this.ownerId = ownerId;
    this.tenantId = tenantId;
    validate();
  }

  public static Parties of(PartsEnum ownerId, PartsEnum tenantId) {
    return new Parties(ownerId, tenantId);
  }

  public PartsEnum getOwnerId() {
    return ownerId;
  }

  public PartsEnum getTenantId() {
    return tenantId;
  }

  @Override
  public void validate(){
    Validator.validateEnum(PartsEnum.class, ownerId, "Owner ID");
    Validator.validateEnum(PartsEnum.class, tenantId, "Tenant ID");
  }
}
