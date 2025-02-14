package com.monopoly.monopoly_managment.domain.property.events;

import com.monopoly.monopoly_managment.domain.property.values.TypeContratEnum;
import com.monopoly.shared.domain.generic.DomainEvent;

public class ContractSigned extends DomainEvent {
  private final String contractId;
  private final String propertyId;
  private final String ownerId;
  private final TypeContratEnum contractType;
  private final Double rate;
  private final String tenantId;

  public ContractSigned(String contractId, String propertyId, String ownerId, TypeContratEnum contractType, Double rate, String tenantId) {
    super(EventsEnum.CONTRACT_SIGNED.name() );
    this.contractId = contractId;
    this.propertyId = propertyId;
    this.ownerId = ownerId;
    this.contractType = contractType;
    this.rate = rate;
    this.tenantId = tenantId;
  }

  public String getContractId() {
    return contractId;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public TypeContratEnum getContractType() {
    return contractType;
  }

  public Double getRate() {
    return rate;
  }

  public String getTenantId() {
    return tenantId;
  }
}
