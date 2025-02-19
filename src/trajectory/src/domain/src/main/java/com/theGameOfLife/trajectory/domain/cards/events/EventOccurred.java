package com.theGameOfLife.trajectory.domain.cards.events;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class EventOccurred extends DomainEvent{
    private final String id;
    private final TypeEvent type;

    public EventOccurred(String id, TypeEvent type){
        super(this.NameEventsListEnum.Event_Occurred.name());
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public TypeEvent getType() {
        return type;
    }
}