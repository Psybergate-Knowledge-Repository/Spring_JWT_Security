package com.psybergate.framework.domain.base;

import java.io.Serializable;

public interface Versionable<V extends Serializable> {
    V getVersion();
}