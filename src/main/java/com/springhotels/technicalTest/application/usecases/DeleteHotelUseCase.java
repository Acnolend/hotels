package com.springhotels.technicalTest.application.usecases;

import java.util.UUID;

public interface DeleteHotelUseCase {

    /**
     * This method delete a Hotel.
     * @param id
     * @return true if hotels is succesfully deleted or false if the hotels with that UUID don't exist.
     */
    public boolean deleteHotel(UUID id);
}
