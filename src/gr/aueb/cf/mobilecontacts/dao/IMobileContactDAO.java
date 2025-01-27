package gr.aueb.cf.mobilecontacts.dao;



import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

public interface IMobileContactDAO {
    //5 βασικες CRUD υπηρεσίες (Create, read, update and delete)
    MobileContact insert(MobileContact mobileContact);
    MobileContact update(Long id, MobileContact mobileContact);
    void deleteById(Long id);
    MobileContact getById(Long id);
    List<MobileContact> getAll ();

    // εξτρα CRUD υπηρεσία
    void deleteByPhoneNumber(String phoneNumber);

    //"queries"
    MobileContact getByPhoneNumber(String phoneNumber);
    boolean userIdExists(Long id);
    boolean phoneNumberExists(String phoneNumber);
}
