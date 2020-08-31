package com.psybergate.framework.domain.base;

import java.io.Serializable;

public interface Identifiable<P extends Serializable> {
    P getId();
}
