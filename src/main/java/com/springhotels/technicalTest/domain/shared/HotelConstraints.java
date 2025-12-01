package com.springhotels.technicalTest.domain.shared;

public final class HotelConstraints {

    private HotelConstraints() {}

    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 100;
    public static final int POSTAL_CODE_MIN_LENGTH = 2;
    public static final int POSTAL_CODE_MAX_LENGTH = 10;
    public static final String POSTAL_CODE_PATTERN = "^[A-Za-z0-9\\- ]{" + POSTAL_CODE_MIN_LENGTH + "," + POSTAL_CODE_MAX_LENGTH + "}$";
    public static final String NAME_PATTERN = "^[\\p{L}0-9 .,'-]+$";
}
