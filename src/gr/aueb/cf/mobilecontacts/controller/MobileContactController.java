package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.core.serializer.Serializer;
import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDaoImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.mapper.Mapper;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.IMobileContactService;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;
import gr.aueb.cf.mobilecontacts.validation.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class MobileContactController {

    private final IMobileContactDAO dao = new MobileContactDaoImpl();
    private final IMobileContactService service = new MobileContactServiceImpl(dao);

    public String insertContact(MobileContactInsertDTO insertDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            //validation of input data
            String errorVector = ValidationUtil.validateDTO(insertDTO);
            //            if(!errorVector.equals(""))
            if(!errorVector.isEmpty()) {
                return "Error." + "Validation error.\n" + errorVector;
            }
            //if validation is ok, insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n " + Serializer.serializeDTO(readOnlyDTO);
        }catch (PhoneNumberAlreadyExistsException e) {
           return "Error\n " + e.getMessage() + "\n";
        }
    }


    public String updateContact(MobileContactUpdateDTO updateDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            //validation of input data
            String errorVector = ValidationUtil.validateDTO(updateDTO);
            //            if(!errorVector.equals(""))
            if(!errorVector.isEmpty()) {
                return "Error." + "Validation error.\n" + errorVector;
            }
            //if validation is ok, insert contact
            mobileContact = service.updateMobileContact(updateDTO);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n " + Serializer.serializeDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException e) {
            return "Error.\n" + e.getMessage() + "\n";
        } catch (ContactNotFoundException e) {
            return "Error.\n" + e.getMessage() + "\n";
        }
    }

    public String deleteContactById (Long id) {
        try {
            service.deleteContactById(id);
            return "OK\n Contact deleted";
        } catch (ContactNotFoundException e) {
            return "Error.\n Error during deletion. Contact not found.";
        }
    }

    public String getContactById(Long id) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactById(id);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n" + Serializer.serializeDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error.\n" + "Contact not found.";
        }
    }

    public List<String> getAllContacts() {
        List<MobileContact> contacts;
        List<String> serializedList = new ArrayList<>();
        MobileContactReadOnlyDTO readOnlyDTO;
        String serialized;

        contacts = service.getAllContacts();
        for (MobileContact contact : contacts) {
            readOnlyDTO = Mapper.mapMobileContactToDTO(contact);
            serialized = Serializer.serializeDTO(readOnlyDTO);
            serializedList.add(serialized);
        }

        return serializedList;
    }

    public String getContactByPhoneNumber(String phoneNumber) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactByPhoneNumber(phoneNumber);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK.\n" + Serializer.serializeDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error.\n" + "Contact not found.";
        }
    }

    public String deleteContactByPhoneNumber (String phoneNumber) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactByPhoneNumber(phoneNumber);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            service.deleteContactByPhoneNumber(phoneNumber);

            return "OK\n Contact deleted" + Serializer.serializeDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error.\n" + "Error during deletion. Contact not found.";
        }
    }


//      --πήγε στην κλάση Mapper και εγινε public static
//    private MobileContactReadOnlyDTO mapMobileContactToDTO(MobileContact mobileContact){
//        return new MobileContactReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(),
//                mobileContact.getLastname(), mobileContact.getPhoneNumber());
//    }

//    private String serializeDTO (MobileContactReadOnlyDTO readOnlyDTO) {
//        return "ID: " + readOnlyDTO.getId() + "First name: " + readOnlyDTO.getFirstname()
//                + "Last name: " + readOnlyDTO.getLastname() + "Phone number: " + readOnlyDTO.getPhoneNumber();
//    }
}
