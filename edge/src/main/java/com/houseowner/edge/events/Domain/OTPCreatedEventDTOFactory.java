package com.houseowner.edge.events.Domain;

import com.houseowner.edge.dto.OTPCreatedEventDTO;
import com.houseowner.edge.interfaces.DTO;
import com.houseowner.edge.interfaces.DTOFactory;

public class OTPCreatedEventDTOFactory implements DTOFactory {
    /**
     */
    @Override
    public DTO createDTO() {
        return new OTPCreatedEventDTO();
    }
}
