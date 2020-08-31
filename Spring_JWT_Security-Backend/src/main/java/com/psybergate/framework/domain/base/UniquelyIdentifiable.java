package com.psybergate.framework.domain.base;

import java.io.Serializable;

public interface UniquelyIdentifiable<U extends Serializable> {
    U getUniqueIdentifier();
}