package com.growit.api.domain;

public enum SafetyClass {
A, B, C, D;

    @Override
    public String toString() {
        return name();
    }
}
