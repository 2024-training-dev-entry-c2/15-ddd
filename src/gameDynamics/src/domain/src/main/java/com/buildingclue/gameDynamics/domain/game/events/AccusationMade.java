package com.buildingclue.gameDynamics.domain.game.events;
import com.buildingclue.shared.domain.generic.DomainEvent;

public class AccusationMade extends DomainEvent{

  private String playerId;
  private String accusedSuspect;
  private String accusedWeapon;
  private String accusedLocation;

  public AccusationMade() {
    super(EventsEnum.ACCUSATION_MADE.name());
  }

  public AccusationMade(String playerId, String accusedSuspect, String accusedWeapon, String accusedLocation) {
    super(EventsEnum.ACCUSATION_MADE.name());
    this.playerId = playerId;
    this.accusedSuspect = accusedSuspect;
    this.accusedWeapon = accusedWeapon;
    this.accusedLocation = accusedLocation;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getAccusedSuspect() {
    return accusedSuspect;
  }

  public String getAccusedWeapon() {
    return accusedWeapon;
  }

  public String getAccusedLocation() {
    return accusedLocation;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setAccusedSuspect(String accusedSuspect) {
    this.accusedSuspect = accusedSuspect;
  }

  public void setAccusedWeapon(String accusedWeapon) {
    this.accusedWeapon = accusedWeapon;
  }

  public void setAccusedLocation(String accusedLocation) {
    this.accusedLocation = accusedLocation;
  }
}
