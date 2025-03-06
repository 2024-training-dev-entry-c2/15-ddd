package com.monopoly.monopoly_managment.application.property.shared;

import com.monopoly.monopoly_managment.domain.property.values.TypeContrat;
import com.monopoly.monopoly_managment.domain.property.values.TypeImprovement;

import java.util.List;

public class PropertyResponse {
  private final String propertyID;
  private final Upgrade upgrade;
  private final Contract contract;
  private final Mortgage mortgage;
  private final String name;
  private final Double price;
  private final String colorGroup;

  public PropertyResponse(String propertyID, Upgrade upgrade, Contract contract, Mortgage mortgage, String name, Double price, String colorGroup) {
    this.propertyID = propertyID;
    this.upgrade = upgrade;
    this.contract = contract;
    this.mortgage = mortgage;

    this.name = name;
    this.price = price;
    this.colorGroup = colorGroup;
  }

  public String getPropertyID() {
    return propertyID;
  }

  public Upgrade getUpgrade() {
    return upgrade;
  }

  public Contract getContract() {
    return contract;
  }

  public Mortgage getMortgage() {
    return mortgage;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public String getColorGroup() {
    return colorGroup;
  }

  public static class Upgrade {
    private final TypeImprovement typeImprovement;
    private final Integer developmentLevel;
    private final String propertyId;
    private final Double cost;

    public Upgrade(TypeImprovement typeImprovement, Integer developmentLevel, String propertyId, Double cost) {
      this.typeImprovement = typeImprovement;
      this.developmentLevel = developmentLevel;
      this.propertyId = propertyId;
      this.cost = cost;
    }

    public Integer getDevelopmentLevel() {
      return developmentLevel;
    }

    public String getPropertyId() {
      return propertyId;
    }

    public Double getCost() {
      return cost;
    }
  }
  public static class Contract{
    private final TypeContrat type;
    private final Double rate;
    private final Parties parties;
    private final Boolean isActive;

    public Contract(TypeContrat type, Double rate, Parties parties, Boolean isActive) {
      this.type = type;
      this.rate = rate;
      this.parties = parties;
      this.isActive = isActive;
    }

    public TypeContrat getType() {
      return type;
    }

    public Double getRate() {
      return rate;
    }

    public Parties getParties() {
      return parties;
    }

    public Boolean getActive() {
      return isActive;
    }
  }

  public static class Parties{
    private final String ownerId;
    private final List<String> tenantId;

    public Parties(String ownerId, List<String> tenantId) {
      this.ownerId = ownerId;
      this.tenantId = tenantId;
    }

    public String getOwnerId() {
      return ownerId;
    }

    public List<String> getTenantId() {
      return tenantId;
    }
  }

  public static class Mortgage{
    private final Double value;

    private final Double cancellationCost;

    public Mortgage(Double value, Boolean isMortgaged, Double cancellationCost) {
      this.value = value;

      this.cancellationCost = cancellationCost;
    }

    public Double getValue() {
      return value;
    }


    public Double getCancellationCost() {
      return cancellationCost;
    }
  }

  public static class Owner{
    private final String alias;
    private final String token;
    private final List<String> portfolio;
    private final Double wealth;

    public Owner(String alias, String token, List<String> portfolio, Double wealth) {
      this.alias = alias;
      this.token = token;
      this.portfolio = portfolio;
      this.wealth = wealth;
    }

    public String getAlias() {
      return alias;
    }

    public String getToken() {
      return token;
    }

    public List<String> getPortfolio() {
      return portfolio;
    }

    public Double getWealth() {
      return wealth;
    }
  }
}
