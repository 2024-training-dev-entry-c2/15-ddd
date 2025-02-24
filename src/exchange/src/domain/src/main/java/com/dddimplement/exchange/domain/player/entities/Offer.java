package com.dddimplement.exchange.domain.player.entities;

import com.dddimplement.exchange.domain.player.values.Amount;
import com.dddimplement.exchange.domain.player.values.IsAccepted;
import com.dddimplement.exchange.domain.player.values.OfferId;
import com.dddimplement.exchange.domain.player.values.ResourceType;
import com.dddimplement.shared.domain.generic.Entity;

public class Offer extends Entity<OfferId> {
    private Amount amount;
    private ResourceType type;
    private IsAccepted isAccepted;

    // region Constructors
    public Offer(OfferId identity, Amount amount, ResourceType type, IsAccepted isAccepted) {
        super(identity);
        this.amount = amount;
        this.type = type;
        this.isAccepted = isAccepted;
    }

    public Offer(Amount amount, ResourceType type, IsAccepted isAccepted) {
        super(new OfferId());
        this.amount = amount;
        this.type = type;
        this.isAccepted = isAccepted;
    }
    // endregion

    //region Getters and Setters
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public IsAccepted getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(IsAccepted isAccepted) {
        this.isAccepted = isAccepted;
    }

    //endregion

    //region Methods
    public void accept() {
        this.isAccepted = IsAccepted.of(true);
    }

    public void reject() {
        this.isAccepted = IsAccepted.of(false);
    }

    //endregion
}
