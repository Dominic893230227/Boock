package com.boock.entity.po;

public enum Rank {
    F(1), E(2), D(3), C(4), B(5), A(6), S(7);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}