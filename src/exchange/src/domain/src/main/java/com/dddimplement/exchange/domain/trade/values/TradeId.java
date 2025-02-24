package com.dddimplement.exchange.domain.trade.values;

import com.dddimplement.shared.domain.generic.Identity;

public class TradeId extends Identity {

        public TradeId() {
            super();
        }

        private TradeId(String id) {
            super(id);
        }

        public static TradeId of(String id) {
            return new TradeId(id);
        }
}
