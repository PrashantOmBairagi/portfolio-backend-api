package com.portfolioprashant.contactapi.service;

import com.portfolioprashant.contactapi.entity.Contact;
// DB imports commented out — repository disabled until cloud PostgreSQL is restored.
// import com.portfolioprashant.contactapi.exception.ResourceNotFoundException;
// import com.portfolioprashant.contactapi.repository.ContactRepository;
import org.springframework.stereotype.Service;

// import java.util.List;

@Service
public class ContactService {

    // ── DB repository disabled (cloud PostgreSQL instance expired) ────────────
    // public final ContactRepository contactRepository;
    // public ContactService(ContactRepository contactRepository) {
    //     this.contactRepository = contactRepository;
    // }

    private final EmailService emailService;

    public ContactService(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Forwards the contact form submission via email.
     * Previously persisted to PostgreSQL — restore DB and un-comment repository
     * calls when a new instance is provisioned.
     */
    public boolean saveContact(Contact contact) {
        // contactRepository.save(contact);  ← restore when DB is back
        emailService.sendContactEmail(contact);
        return true;
    }

    // ── DB-dependent methods — kept for reference, re-enable with DB ──────────
    // public Contact findContactById(Long id) {
    //     if (id == null) {
    //         throw new IllegalArgumentException("ID must not be null");
    //     }
    //     return contactRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));
    // }

    // public boolean deleteContactById(Long id) {
    //     if (id == null || id <= 0) {
    //         throw new IllegalArgumentException("ID must be a valid positive number");
    //     }
    //     if (!contactRepository.existsById(id)) {
    //         throw new ResourceNotFoundException("Contact not found with ID: " + id);
    //     }
    //     contactRepository.deleteById(id);
    //     return true;
    // }

    // public List<Contact> getAllContacts() {
    //     return (List<Contact>) contactRepository.findAll();
    // }
}

