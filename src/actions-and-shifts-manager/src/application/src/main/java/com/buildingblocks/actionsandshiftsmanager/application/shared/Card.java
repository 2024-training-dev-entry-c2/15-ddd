package com.buildingblocks.actionsandshiftsmanager.application.shared;

public class Card {
    private String id;
    private String name;
    private String type;
    private String action;

    public Card(String name, String type, String action) {
        this.name = name;
        this.type = type;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
