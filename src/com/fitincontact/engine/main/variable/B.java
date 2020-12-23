package com.fitincontact.engine.main.variable;

import java.io.Serializable;
import java.util.Objects;

public class B implements Serializable {
    private static final long serialVersionUID = -2454772356475180773L;
    boolean b;

    protected B(final boolean b) {
        this.b = b;
    }

    public void set(final boolean b) {
        this.b = b;
    }

    public boolean val() {
        return this.b;
    }

    @Override
    public boolean equals(final Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof B)) {
            return false;
        }
        final B entry = (B) o;
        return this.b == entry.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.b);
    }
}