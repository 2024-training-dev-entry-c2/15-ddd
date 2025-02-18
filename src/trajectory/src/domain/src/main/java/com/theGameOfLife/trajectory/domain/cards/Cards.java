package com.theGameOfLife.trajectory.domain.cards;

import com.theGameOfLife.trajectory.domain.cards.entities.Consequence;
import com.theGameOfLife.trajectory.domain.cards.entities.Decision;
import com.theGameOfLife.trajectory.domain.cards.entities.Reward;
import com.theGameOfLife.trajectory.domain.cards.entities.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.entities.StatedEvent;
import java.util.ArrayList;
import java.util.List;

public class Cards extends AggregateRoot<CardId>{

    private Consequence consequence;
    private Decision decision;
    private List<Reward> rewards = new ArrayList<>();
    private TypeEvent type;
    private StatedEvent state;

    public Cards() {
        super(new CardId());
        subscribe(new CardsHandler(this));
        apply(new CancelledEvent(id, type, state));
        apply(new GroupImpact(type, state));
        apply(new IsRewarded(id, nameReward, descriptionReward, state, type, value));
        apply(new TakenDecision(id, nameDecision, descriptionDecision, state, type, value));
    }

    private Cards(CardsId Identity){
        super(Identity);
        subscribe(new CardsHandler(this));
    }

    public Consequence getConsequence() {
        return consequence;
    }

    public void setConsequence(Consequence consequence) {
        this.consequence = consequence;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public TypeEvent getType() {
        return type;
    }

    public void setType(TypeEvent type) {
        this.type = type;
    }

    public StatedEvent getState() {
        return state;
    }

    public void setState(StatedEvent state) {
        this.state = state;
    }

    public void validateStateOfCard(String id) {
        if (id.equals(this.id.getId()) && this.state.getState() == StatedEvent.COMPLETED) {
            thorw new IllegalArgumentException("Estado de la tarjeta no puede ser COMPLETADO");
        }
    }

































    //region actions
    public void canceledEvent(String id, TypeEvent type, StateEvent state){
        apply(new CancelledEvent(id, type, state));
    }

    public void eventOccurred(String id, TypeEvent type){
        apply(new EventOccurred(id, type));
    }

    public void groupImpact(TypeEvent type, StateEvent state, ValueConsequence value){
        apply(new GroupImpact(type, state, value));
    }

    public void individualImpact(TypeEvent type, StateEvent state, ValueConsequence value){
        apply(new IndividualImpact(type, state, value));
    }

    public void isRewarded(String id, NameReward nameReward, DescriptionReward descriptionReward, StatedEvent state, TypeEvent type, ValueConsequence value){
        apply(new IsRewarded(id, nameReward, descriptionReward, state, type, value));
    }

    public void PostponedEvent(String id, StateEvent state, TypeEvent type){
        apply(new PostponedEvent(id, state, type));
    }

    public void takenDecision(String id, NameDecision nameDecision, DescriptionDecision descriptionDecision, StatedEvent state, TypeEvent type, ValueDecision value){
        apply(new TakenDecision(id, nameDecision, descriptionDecision, state, type, value));
    }

    public void updateReward(String id, NameReward nameReward, DescriptionReward descriptionReward, StatedEvent state, TypeEvent type, QuantityReward quantityReward){
        apply(new UpdateReward(id, nameReward, descriptionReward, state, type, quantityReward));
    }
    //endregion


}