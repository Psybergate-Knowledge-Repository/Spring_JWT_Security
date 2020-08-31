package com.psybergate.framework.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
@SuppressWarnings("rawtypes")
public abstract class AbstractEntity<T extends Entity, U extends Serializable>
		implements Entity<T, U, Long, Long>, Serializable {

	private static final long serialVersionUID = 1L;

	protected static final String SEPARATOR = "_";

	@Version
	@JsonIgnore
	private Long version;

	protected static String buildUniqueIdentifier(Object... uniqueIdentifiers) {
		if (uniqueIdentifiers == null || uniqueIdentifiers.length == 0)
			return null;
		String result = "";
		String separator = "";
		for (int i = 0; i < uniqueIdentifiers.length; i++) {
			result += (separator + uniqueIdentifiers[i]);
			if (i == 0) {
				separator = SEPARATOR;
			}
		}
		return result;
	}

	protected AbstractEntity() {
	}

	public Long getVersion() {
		return version;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public final boolean equals(Object object) {
		if (object == null)
			return false;
		if (this.getClass() != object.getClass())
			return false;
		return sameIdentityAs(((T) object));
	}

	public final boolean sameIdentityAs(T t) {
		if (this == t) {
			return true;
		}
		return internalSameIdentityAs(t);
	}

	protected boolean internalSameIdentityAs(T t) {
		return new EqualsBuilder().append(getUniqueIdentifier(), t.getUniqueIdentifier()).isEquals();
	}

	@Override
	public final int hashCode() {
		return internalHashCode();
	}

	protected int internalHashCode() {
		return new HashCodeBuilder().append(getUniqueIdentifier()).toHashCode();
	}

	/**
	 * If a subclass has a unique identifier made up of more than 1 attribute then,
	 * it will not override or call this method. The subclass's method should
	 * override method internalSameIdentityAs and internalHashCode()
	 */
	@JsonIgnore
	public U getUniqueIdentifier() {
		throw new UnsupportedOperationException("Method should always be implemented by the SubClass");
	}
}
