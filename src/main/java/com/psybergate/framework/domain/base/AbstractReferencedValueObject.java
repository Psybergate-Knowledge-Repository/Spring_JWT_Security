package com.psybergate.framework.domain.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("rawtypes")
public class AbstractReferencedValueObject<T extends ValueObject> extends AbstractValueObject<T>
        implements Referenceable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    public AbstractReferencedValueObject() {
        init(name, name);
    }

    public AbstractReferencedValueObject(String name) {
        init(name, name);
    }

    public AbstractReferencedValueObject(String name, String description) {
        init(name, description);
    }

    protected void init(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
