package com.psybergate.framework.domain.base;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public abstract class AbstractValueObject<T extends ValueObject> implements ValueObject<T>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public final boolean equals(Object object) {
        if(object==null) return false;
        if (this.getClass() != object.getClass()) return false;
        return sameValueAs(((T) object));
    }

    public final boolean sameValueAs(T t) {
        if (this == t) {
            return true;
        }
        return internalSameValueAs(t);
    }

    protected boolean internalSameValueAs(T t) {
        return EqualsBuilder.reflectionEquals(this, t);
    }

    @Override
    public final int hashCode() {
        return internalHashCode();
    }

    protected int internalHashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
