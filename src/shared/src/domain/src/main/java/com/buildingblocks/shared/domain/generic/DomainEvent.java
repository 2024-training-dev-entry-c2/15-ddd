package com.buildingblocks.shared.domain.generic;

import java.time.Instant;
import java.util.UUID;

public class DomainEvent {
    private Instant when;
    private String uuid;
    private String nameDomainEvent;
    private String aggregateRootId;
    private String aggregateName;
    private Long version;

    protected DomainEvent(String nameDomainEvent) {
        this.when = Instant.now();
        this.uuid = UUID.randomUUID().toString();
        this.nameDomainEvent = nameDomainEvent;
        this.version = 1L;
    }

    public Instant getWhen() {
        return when;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEventName() {
        return nameDomainEvent;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public String getAggregateName() {
        return aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setWhen(Instant when) {
        this.when = when;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setEventName(String tableName) {
        this.nameDomainEvent = tableName;
    }
}
