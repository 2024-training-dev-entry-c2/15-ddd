package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities;

import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Name;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.PlayerId;
import com.buildingblocks.shared.domain.generic.Entity;

public class Player extends Entity<PlayerId> {
    private Name name;

    public Player( Name name) {
        super(new PlayerId());
        this.name = name;
    }

    public Player(PlayerId identity, Name name) {
        super(identity);
        this.name = name;
    }

    public boolean isDead(){
        if (name.getName().contains("(dead)")){
            return true;
        }
        return false;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
