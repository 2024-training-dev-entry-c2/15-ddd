package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class SuspectEliminated extends DomainEvent {

  private String incidentId;
  private String suspectName;

  public SuspectEliminated(){
    super(EventsEnum.SUSPECT_ELIMINATED.name());
  }

  public SuspectEliminated(String incidentId, String suspectName) {
    super(EventsEnum.SUSPECT_ELIMINATED.name());
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
