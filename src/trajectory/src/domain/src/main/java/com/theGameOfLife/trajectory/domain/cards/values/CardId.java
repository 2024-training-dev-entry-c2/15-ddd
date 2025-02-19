package com.theGameOfLife.shared.domain.generic;
import com.theGameOfLife.trajectory.domain.cards.values.Identity;

public class CardId extends Identity{
    public CardId(){
        super();
    }

    private CardId(String id){
        super(id);
    }

    public static CardId of(String id){
        return new CardId(id);
    }
}
