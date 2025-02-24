package com.dddimplement.exchange.domain.trade.values;

import com.dddimplement.shared.domain.generic.Identity;

public class DomesticId extends Identity {

    public DomesticId(){
        super();
    }

    private DomesticId(String id){
        super(id);
    }

    public static DomesticId of(String id){
        return new DomesticId(id);
    }
}
