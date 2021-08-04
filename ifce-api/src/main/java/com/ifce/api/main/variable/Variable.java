package com.ifce.api.main.variable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Variable implements Serializable {

    private static final long serialVersionUID = 2747570558540364997L;
    private static Variable instance;
    final List<B> booleans = new ArrayList<>();
    final List<L> longs = new ArrayList<>();
    final List<D> doubles = new ArrayList<>();
    final List<S> strings = new ArrayList<>();

    public static Variable getInstance() {
        if (instance == null) {
            instance = new Variable();
        }
        return instance;
    }

    private void add(final B v) {
        this.booleans.add(v);
    }

    private void add(final L v) {
        this.longs.add(v);
    }

    private void add(final D v) {
        this.doubles.add(v);
    }

    private void add(final S v) {
        this.strings.add(v);
    }

    public B newBoolean(final boolean v) {
        final var i = new B(v);
        add(i);
        return i;
    }

    public L newLong(final long v) {
        final var i = new L(v);
        add(i);
        return i;
    }

    public D newDouble(final double v) {
        final var i = new D(v);
        add(i);
        return i;
    }

    public S newString(final String v) {
        final var i = new S(v);
        add(i);
        return i;
    }

    public void set(final Variable v) {
        booleans.removeAll(booleans);
        booleans.addAll(v.booleans);
        longs.removeAll(longs);
        longs.addAll(v.longs);
        doubles.removeAll(doubles);
        doubles.addAll(v.doubles);
        strings.removeAll(strings);
        strings.addAll(v.strings);
    }

}