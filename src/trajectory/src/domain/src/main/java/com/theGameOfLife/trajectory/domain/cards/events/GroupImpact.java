package com.theGameOfLife.trajectory.domain.cards.events;
import com.theGameOfLife.trajectory.domain.cards.values.StateEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.values.ValueConsequence;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class GroupImpact extends DomainEvent{
    private final TypeEvent type;
    private final StateEvent state;
    private final ValueConsequence value;

    public GroupImpact(TypeEvent type, StateEvent state, ValueConsequence value){
        super(this.NameEventsListEnum.Group_Impact.name());
        this.type = type;
        this.state = state;
        this.value = value;
    }

    public TypeEvent getType() {
        return type;
    }

    public StateEvent getState() {
        return state;
    }

    public ValueConsequence getValue() {
        return value;
    }
}