package com.theGameOfLife.trajectory.domain.cards.events;
import com.theGameOfLife.trajectory.domain.cards.values.StateEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class PostponedEvent extends DomainEvent{

    private final String id;
    private final StateEvent state;
    private final TypeEvent type;

    public PostponedEvent(String id, StateEvent state, TypeEvent type){
        super(this.NameEventsListEnum.Postponed_Event.name());
        this.id = id;
        this.state = state;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public StateEvent getState() {
        return state;
    }

    public TypeEvent getType() {
        return type;
    }

}