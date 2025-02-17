package com.theGameOfLife.trajectory.domain.player.events;
import com.theGameOfLife.trajectory.domain.player.values.StatePlayerEnum;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class StateChange extends DomainEvent{

    pirvate final StatePlayerEnum state;

    public StateChange(StatePlayerEnum state){
        super(this.EventsNameEnum.State_Change.name());
        this.state = state;
    }

    public StatePlayerEnum getState() {
        return state;
    }

}