package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class TurnFase implements IValueObject {
    private final  TurnFaseEnum fase;

    private TurnFase(final TurnFaseEnum fase) {
        this.fase = fase;
        validate();
    }

    public static TurnFase of(final TurnFaseEnum fase) {
        return new TurnFase(fase);
    }

    public TurnFaseEnum getFase() {
        return fase;
    }

    @Override
    public void validate() {
        if(this.fase == null) {
            throw new IllegalArgumentException("TurnFase cannot be null");
        }
    }
}
