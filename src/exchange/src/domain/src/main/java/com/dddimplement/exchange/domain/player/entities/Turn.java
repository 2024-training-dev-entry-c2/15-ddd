package com.dddimplement.exchange.domain.player.entities;

import com.dddimplement.exchange.domain.player.values.TurnFase;
import com.dddimplement.exchange.domain.player.values.TurnFaseEnum;
import com.dddimplement.exchange.domain.player.values.TurnId;
import com.dddimplement.shared.domain.generic.Entity;

import java.util.Random;

public class Turn extends Entity<TurnId> {
    private TurnFase fase;

    public Turn(TurnId identity, TurnFase fase) {
        super(identity);
        this.fase = fase;
    }

    public Turn(TurnFase fase) {
        super(new TurnId());
        this.fase = fase;
    }

    public TurnFase getFase() {
        return fase;
    }

    public void setFase(TurnFase fase) {
        this.fase = fase;
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(12) + 1;
    }

    public void nextFase() {
        fase = switch (fase.getFase()) {
            case ROLL -> TurnFase.of(TurnFaseEnum.TRADE);
            case TRADE -> TurnFase.of(TurnFaseEnum.BUILD);
            case BUILD -> TurnFase.of(TurnFaseEnum.END);
            default -> fase;
        };
    }

}
