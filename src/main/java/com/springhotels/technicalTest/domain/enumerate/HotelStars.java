package com.springhotels.technicalTest.domain.enumerate;

import java.util.Arrays;
import java.util.Random;

public enum HotelStars {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private final int value;

    HotelStars(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static HotelStars fromValue(int value) {
        return Arrays.stream(values())
                .filter(stars -> stars.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hotel stars must be between 1 and 5"));
    }

    public static boolean isValid(int value) {
        return Arrays.stream(values())
                .anyMatch(stars -> stars.value == value);
    }

    public static HotelStars random() {
        return values()[new Random().nextInt(values().length)];
    }
}
