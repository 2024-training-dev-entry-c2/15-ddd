public abstract class AggregateRoot<I extends Identity> extends Entity<I>{

    private final DomainActionHandler actionHandler = new DomainActionHandler();

    protected AggregateRoot(final I identity){
        super(identity);
    }

    public List<DomainEvent> getUncommittedEvents(){
        return List.copyOf(actionHandler.getEvents());
    }

    public void markEventAsCommitted(){
        actionHandler.getEvents().clear();
    }

    public void subscribe(final DomainActionsContainer container){
        actionHandler.subscribe(container)
    }

    protected void apply(final DomainEvent event) {
        final String aggregateName = getIdentity().getClass().getSimpleName().replace("id", "").toLowerCase();
        event.setAggregateName(aggregateName);
        event.setAggregateRootId(getIdentity().getValue());
        actionHandler.apply(event);

    }

}