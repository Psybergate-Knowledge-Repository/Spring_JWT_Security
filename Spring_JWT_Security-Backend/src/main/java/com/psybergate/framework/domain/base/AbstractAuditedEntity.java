package com.psybergate.framework.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@SuppressWarnings("rawtypes")
public abstract class AbstractAuditedEntity<T extends Entity, U extends Serializable> extends AbstractEntity<T, U>
        implements Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who first created this entity.
     */
    @CreatedBy
    @JsonIgnore
    @Column(nullable = false)
    private String createdBy;

    /**
     * The dateTime on which this entity was first created.
     */
    @CreatedDate
    @JsonIgnore
    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    /**
     * The user who last modified this entity.
     */
    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;

    /**
     * The dateTime on which this entity was last modified.
     */
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime lastModifiedDateTime;

    protected AbstractAuditedEntity() {
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
