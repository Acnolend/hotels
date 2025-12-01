package com.springhotels.technicalTest.adapter.rest.response;

import com.springhotels.technicalTest.adapter.rest.request.AddressDTO;

public record HotelResponseDTO(
        String id,
        String name,
        int stars,
        AddressDTO address
) {}

