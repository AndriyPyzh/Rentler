package com.rentler.apartment.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Amenities {
    POOL("Pool"),
    GARDEN("Garden"),
    ELEVATOR("Elevator"),
    DOORMAN("Doorman"),
    DECK("Deck"),
    WASHER("Washer"),
    GYM("Gym"),
    PARKING("Parking Spot"),
    FIREPLACE("Fireplace"),
    CONDITIONER("Air conditioning");


    private final String text;

    Amenities(final String text) {
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
