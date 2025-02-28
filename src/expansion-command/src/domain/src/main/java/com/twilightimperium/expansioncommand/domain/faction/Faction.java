package com.twilightimperium.expansioncommand.domain.faction;

import com.twilightimperium.expansioncommand.domain.faction.entities.ConqueredFaction;
import com.twilightimperium.expansioncommand.domain.faction.entities.Government;
import com.twilightimperium.expansioncommand.domain.faction.entities.Technology;
import com.twilightimperium.expansioncommand.domain.faction.entities.Unit;
import com.twilightimperium.expansioncommand.domain.faction.events.ConqueredFactionAdded;
import com.twilightimperium.expansioncommand.domain.faction.events.ConqueredFactionPercentageUpdated;
import com.twilightimperium.expansioncommand.domain.faction.events.FactionCreated;
import com.twilightimperium.expansioncommand.domain.faction.events.FactionSurrendered;
import com.twilightimperium.expansioncommand.domain.faction.events.GovernmentLevelIncreased;
import com.twilightimperium.expansioncommand.domain.faction.events.GovernmentTypeChanged;
import com.twilightimperium.expansioncommand.domain.faction.events.TechnologyAdded;
import com.twilightimperium.expansioncommand.domain.faction.events.TechnologyLevelIncreased;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitCapacityIncreased;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitCostReduced;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitCreated;
import com.twilightimperium.expansioncommand.domain.faction.events.UnitRemoved;
import com.twilightimperium.expansioncommand.domain.faction.values.Description;
import com.twilightimperium.expansioncommand.domain.faction.values.FactionId;
import com.twilightimperium.expansioncommand.domain.faction.values.IsSurrendered;
import com.twilightimperium.expansioncommand.domain.faction.values.Name;
import com.twilightimperium.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Faction extends AggregateRoot<FactionId> {
    private Name name;
    private Description description;
    private IsSurrendered isSurrendered;
    private List<Unit> unitsList;
    private List<Technology> technologiesList;
    private List<ConqueredFaction> conqueredFactionsList;
    private Government government;

    // region Constructors
    public Faction(String name, String description) {
        super(new FactionId());
        apply(new FactionCreated(name, description, false, "Default", 1));
        subscribe(new FactionHandler(this));
    }

    private Faction(FactionId identity) {
        super(identity);
    }
    // endregion


    public List<ConqueredFaction> getConqueredFactionsList() {
        return conqueredFactionsList;
    }

    public void setConqueredFactionsList(List<ConqueredFaction> conqueredFactionsList) {
        this.conqueredFactionsList = conqueredFactionsList;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public IsSurrendered getIsSurrendered() {
        return isSurrendered;
    }

    public void setIsSurrendered(IsSurrendered isSurrendered) {
        this.isSurrendered = isSurrendered;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Technology> getTechnologiesList() {
        return technologiesList;
    }

    public void setTechnologiesList(List<Technology> technologiesList) {
        this.technologiesList = technologiesList;
    }

    public List<Unit> getUnitsList() {
        return unitsList;
    }

    public void setUnitsList(List<Unit> unitsList) {
        this.unitsList = unitsList;
    }

    // region Domain Actions
    public void createUnit(String type, Integer combatPower, Integer movement, Integer capacity, Integer cost) {
        apply(new UnitCreated(type, combatPower, movement, capacity, cost));
    }

    public void removeUnit(String id) {
        apply(new UnitRemoved(id));
    }

    public void increaseUnitCapacity(String id, Integer capacity) {
        apply(new UnitCapacityIncreased(id, capacity));
    }

    public void reduceUnitCost(String id, Integer cost) {
        apply(new UnitCostReduced(id, cost));
    }

    public void addTechnology(String name, Integer level) {
        apply(new TechnologyAdded(name, level));
    }

    public void increaseTechnologyLevel(Integer level) {
        apply(new TechnologyLevelIncreased(level));
    }

    public void increaseGovernmentLevel(Integer level) {
        apply(new GovernmentLevelIncreased(level));
    }

    public void changeGovernmentType(String type, Integer level) {
        apply(new GovernmentTypeChanged(type, level));
    }

    public void addConqueredFaction(String name, Integer percentage) {
        apply(new ConqueredFactionAdded(name, percentage));
    }

    public void updateConqueredFactionPercentage(String id, Integer percentage) {
        apply(new ConqueredFactionPercentageUpdated(id, percentage));
    }

    public void surrender(Boolean isSurrendered) {
        apply(new FactionSurrendered(true));
    }
    // endregion
}
