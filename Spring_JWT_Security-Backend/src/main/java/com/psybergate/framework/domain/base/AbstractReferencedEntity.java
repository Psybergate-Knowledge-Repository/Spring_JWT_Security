package com.psybergate.framework.domain.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("rawtypes")
public abstract class AbstractReferencedEntity<T extends Entity> extends AbstractEntity<T, String>
        implements Referenceable {

    private static final long serialVersionUID = 1L;

    /**
     * This refers to the name (code or abbreviation) that is typically used by
     * the business to refer to this entity.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Description for this entity.
     */
    @Column(nullable = false)
    private String description;

    protected AbstractReferencedEntity() {
    }

    public AbstractReferencedEntity(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUniqueIdentifier() {
        return getName();
    }
}
