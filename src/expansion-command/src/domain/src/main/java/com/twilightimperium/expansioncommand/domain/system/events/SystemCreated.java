package com.twilightimperium.expansioncommand.domain.system.events;

import com.twilightimperium.shared.domain.generic.DomainEvent;
import java.util.List;

public class SystemCreated extends DomainEvent {
    private final Integer number;
    private final String factionId;

    public SystemCreated(Integer number, String factionId, List<String> planets) {
        super(EventsEnum.SYSTEM_CREATED.name());
        this.number = number;
        this.factionId = factionId;
    }

    public Integer getNumber() {
        return number;
    }

    public String getFactionId() {
        return factionId;
    }
}
