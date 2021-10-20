package hu.futureofmedia.task.contactsapi.service;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.Stts;
import hu.futureofmedia.task.contactsapi.exception.ResourceNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.CompanyRepository;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, CompanyRepository companyRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public Page<Contact> findAllContactByCompany(Long companyId, Pageable pageable){
        return contactRepository.findByCompanyId(companyId, pageable);
    }

    public Page<Contact> findAllContact(Pageable pageable){
        //return contactRepository.findAll(pageable);
        

        return contactRepository.findByStatus(Stts.Active, pageable);
    }

    public Contact addContact(Long companyId, Contact contact) {
        return companyRepository.findById(companyId).map(company -> {
            
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            PhoneNumber phoneNumber = null;
            
            try {
                phoneNumber = phoneUtil.parse(contact.getPhoneNumber(),"HU");
                
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
            
            
            if(!emailCheck(contact.getEmail())){
                return null;
            }

            if(phoneUtil.isValidNumber(phoneNumber)){
            contact.setPhoneNumber(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164));
            
            contact.setCompany(company);
            return contactRepository.save(contact);
            }
            else{return null;}
        }).orElseThrow(() -> new ResourceNotFoundException("companyId " + companyId + " not found"));
    }
    
    public Contact updateContact(Long companyId, Long contactId, Contact contactRequest){
        if(!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("CompanyId " + companyId + " not found");
        }

        return contactRepository.findById(contactId).map(contact -> {
            if(!emailCheck(contactRequest.getEmail())){
                return null;
            }
            contactRequest.setCompany(companyRepository.findById(companyId).get());
            contactRequest.setId(contact.getId());
            contactRequest.setCreateDate(contact.getCreateDate());
            return contactRepository.save(contactRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("ContactId " + contactId + "not found"));
    }


    public void deleteContact(Long contactId){
        contactRepository.deleteById(contactId);
    }

    public Contact sDeleteContact(Long contactId){
        return contactRepository.findById(contactId).map(contact -> {
            contact.setStatus(Stts.Deleted);
            return contactRepository.save(contact);
        }).orElseThrow(() -> new ResourceNotFoundException("ContactId " + contactId + "not found"));
    }
   
    private boolean emailCheck(String email){
        //Regular Expression   
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
        //Compile regular expression to get the pattern  
        Pattern pattern = Pattern.compile(regex);  
        
        //Create instance of matcher   
        Matcher matcher = pattern.matcher(email);  
        
        return matcher.matches();
    }

}
