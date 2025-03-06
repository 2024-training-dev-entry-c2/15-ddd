package com.monopoly.shared.application;

public abstract class Request {
    private String aggregateId;

    protected Request() {
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    protected Request(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
