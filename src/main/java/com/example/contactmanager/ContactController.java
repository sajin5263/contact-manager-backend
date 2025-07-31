package com.example.contactmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // Create Contact
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    // Get All Contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get Contact by ID
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    // Update Contact
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contactDetails) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            contact.setName(contactDetails.getName());
            contact.setEmail(contactDetails.getEmail());
            contact.setPhone(contactDetails.getPhone());
            return contactRepository.save(contact);
        }
        return null;
    }

    // Delete Contact
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
}
