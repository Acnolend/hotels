package com.springhotels.technicalTest.domain.valueobject;

import java.util.Objects;

import com.springhotels.technicalTest.domain.shared.HotelConstraints;

public class HotelAddress {

    private static final String ERROR_FIELD_NOT_DEFINED = "%s cannot be null";
    private static final String ERROR_FIELD_BLANK = "%s cannot be blank";
    private static final String ERROR_FIELD_TOO_SHORT = "%s must have at least " + HotelConstraints.MIN_LENGTH + " characters";
    private static final String ERROR_FIELD_TOO_LONG = "%s must not exceed " + HotelConstraints.MAX_LENGTH + " characters";
    private static final String ERROR_POSTAL_CODE_INVALID = "Postal code format is invalid";

    /**
     * The street of the address. It is a required attribute.
     */
    private final String street;
    /**
     * The city of the address. It is a required attribute.
     */
    private final String city;
    /**
     * The country of the address. It is a required attribute.
     */
    private final String country;
    /**
     * The postal code of the address. It is a required attribute.
     */
    private final String postalCode;

    public HotelAddress(String street, String city, String country, String postalCode) {
        this.street = validateTextField(street, "Street");
        this.city = validateTextField(city, "City");
        this.country = validateTextField(country, "Country");
        this.postalCode = validatePostalCode(postalCode);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    private String validateTextField(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_NOT_DEFINED, fieldName));
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_BLANK, fieldName));
        }
        if (trimmed.length() < HotelConstraints.MIN_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_TOO_SHORT, fieldName));
        }
        if (trimmed.length() > HotelConstraints.MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_TOO_LONG, fieldName));
        }
        return trimmed;
    }

    private String validatePostalCode(String postalCode) {
        if (postalCode == null) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_NOT_DEFINED, "Postal code"));
        }
        String trimmed = postalCode.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_BLANK, "Postal code"));
        }
        if (!trimmed.matches(HotelConstraints.POSTAL_CODE_PATTERN)) {
            throw new IllegalArgumentException(ERROR_POSTAL_CODE_INVALID);
        }
        return trimmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelAddress that = (HotelAddress) o;
        return street.equals(that.street)
                && city.equals(that.city)
                && country.equals(that.country)
                && postalCode.equals(that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, country, postalCode);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", street, city, country, postalCode);
    }
}
