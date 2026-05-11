package com.sovereign.service;

import com.sovereign.model.ContactMessage;
import com.sovereign.repository.ContactRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * ContactService — handles saving and retrieving contact form messages.
 */
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Saves a contact message from the form submission.
     * createdAt is set automatically in the ContactMessage constructor.
     */
    public void saveMessage(String name, String email, String message) {
        ContactMessage msg = new ContactMessage(name, email, message);
        contactRepository.save(msg);
    }

    /**
     * Returns all messages for the admin dashboard.
     */
    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }
}
