package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.shared.domain.generic.DomainEvent;

public class ContractFinalized extends DomainEvent {
  private final String contractId;
  private final String propertyId;
  private final String ownerId;

  public ContractFinalized(String contractId, String propertyId, String ownerId) {
    super(EventsEnum.CONTRACT_FINALIZED.name());
    this.contractId = contractId;
    this.propertyId = propertyId;
    this.ownerId = ownerId;
  }

  public String getContractId() {
    return contractId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPropertyId() {
    return propertyId;
  }
}
