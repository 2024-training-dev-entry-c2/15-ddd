package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class SuspectIdentified extends DomainEvent {

  private String incidentId;
  private String suspectName;

  public SuspectIdentified(){
    super(EventsEnum.SUSPECT_IDENTIFED.name());
  }

  public SuspectIdentified(String incidentId, String suspectName) {
    super(EventsEnum.SUSPECT_IDENTIFED.name());
    this.incidentId = incidentId;
    this.suspectName = suspectName;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getSuspectName() {
    return suspectName;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  public void setSuspectName(String suspectName) {
    this.suspectName = suspectName;
  }
}
