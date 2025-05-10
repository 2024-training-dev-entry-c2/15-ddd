package com.buildingblocks.actionsandshiftsmanager.domain.playerphase;

import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities.Card;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities.Player;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.*;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.AmountPoints;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.PlayerPhaseId;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerPhase extends AggregateRoot<PlayerPhaseId> {
    private Player player;
    private List<Card> cardList = new ArrayList<>();
    private AmountPoints amountPoints;

    //region Contructors
    public PlayerPhase() {
        super(new PlayerPhaseId());
        subscribe(new PlayerPhaseHandler(this));
    }

    private PlayerPhase(PlayerPhaseId identity) {
        super(identity);
        subscribe(new PlayerPhaseHandler(this));
    }
    //endregion

    //region getters and setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public AmountPoints getAmountPoints() {
        return amountPoints;
    }

    public void setAmountPoints(AmountPoints amountPoints) {
        this.amountPoints = amountPoints;
    }
    //endregion

    //region Domain Actions
    public void assignedCard(String name, String type, String effect) {
        apply(new AssignedCard(name, type, effect));
    }

    public void assignedPlayer(String name) {
        apply(new AssignedPlayer(name));
    }

    public void calculatedPoints(int amountPoints) {
        apply(new CalculatedPoints(amountPoints));
    }

    public void cardDeleted(String id, String name, String type, String effect) {
        apply(new CardDeleted(id, name, type, effect));
    }

    public void modifiedPoints(String id, int amountPoints) {
        apply(new ModifiedPoints(id, amountPoints));
    }

    public void playerChanged(String id, String name) {
        apply(new PlayerChanged(id, name));
    }

    public static PlayerPhase from(final String identity, final List<DomainEvent> events) {
        PlayerPhase playerPhase = new PlayerPhase(PlayerPhaseId.of(identity));

        events.forEach(playerPhase::apply);
        return playerPhase;
    }

    //endregion

}
