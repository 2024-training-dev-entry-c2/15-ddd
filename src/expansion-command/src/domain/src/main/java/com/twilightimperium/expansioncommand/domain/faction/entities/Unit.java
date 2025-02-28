package com.twilightimperium.expansioncommand.domain.faction.entities;

import com.twilightimperium.expansioncommand.domain.faction.values.CombatPower;
import com.twilightimperium.expansioncommand.domain.faction.values.Capacity;
import com.twilightimperium.expansioncommand.domain.faction.values.Cost;
import com.twilightimperium.expansioncommand.domain.faction.values.Movement;
import com.twilightimperium.expansioncommand.domain.faction.values.Type;
import com.twilightimperium.expansioncommand.domain.faction.values.UnitId;
import com.twilightimperium.shared.domain.generic.Entity;

public class Unit extends Entity<UnitId> {
    private Type type;
    private CombatPower combatPower;
    private Movement movement;
    private Capacity capacity;
    private Cost cost;

    // region Constructors
    public Unit(CombatPower combatPower, Capacity capacity, Cost cost, Type type, Movement movement) {
        super(new UnitId());
        this.combatPower = combatPower;
        this.capacity = capacity;
        this.cost = cost;
        this.type = type;
        this.movement = movement;
    }

    public Unit(UnitId identity, CombatPower combatPower, Capacity capacity, Cost cost, Type type, Movement movement) {
        super(identity);
        this.combatPower = combatPower;
        this.capacity = capacity;
        this.cost = cost;
        this.type = type;
        this.movement = movement;
    }
    // endregion

    // region Getters and Setters
    public CombatPower getAttackPower() {
        return combatPower;
    }

    public void setAttackPower(CombatPower combatPower) {
        this.combatPower = combatPower;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
    // endregion

    // region Public Methods
    public void increaseCapacity() {
        this.capacity = Capacity.of(this.capacity.getValue() + 2);
    }

    public void reduceCost(int amount) {
        int newCostValue = Math.max(1, this.cost.getValue() - amount);
        this.cost = Cost.of(newCostValue);
    }
    // endregion

}
