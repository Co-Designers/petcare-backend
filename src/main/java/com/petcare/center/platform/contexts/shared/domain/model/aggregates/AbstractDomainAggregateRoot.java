package com.petcare.center.platform.contexts.shared.domain.model.aggregates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDomainAggregateRoot<T> {
    private final transient List<Object> domainEvents = new ArrayList<>();

    protected void registerEvent(Object event) {
        this.domainEvents.add(event);
    }

    public List<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
