package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

import java.util.List;

public class MonopolyAcquired extends DomainEvent {
  private final String ownerId;
  private final List<String> propertyIds;
  private final String groupColor;


  public MonopolyAcquired(String ownerId, List<String> propertyIds, String groupColor) {
    super(EventsEnum.MONOPOLY_ACQUIRED.name() );
    this.ownerId = ownerId;
    this.propertyIds = propertyIds;
    this.groupColor = groupColor;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public List<String> getPropertyIds() {
    return propertyIds;
  }

  public String getGroupColor() {
    return groupColor;
  }
}
