package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDaoImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.IMobileContactService;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;
import gr.aueb.cf.mobilecontacts.validation.ValidationUtil;

public class MobileContactController {

    private final IMobileContactDAO dao = new MobileContactDaoImpl();
    private final IMobileContactService service = new MobileContactServiceImpl(dao);

    public String insertContact(MobileContactInsertDTO insertDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            //validation of input data
            String errorVector = ValidationUtil.validateDTO(insertDTO);
//            if(!error.isEmpty()) {
            if(!errorVector.equals("")){
                return "Error " + "Validation error.\n" + errorVector;
            }
            //if validation is ok, insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            readOnlyDTO = mapMobileContactToDTO(mobileContact);
            return "OK\n " + serializeDTO(readOnlyDTO);
        }catch (PhoneNumberAlreadyExistsException e) {
           return "Error\n " + e.getMessage() + "\n";
        }
    }

    private MobileContactReadOnlyDTO mapMobileContactToDTO(MobileContact mobileContact){
        return new MobileContactReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(),
                mobileContact.getLastname(), mobileContact.getPhoneNumber());
    }

    private String serializeDTO (MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + "First name: " + readOnlyDTO.getFirstname()
                + "Last name: " + readOnlyDTO.getLastname() + "Phone number: " + readOnlyDTO.getPhoneNumber();
    }
}
