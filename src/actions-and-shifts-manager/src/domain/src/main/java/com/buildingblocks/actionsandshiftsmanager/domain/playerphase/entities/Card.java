package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities;

import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Action;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.CardId;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Name;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Type;
import com.buildingblocks.shared.domain.generic.Entity;

public class Card extends Entity<CardId> {
    private Name name;
    private Type type;
    private Action action;

    public Card( Name name, Type type, Action action) {
        super(new CardId());
        this.name = name;
        this.type = type;
        this.action = action;
    }

    public Card(CardId identity, Name name, Type type, Action action) {
        super(identity);
        this.name = name;
        this.type = type;
        this.action = action;
    }

    public static int cardPoints(String text) {
        text = String.format(text).toUpperCase();
        return switch (text) {
            case "ROJO" -> 1;
            case "AMARILLO" -> 2;
            case "AZUL" -> 3;
            case "NEGRO" -> 4;
            default -> 0;
        };
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
