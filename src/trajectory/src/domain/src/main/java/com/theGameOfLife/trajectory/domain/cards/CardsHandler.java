package com.theGameOfLife.trajectory.domain.cards;
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
import com.theGameOfLife.trajectory.domain.player.values.Properties;


puclic class CardsHandler extends DomainActionHandler{
    public CardsHandler(Cards card){
        add(cancelledEvent(card));
        add(groupImpact(card));
        add(isRewarded(card));
        add(takenDecision(card));
    }

    public Consumer<? extends DomainEvent> isRewarded(Cards card) {
        return (IsRewarded event) -> {
            validateStateOfCard(event.getId());
            if (card.getState() == StatedEvent.ACTIVE) {
                Reward reward = new Reward(event.getNameReward(), event.getDescriptionReward(), event.getValue());
                card.setReward(reward);
                card.setState(StatedEvent.COMPLETED);
            } else {
                throw new IllegalArgumentException("La tarjeta no está activa, no se puede asignar recompensa.");
            }
        };
    }

    public Consumer<? extends DomainEvent> cancelledEvent(Cards card) {
        return (CancelledEvent event) -> {
            validateStateOfCard(id);
            if (card.getId().card.getState().equals(StatedEvent.ACTIVE)) {
                this.cards.setState(StatedEvent.CANCELLED);
            } else {
                System.out.println("La tarjeta ya no está activa, no se puede cancelar.");
            }
        };
    }

    public Consumer<? extends DomainEvent> groupImpact(Cards card) {
        return (GroupImpact event) -> {
            validateStateOfCard();
        }
            if (event.getState() == StateEvent.ACTIVE) {
                applyImpactToGroup(event);
                card.setState(StatedEvent.COMPLETED);
        };
    }

    public Consumer<? extends DomainEvent> takenDecision(Cards card) {
        return (TakenDecision event) -> {
            if (card.getState() == StatedEvent.ACTIVE) {
                Decision decision = new Decision(
                        event.getNameDecision(),
                        event.getDescriptionDecision(),
                        event.getValue()
                );
                card.setDecision(decision);
                card.setState(StatedEvent.COMPLETED);
                card.setType(TypeEvent.DECISION);
        };
    }


}
