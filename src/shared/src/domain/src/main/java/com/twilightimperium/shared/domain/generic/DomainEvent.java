package com.twilightimperium.shared.domain.generic;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final Instant when;
    private final String uuid;
    private final String name;
    private String aggregateRootId;
    private String aggregateName;
    private Long version;

    protected DomainEvent(String name) {
        this.when = Instant.now();
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.version = 1L;
    }

    public String getAggregateName() {
        return aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Instant getWhen() {
        return when;
    }
}
