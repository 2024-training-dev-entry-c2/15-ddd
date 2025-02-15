package com.monopoly.monopoly_managment.domain.property.entities;

import com.monopoly.monopoly_managment.domain.property.values.Alias;
import com.monopoly.monopoly_managment.domain.property.values.OwnerId;
import com.monopoly.monopoly_managment.domain.property.values.Portfolio;
import com.monopoly.monopoly_managment.domain.property.values.Token;
import com.monopoly.monopoly_managment.domain.property.values.Wealth;
import com.monopoly.shared.domain.generic.Entity;

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

  public Owner( Alias alias, Token token, Portfolio portfolio, Wealth wealth) {
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
}
