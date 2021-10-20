package hu.futureofmedia.task.contactsapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.service.ContactService;


@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;
    public ContactController(ContactService contactService) {
             this.contactService = contactService;
    }

    @GetMapping("/all/companyId/{companyId}")
    public ResponseEntity<Page<Contact>> getAllContactsByCompanyId(@PathVariable (value = "companyId") Long companyId, Pageable pageable) {
        Page<Contact> contacts = contactService.findAllContactByCompany(companyId, pageable);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<Contact>> getContactAll(Pageable pageable) {
        Page<Contact> contacts = contactService.findAllContact(pageable);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> addContact(
                                 @Valid @RequestBody Contact contact) {
        Contact newContact = contactService.addContact(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/update/{contactId}")
    public ResponseEntity<Contact> updateContact(@PathVariable (value = "contactId") Long contactId,  @Valid @RequestBody Contact contactRequest) {
        Contact updateContact = contactService.updateContact(contactId, contactRequest);
        return new ResponseEntity<>(updateContact, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<?> deleteContact( @PathVariable (value = "contactId") Long contactId) { //@PathVariable (value = "companyId") Long companyId,
        contactService.deleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/sDelete/{contactId}")
    public ResponseEntity<?> sDeleteContact( @PathVariable (value = "contactId") Long contactId) {
        contactService.sDeleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
