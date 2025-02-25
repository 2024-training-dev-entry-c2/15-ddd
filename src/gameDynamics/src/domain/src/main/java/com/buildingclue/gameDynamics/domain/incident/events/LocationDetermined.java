package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class LocationDetermined extends DomainEvent {

  private String incidentId;
  private String locationName;

  public LocationDetermined(){
    super(EventsEnum.LOCATION_DETERMINED.name());
  }

  public LocationDetermined(String incidentId, String locationName) {
    super(EventsEnum.LOCATION_DETERMINED.name());
    this.incidentId = incidentId;
    this.locationName = locationName;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }
}
