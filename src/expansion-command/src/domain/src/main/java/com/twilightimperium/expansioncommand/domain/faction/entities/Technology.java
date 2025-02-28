package com.twilightimperium.expansioncommand.domain.faction.entities;

import com.twilightimperium.expansioncommand.domain.faction.values.Level;
import com.twilightimperium.expansioncommand.domain.faction.values.Name;
import com.twilightimperium.expansioncommand.domain.faction.values.TechnologyId;
import com.twilightimperium.shared.domain.generic.Entity;

public class Technology extends Entity<TechnologyId> {
    private Name name;
    private Level level;

    // region Constructors
    public Technology(Name name, Level level) {
        super(new TechnologyId());
        this.name = name;
        this.level = level;
    }

    public Technology(TechnologyId identity, Name name, Level level) {
        super(identity);
        this.name = name;
        this.level = level;
    }
    // endregion

    // region Getters and Setters
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
    // endregion

    // region Public Methods
    public void increaseLevel(Integer amount) {
        this.level = Level.of(this.level.getValue() + amount);
    }
    // endregion
}
