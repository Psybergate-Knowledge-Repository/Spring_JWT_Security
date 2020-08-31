package com.psybergate.framework.domain.base;

import java.time.LocalDateTime;

public interface Auditable {
    /**
     * The user who first created this entity.
     */
    String getCreatedBy();

    /**
     * The dateTime on which this entity was first created.
     */
    LocalDateTime getCreatedDateTime();

    /**
     * The user who last modified this entity.
     */
    String getLastModifiedBy();

    /**
     * The dateTime on which this entity was last modified.
     */
    LocalDateTime getLastModifiedDateTime();
}
