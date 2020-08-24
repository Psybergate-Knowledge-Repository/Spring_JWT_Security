package com.psybergate.framework.domain.base;

@SuppressWarnings("rawtypes")
public interface ValueObject<T extends ValueObject> extends DomainObject {
    boolean sameValueAs(T t);
}
