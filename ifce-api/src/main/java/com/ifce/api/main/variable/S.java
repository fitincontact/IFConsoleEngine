package com.ifce.api.main.variable;

import java.io.Serializable;
import java.util.Objects;

public class S implements Serializable {
    private static final long serialVersionUID = -4571683861139683320L;
    String s;

    protected S(final String s) {
        this.s = s;
    }

    public void set(final String d) {
        this.s = d;
    }

    public String val() {
        return this.s;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof S)) {
            return false;
        }
        final S entry = (S) o;
        return this.s == entry.s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.s);
    }
}