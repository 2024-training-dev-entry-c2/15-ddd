package com.theGameOfLife.trajectory.domain.cards.entities;
import com.theGameOfLife.trajectory.domain.cards.values.DescriptionDecision;
import com.theGameOfLife.trajectory.domain.cards.values.NameDecision;
import com.theGameOfLife.trajectory.domain.cards.values.Question;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEventEnum;
import com.theGameOfLife.trajectory.domain.cards.values.ValueDecision;
import com.theGameOfLife.shared.domain.generic.Identity;

public class Decision extends Entity<CardId>{
    private NameDecision nameDecision;
    private DescriptionDecision descriptionDecision;
    private TypeEvent type;
    private ValueDecision value;
    private Question question;

    public Decision(NameDecision nameDecision, DescriptionDecision descriptionDecision, TypeEvent type, ValueDecision value, Question question){
        super(new CardId());
        this.nameDecision = nameDecision;
        this.descriptionDecision = descriptionDecision;
        this.type = type;
        this.value = value;
        this.question = question;
    }

    public Decision(Identity id, NameDecision nameDecision, DescriptionDecision descriptionDecision, TypeEvent type, ValueDecision value, Question question){
        super(id);
        this.nameDecision = nameDecision;
        this.descriptionDecision = descriptionDecision;
        this.type = type;
        this.value = value;
        this.question = question;
    }

    public decide() {
        question.getQuestion() == "¿Si o no?" ?
                type.setTypeEvent(TypeEventEnum.GROUP_IMPACT) :
                type.setTypeEvent(TypeEventEnum.INDIVIDUAL_IMPACT);
    }



}