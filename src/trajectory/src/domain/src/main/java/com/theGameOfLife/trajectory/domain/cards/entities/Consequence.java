package com.theGameOfLife.trajectory.domain.cards.entities;
import com.theGameOfLife.trajectory.domain.cards.values.DescriptionConsequence;
import com.theGameOfLife.trajectory.domain.cards.values.NameConsequence;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEvent;
import com.theGameOfLife.trajectory.domain.cards.values.TypeEventEnum;
import com.theGameOfLife.trajectory.domain.cards.values.ValueConsequence;
import com.theGameOfLife.shared.domain.generic.Identity;

public class Consequence extends Identity<CardId>{

    private NameConsequence nameConsequence;
    private DescriptionConsequence descriptionConsequence;
    private TypeEvent type;
    private ValueConsequence value;

    public Consequence(NameConsequence nameConsequence, DescriptionConsequence descriptionConsequence, TypeEvent type, ValueConsequence value){
        super(new CardId());
        this.nameConsequence = nameConsequence;
        this.descriptionConsequence = descriptionConsequence;
        this.type = type;
        this.value = value;
    }

    public Consequence(Identity id, NameConsequence nameConsequence, DescriptionConsequence descriptionConsequence, TypeEvent type, ValueConsequence value){
        super(id);
        this.nameConsequence = nameConsequence;
        this.descriptionConsequence = descriptionConsequence;
        this.type = type;
        this.value = value;
    }

    public NameConsequence cambiarTipoEvento() {
        type.getTypeEvent() == TypeEventEnum.GROUP_IMPACT ?
                type.setTypeEvent(TypeEventEnum.INDIVIDUAL_IMPACT) :
                type.setTypeEvent(TypeEventEnum.GROUP_IMPACT);
    }


    public  consecuenciaIndividual() {
        type.getTypeEvent() == TypeEventEnum.INDIVIDUAL_IMPACT ?
                value.setValue(value.getValue() - 50) :
                value.setValue(value.getValue() + 100);
    }





}