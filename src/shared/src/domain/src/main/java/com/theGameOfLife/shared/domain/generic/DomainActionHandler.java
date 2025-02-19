package com.theGameOfLife.shared.domain.generic;

public abstract class DomainActionHandler{
    private final List<DomainEvent> listEvents = new LinkedList<>();
    private final Map<String, AtomicLong> versions = ConcurrentHashMap<>();
    protected Set<Consumer<? super DomainEvent>> actions = new HashSet<>();

    public List<DomainEvent> getEvents(){
        return listEvents;
    }

    public void subscribe (final DomainActionContainer actionsContainer) {
        actions.addAll(actionsContainer.actions);
    }

    private void apply (final DomainEvent event){
        actions.forEach(action -> handle(event, action))
    }

    private void handle(final DomainEvent event, final Consumer<? super DomainEvent> consumer){
        try {
            consumer.accept(event);
            final long newVersion = increaseVersion(event);
            event.setVersion(newVersion);
        } catch (ClassCastException ignored) {}
    }

    public long increaseVersion(final DomainEvent event){
        final AtomicLong version = versions.get(event.getName());
        final long newVersion = version = null ? event.getVersion() : version.incrementAndGet();
        version.put(event.getName(), new AtomicLong(newVersion));
        return newVersion
    }


}