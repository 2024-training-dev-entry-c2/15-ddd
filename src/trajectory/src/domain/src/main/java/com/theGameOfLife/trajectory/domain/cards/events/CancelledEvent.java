package com.theGameOfLife.trajectory.domain.cards.events;
import com.theGameOfLife.trajectory.domain.cards.values.StateEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class CancelledEvent extends DomainEvent{

    private final String id;
    private final TypeEvent type;
    private final StateEvent state;

    public CancelledEvent(String id, TypeEvent type, StateEvent state){
        super(this.NameEventsListEnum.Cancelled_Event.name());
        this.id = id;
        this.type = type;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public TypeEvent getType() {
        return type;
    }

    public StateEvent getState() {
        return state;
    }
}