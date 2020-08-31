package com.psybergate.framework.domain.base;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public interface Entity<T extends Entity, U extends Serializable, P extends Serializable, V extends Serializable> extends DomainObject, UniquelyIdentifiable<U>, Identifiable<P>, Versionable<V> {
    boolean sameIdentityAs(T t);
}
