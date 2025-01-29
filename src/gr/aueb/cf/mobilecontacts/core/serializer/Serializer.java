package gr.aueb.cf.mobilecontacts.core.serializer;

import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;

/**
 * no instances of this class should be available
 */
public class Serializer {
    private Serializer(){

    }
    public static String serializeDTO (MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + "First name: " + readOnlyDTO.getFirstname()
                + "Last name: " + readOnlyDTO.getLastname() + "Phone number: " + readOnlyDTO.getPhoneNumber();
    }
}
