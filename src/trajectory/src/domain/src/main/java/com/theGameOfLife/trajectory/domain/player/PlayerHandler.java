package com.theGameOfLife.trajectory.domain.player;
import com.theGameOfLife.trajectory.domain.player.events.AcquiredProperty;
import com.theGameOfLife.trajectory.domain.player.events.LoseMoney;
import com.theGameOfLife.trajectory.domain.player.events.MakeyMoney;
import com.theGameOfLife.trajectory.domain.player.events.PropertySold;
import com.theGameOfLife.trajectory.domain.player.events.StateChange;
import com.theGameOfLife.trajectory.domain.player.events.UpdateFamily;
import com.theGameOfLife.trajectory.domain.player.values.Children;
import com.theGameOfLife.trajectory.domain.player.values.Coupe;
import com.theGameOfLife.trajectory.domain.player.values.Diner;
import com.theGameOfLife.shared.domain.generic.DomainActionHandler;
import com.theGameOfLife.shared.domain.generic.DomainEvent.subscribe;
import com.theGameOfLife.trajectory.domain.player.values.Properties;



puclic class PlayerHandler extends DomainActionHandler{
    public PlayerHandler(Player player){
        add(acquireProperty(player));
        add(loseMoney(player));
        add(makeMoney(player));
        add(propertySold(player));
        add(stateChange(player));
        add(updateFamily(player))
    }

    public Consumer<? extends DomainEvent> acquireProperty(Player player) {
        return (AcquiredProperty event) -> {
            Properties propiedad = new Properties(event.getAddress(), event.getValue(), event.getType());
            player.setProperty(propiedad);
        }
    }

    public Consumer<? extends DomainEvent> propertySold(Player player) {
        return (PropertySold event) -> {
            playerl.encontrarPropiedad(event.getId());
            player.getProperties().remove(propertyToRemove);
            player.setDiner(event.getValue());
        };
    };

    public Consumer<? extends DomainEvent> makeMoney(Player player) {
        return (MakeyMoney event) -> {
            player.validateAmount(event.getAmount());
            player.setDiner(event.getAmount());
        };
    }

    public Consumer<? extends DomainEvent> updateFamily(Player player) {
        return (UpdateFamily event) -> {
            Family integrante = new Family(event.getChildren(), event.getCoupe());
            player.setFamily(integrante);
        };
    }

    public Consumer<? extends DomainEvent> loseMoney(Player player) {
        return (LoseMoney event) -> {
            player.disminurDinero(event.getAmount());
            player.setDiner(event.getAmount());
        };
    }


        public Consumer<? extends DomainEvent> stateChange (Player player){
            return (StateChange event) -> {
                player.setState(event.getState());
            };
        };
}
