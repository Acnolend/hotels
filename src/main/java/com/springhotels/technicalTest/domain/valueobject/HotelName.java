package com.springhotels.technicalTest.domain.valueobject;

import java.util.Objects;

public class HotelName {
    private static final String ERROR_NAME_NOT_DEFINED = "The name of the hotel cannot be null";
    private static final String ERROR_NAME_BLANK = "The name of the hotel cannot be blank";
    private static final String ERROR_NAME_TOO_SHORT = "The name of the hotel must have at least 2 characters";
    private static final String ERROR_NAME_TOO_LONG = "The name of the hotel must not exceed 100 characters";
    private static final String ERROR_NAME_INVALID_CHARS = "The name of the hotel contains invalid characters";
    /**
     * The name of the hotel. It is a required attribute.
     */
    private String name;

    public HotelName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public HotelName setName(String name) {
        return new HotelName(name);
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NAME_NOT_DEFINED);
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_NAME_BLANK);
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException(ERROR_NAME_TOO_SHORT);
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException(ERROR_NAME_TOO_LONG);
        }
        if (!name.matches("^[\\p{L}0-9 .,'-]+$")) {
            throw new IllegalArgumentException(ERROR_NAME_INVALID_CHARS);
        }
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        final HotelName otherHotelName = (HotelName) otherObject;
        return this.name.equals(otherHotelName.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
