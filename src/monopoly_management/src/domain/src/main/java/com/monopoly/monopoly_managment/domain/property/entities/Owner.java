package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.monopoly_managment.domain.property.values.Alias;
import com.monopoly.monopoly_managment.domain.property.values.ColorGroup;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Portfolio;
import com.monopoly.monopoly_managment.domain.property.values.PropertyId;
import com.monopoly.monopoly_managment.domain.property.values.Token;
import com.monopoly.monopoly_managment.domain.property.values.Wealth;
import com.monopoly.shared.domain.generic.Entity;

import java.util.stream.Stream;

public class Owner extends Entity<OwnerId> {
  private Alias alias;
  private Token token;
  private Portfolio portfolio;
  private Wealth wealth;

  public Owner(OwnerId identity, Alias alias, Token token, Portfolio portfolio, Wealth wealth) {
    super(identity);
    this.alias = alias;
    this.token = token;
    this.portfolio = portfolio;
    this.wealth = wealth;
  }

  public Owner(OwnerId identity) {
    super(identity);
  }

  public Owner(Alias alias, Token token, Portfolio portfolio, Wealth wealth) {
    super(new OwnerId());
    this.alias = alias;
    this.token = token;
    this.portfolio = portfolio;
    this.wealth = wealth;
  }

  public Alias getAlias() {
    return alias;
  }

  public void setAlias(Alias alias) {
    this.alias = alias;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  public Wealth getWealth() {
    return wealth;
  }

  public void setWealth(Wealth wealth) {
    this.wealth = wealth;
  }

  public void acquireProperty(PropertyId propertyId){

  this.portfolio = Portfolio.of(Stream.concat(portfolio.getPropertiesIds().stream(), Stream.of(propertyId.getValue())).toList());
  }

  public void sellProperty(PropertyId propertyId){
    validatePropertyOwnership(propertyId);
    this.portfolio = Portfolio.of(portfolio.getPropertiesIds().stream().filter(id -> !id.equals(propertyId.getValue())).toList());
  }

  public void transferProperty(PropertyId propertyId, Owner newOwner){
    validatePropertyOwnership(propertyId);
    this.portfolio = Portfolio.of(portfolio.getPropertiesIds().stream().filter(id -> !id.equals(propertyId.getValue())).toList());
    newOwner.acquireProperty(propertyId);
  }

  public Wealth calculateTotalWealth() {
  return Wealth.of(wealth.getBalance() + portfolio.getPropertiesIds().stream().map(PropertyId::of).map(this::getPropertyValue).reduce(0.0, Double::sum), portfolio.getPropertiesIds());
  }

  public Boolean validateMonopoly(ColorGroup group) {
    return group.getValue().equals("BROWN");
  }

  public Boolean validateFullMonopoly(ColorGroup group) {
    return false;
  }

  private void validatePropertyOwnership(PropertyId property) {
    if (!portfolio.getPropertiesIds().contains(property.getValue())) {
      throw new IllegalStateException("Owner does not own this property");
    }
  }
  private void validatePropertyNotOwned(PropertyId property) {
    if (portfolio.getPropertiesIds().contains(property.getValue())) {
      throw new IllegalStateException("Property already owned");
    }
  }

  private Double getPropertyValue(PropertyId propertyId) {
    return 4.0;
  }

  private Integer getRealGroupSize(String group){
    return 3;
  }

}
