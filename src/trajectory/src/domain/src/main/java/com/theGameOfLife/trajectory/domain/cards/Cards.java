package com.theGameOfLife.trajectory.domain.cards;

import com.theGameOfLife.trajectory.domain.cards.entities.Consequence;
import com.theGameOfLife.trajectory.domain.cards.entities.Decision;
import com.theGameOfLife.trajectory.domain.cards.entities.Reward;
import com.theGameOfLife.trajectory.domain.cards.entities.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.entities.StatedEvent;



public class Cards extends AggregateRoot<CardId>{

    private Consequence consequence;
    private Decision decision;
    private Reward reward;
    private TypeEvent type;
    private StatedEvent state;

    public Cards(){
        super(new CardId());
    }

    private Cards(CardId Identity){
        super(Identity);
    }

    getConsequence(){
        return consequence;
    }

    getDecision(){
        return decision;
    }

    getReward(){
        return reward;
    }

    getType(){
        return type;
    }

    getState(){
        return state;
    }

    setConsequence(Consequence consequence){
        this.consequence = consequence;
    }

    setDecision(Decision decision){
        this.decision = decision;
    }

    setReward(Reward reward){
        this.reward = reward;
    }

    setType(TypeEvent type){
        this.type = type;
    }

    setState(StatedEvent state){
        this.state = state;
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