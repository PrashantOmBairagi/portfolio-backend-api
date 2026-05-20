package com.portfolioprashant.contactapi.service;

import com.portfolioprashant.contactapi.entity.Contact;
import com.portfolioprashant.contactapi.exception.ResourceNotFoundException;
import com.portfolioprashant.contactapi.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    public final ContactRepository contactRepository;
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public boolean saveContact(Contact contact) {
        contactRepository.save(contact);
        return true;
    }
    public Contact findContactById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));
    }
    public boolean deleteContactById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a valid positive number");
        }
        if (!contactRepository.existsById(id)) {        // ✅ Check if exists before delete
            throw new ResourceNotFoundException("Contact not found with ID: " + id);
        }
        contactRepository.deleteById(id);
        return true;
    }

    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }
}
