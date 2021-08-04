package com.ifce.api.main.variable;

import java.io.Serializable;
import java.util.Objects;

public class L implements Serializable {
    private static final long serialVersionUID = -6826694665830846495L;
    long l;

    protected L(final long l) {
        this.l = l;
    }

    public void set(final long l) {
        this.l = l;
    }

    public long val() {
        return this.l;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof L)) {
            return false;
        }
        final L entry = (L) o;
        return this.l == entry.l;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.l);
    }
}