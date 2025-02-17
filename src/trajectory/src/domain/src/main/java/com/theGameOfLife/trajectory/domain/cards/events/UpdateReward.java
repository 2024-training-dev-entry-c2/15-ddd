package com.theGameOfLife.trajectory.domain.cards.events;

public class UpdateReward extends DomainEvent{
    private final String id;
    private final NameReward nameReward;
    private final DescriptionReward descriptionReward;
    private final StatedEvent state;
    private final TypeEvent type;
    private final QuantityReward quantityReward;

    public UpdateReward(String id, NameReward nameReward, DescriptionReward descriptionReward, StatedEvent state, TypeEvent type, QuantityReward quantityReward){
        super(this.NameEventsListEnum.Update_Reward.name());
        this.id = id;
        this.nameReward = nameReward;
        this.descriptionReward = descriptionReward;
        this.state = state;
        this.type = type;
        this.quantityReward = quantityReward;
    }

    public String getId() {
        return id;
    }

    public NameReward getNameReward() {
        return nameReward;
    }

    public DescriptionReward getDescriptionReward() {
        return descriptionReward;
    }

    public StatedEvent getState() {
        return state;
    }

    public TypeEvent getType() {
        return type;
    }

    public QuantityReward getQuantityReward() {
        return quantityReward;
    }
}