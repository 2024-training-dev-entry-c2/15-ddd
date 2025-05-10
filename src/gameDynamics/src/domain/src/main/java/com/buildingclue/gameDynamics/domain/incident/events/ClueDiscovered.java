package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class ClueDiscovered extends DomainEvent {

  private String incidentId;
  private String clue;

  public ClueDiscovered(){
    super(EventsEnum.CLUE_DISCOVERED.name());
  }

  public ClueDiscovered(String incidentId, String clue) {
    super(EventsEnum.CLUE_DISCOVERED.name());
    this.incidentId = incidentId;
    this.clue = clue;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getClue() {
    return clue;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  public void setClue(String clue) {
    this.clue = clue;
  }
}
