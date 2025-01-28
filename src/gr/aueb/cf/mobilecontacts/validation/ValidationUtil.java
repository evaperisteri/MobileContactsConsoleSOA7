package gr.aueb.cf.mobilecontacts.validation;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

public class ValidationUtil {
    /**
     * no instances of this class should be available, so private constructor
     */
    private ValidationUtil(){

    }

    public static  String validateDTO(MobileContactInsertDTO insertDTO) {
        String errorResponse = "";
        if(insertDTO.getPhoneNumber().length()<=5) errorResponse += "Phone number must have more than 5 digits\n";
        if(insertDTO.getFirstname().length()<2) errorResponse += "First name must have more than 2 characters\n";
        if(insertDTO.getLastname().length()<2) errorResponse  += "Last name must have more than 2 characters\n";
        return errorResponse;
    }

    public static  String validateDTO(MobileContactUpdateDTO updateDTO) {
        String errorResponse = "";
        if(updateDTO.getPhoneNumber().length()<=5) errorResponse += "Phone number must have more than 5 digits\n";
        if(updateDTO.getFirstname().length()<2) errorResponse += "First name must have more than 2 characters\n";
        if(updateDTO.getLastname().length()<2) errorResponse  += "Last name must have more than 2 characters\n";
        return errorResponse;
    }
}
