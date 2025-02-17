package com.theGameOfLife.trajectory.domain.cards.events;
import com.theGameOfLife.trajectory.domain.cards.values.DescriptionReward;
import com.theGameOfLife.trajectory.domain.cards.values.NameReward;
import com.theGameOfLife.trajectory.domain.cards.values.StatedEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.values.ValueConsequence;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class IsRewarded extends DomainEvent{

    private final String id;
    private final NameReward nameReward;
    private final DescriptionReward descriptionReward;
    private final StatedEvent state;
    private final TypeEvent type;
    private final ValueConsequence value;

    public IsRewarded(String id, NameReward nameReward, DescriptionReward descriptionReward, StatedEvent state, TypeEvent type, ValueConsequence value){
        super(this.NameEventsListEnum.Is_Rewarded.name());
        this.id = id;
        this.nameReward = nameReward;
        this.descriptionReward = descriptionReward;
        this.state = state;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

}