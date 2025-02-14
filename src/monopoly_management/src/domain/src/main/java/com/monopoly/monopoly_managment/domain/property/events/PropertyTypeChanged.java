package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class PropertyTypeChanged extends DomainEvent {
  private final String propertyId;
  private final Integer improvementLevel;

  public PropertyTypeChanged(String propertyId, Integer improvementLevel) {
    super(EventsEnum.PROPERTY_TYPE_CHANGED.name() );
    this.propertyId = propertyId;
    this.improvementLevel = improvementLevel;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public Integer getImprovementLevel() {
    return improvementLevel;
  }
}
