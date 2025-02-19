package com.theGameOfLife.trajectory.domain.player.events;
import com.theGameOfLife.trajectory.domain.player.values.Diner;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class MakeyMoney extends DomainEvent{

    private final Diner amount;
    private final Salary salary;

    public MakeyMoney(Diner amount, Salary salary){
        super(this.EventsNameEnum.Makey_Money.name());
        this.amount = amount;
        this.salary = salary;
    }

    public Diner getAmount() {
        return amount;
    }

    public Salary getSalary() {
        return salary;
    }

}