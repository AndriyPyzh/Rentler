package com.rentler.apartment.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetPolicy {
    DOGS_ALLOWED("Dogs allowed"),
    CAT_ALLOWED("Cats allowed"),
    NO_PETS("No pets");

    private final String text;

    PetPolicy(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @JsonValue
    public String getText() {
        return text;
    }
}
