package com.theGameOfLife.trajectory.domain.player.events;
import com.theGameOfLife.trajectory.domain.player.values.Children;
import com.theGameOfLife.trajectory.domain.player.values.Coupe;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class UpdateFamily extends DomainEvent{
    private final Children children;
    private final Coupe coupe;

    public UpdateFamily(Children children, Coupe coupe){
        super(this.EventsNameEnum.Update_Family.name());
        this.children = children;
        this.coupe = coupe;
    }

    public Children getChildren() {
        return children;
    }

    public Coupe getCoupe() {
        return coupe;
    }
}