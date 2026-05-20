package com.portfolioprashant.contactapi.controller;


import com.portfolioprashant.contactapi.entity.Contact;
import com.portfolioprashant.contactapi.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://prashant-bairagi-portfolio.vercel.app/")
public class ContactController {


    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    public boolean createContact(@Valid @RequestBody Contact contact ) {
        contactService.saveContact(contact);
        return true;
    }
//    @GetMapping("/contacts")
//    public Collection<Contact> getAllContacts() {
//
//        return contactService.getAllContacts();
//    }
//    @GetMapping("/contacts/{id}")
//    public Contact getContact(
//            @PathVariable Long id
//    ) {
//        return contactService.findContactById(id);
//    }
//    @DeleteMapping("/contacts/{id}")
//    public boolean deleteContact( @PathVariable Long id ) {
//        contactService.deleteContactById(id);
//        return true;
//    }
}
