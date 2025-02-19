package com.theGameOfLife.shared.domain.generic;

public abstract class DomainActionsContainer{
    protected Set<Consumer<? extends DomainEvent>> actions = new HashSet<>();

    protected void add(final Consumer<? super DomainEvent> consumer){
        actions.add(consumer);
    }

}