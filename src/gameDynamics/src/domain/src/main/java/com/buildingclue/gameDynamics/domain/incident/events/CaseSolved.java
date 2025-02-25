package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class CaseSolved extends DomainEvent {

  private String incidentId;

  public CaseSolved(){
    super(EventsEnum.CASE_SOLVED.name());
  }

  public CaseSolved(String incidentId) {
    super(EventsEnum.CASE_SOLVED.name());
    this.incidentId = incidentId;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }
}
