package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class PropertyTypeChanged extends DomainEvent {

  public PropertyTypeChanged() {
    super(EventsEnum.PROPERTY_TYPE_CHANGED.name() );
  }
}
