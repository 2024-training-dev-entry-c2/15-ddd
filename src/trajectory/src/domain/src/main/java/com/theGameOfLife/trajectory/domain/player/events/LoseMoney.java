package com.theGameOfLife.trajectory.domain.player.events;
import com.theGameOfLife.trajectory.domain.player.values.Diner;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class LoseMoney extends DomainEvent{

    private final Diner amount;

    public LoseMoney(Diner amount){
        super(this.EventsNameEnum.Lose_Money.name());
        this.amount = amount;
    }

    public Diner getAmount() {
        return amount;
    }

}