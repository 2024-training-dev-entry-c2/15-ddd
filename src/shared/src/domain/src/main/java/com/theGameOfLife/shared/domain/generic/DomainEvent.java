package com.theGameOfLife.shared.domain.generic;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {

    private final Instant moment;
    private final String uuid;
    private final String nameEvent;
    private String aggregateRootId ;
    private String aggregateRootName;
    private Long versionDomainEvent;

    DomainEvent(final String nameEvent){
        this.nameEvent = nameEvent;
        this.uuid = UUID.randomUUID().toString();
        this.moment = Instant.now();
        this.versionDomainEvent = 1L;
    }

    public Instant getMoment() {
        return moment;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public String getAggregateRootName() {
        return aggregateRootName;
    }

    public void setAggregateRootName(String aggregateRootName) {
        this.aggregateRootName = aggregateRootName;
    }

    public Long getVersionDomainEvent() {
        return versionDomainEvent;
    }

    public void setVersionDomainEvent(Long versionDomainEvent) {
        this.versionDomainEvent = versionDomainEvent;
    }
}
