package com.theGameOfLife.trajectory.domain.cards.events;
import com.theGameOfLife.trajectory.domain.cards.values.DescriptionDecision;
import com.theGameOfLife.trajectory.domain.cards.values.NameDecision;
import com.theGameOfLife.trajectory.domain.cards.values.StatedEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.values.ValueDecision;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class TakenDecision extends DomainEvent{

    private final String id;
    private final NameDecision nameDecision;
    private final DescriptionDecision descriptionDecision;
    private final StatedEvent state;
    private final TypeEvent type;
    private final ValueDecision value;

    public TakenDecision(String id, NameDecision nameDecision, DescriptionDecision descriptionDecision, StatedEvent state, TypeEvent type, ValueDecision value){
        super(this.NameEventsListEnum.Taken_Decision.name());
        this.id = id;
        this.nameDecision = nameDecision;
        this.descriptionDecision = descriptionDecision;
        this.state = state;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public NameDecision getNameDecision() {
        return nameDecision;
    }

    public DescriptionDecision getDescriptionDecision() {
        return descriptionDecision;
    }

    public StatedEvent getState() {
        return state;
    }

    public TypeEvent getType() {
        return type;
    }

    public ValueDecision getValue() {
        return value;
    }


}