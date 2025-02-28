package com.twilightimperium.expansioncommand.domain.faction.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;

public class UnitCreated extends DomainEvent {
    private final String type;
    private final Integer combatPower;
    private final Integer movement;
    private final Integer capacity;
    private final Integer cost;

    public UnitCreated(String type, Integer combatPower, Integer movement, Integer capacity, Integer cost) {
        super(EventsEnum.UNIT_CREATED.name());
        this.type = type;
        this.combatPower = combatPower;
        this.movement = movement;
        this.capacity = capacity;
        this.cost = cost;
    }

    public Integer getCombatPower() {
        return combatPower;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public Integer getMovement() {
        return movement;
    }
}
