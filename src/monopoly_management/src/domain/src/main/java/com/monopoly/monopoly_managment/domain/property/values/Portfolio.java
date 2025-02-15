package com.monopoly.monopoly_managment.domain.property.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

import java.util.List;

public class Portfolio implements IValueObject {
  private final List<String> propertiesIds;

  private Portfolio(List<String> propertiesIds) {
    this.propertiesIds = propertiesIds;
    validate();
  }

  public static Portfolio of(List<String> propertiesIds) {
    return new Portfolio(propertiesIds);
  }

  public List<String> getPropertiesIds() {
    return propertiesIds;
  }

  @Override
  public void validate() {
    this.propertiesIds.forEach(propertyId -> Validator.validateUUID(propertyId, "Property ID"));
  }
}
