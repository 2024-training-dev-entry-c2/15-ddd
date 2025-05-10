package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class InvestigationStarted extends DomainEvent {

  private String incidentId;

  public InvestigationStarted(){
    super(EventsEnum.INVESTIGATION_STARTED.name());
  }

  public InvestigationStarted(String incidentId) {
    super(EventsEnum.INVESTIGATION_STARTED.name());
    this.incidentId = incidentId;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }
}
