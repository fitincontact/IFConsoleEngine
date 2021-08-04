package com.ifce.api.main.variable;

import java.io.Serializable;
import java.util.Objects;

public class D implements Serializable {
    private static final long serialVersionUID = 564369598016935181L;
    double d;

    protected D(final double d) {
        this.d = d;
    }

    public void set(final double d) {
        this.d = d;
    }

    public double val() {
        return this.d;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof D)) {
            return false;
        }
        final D entry = (D) o;
        return this.d == entry.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.d);
    }
}