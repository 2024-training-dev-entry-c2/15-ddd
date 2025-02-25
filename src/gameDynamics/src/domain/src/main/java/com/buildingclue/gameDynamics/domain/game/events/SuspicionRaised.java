package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class SuspicionRaised extends DomainEvent {

  private String playerId;
  private String suspect;
  private String weapon;
  private String location;

  public SuspicionRaised(){
    super(EventsEnum.SUSPICION_RAISED.name());
  }

  public SuspicionRaised(String playerId, String suspect, String weapon, String location) {
    super(EventsEnum.SUSPICION_RAISED.name());
    this.playerId = playerId;
    this.suspect = suspect;
    this.weapon = weapon;
    this.location = location;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getSuspect() {
    return suspect;
  }

  public String getWeapon() {
    return weapon;
  }

  public String getLocation() {
    return location;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setSuspect(String suspect) {
    this.suspect = suspect;
  }

  public void setWeapon(String weapon) {
    this.weapon = weapon;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
