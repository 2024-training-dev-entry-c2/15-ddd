package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class WeaponIdentified extends DomainEvent {

  private String incidentId;
  private String weaponName;

  public WeaponIdentified(){
    super(EventsEnum.WEAPON_IDENTIFED.name());
  }

  public WeaponIdentified(String incidentId, String weaponName) {
    super(EventsEnum.WEAPON_IDENTIFED.name());
    this.incidentId = incidentId;
    this.weaponName = weaponName;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getWeaponName() {
    return weaponName;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }

  public void setWeaponName(String weaponName) {
    this.weaponName = weaponName;
  }
}
