package com.example.contactbook.service;

import com.example.contactbook.dto.ContactRequest;
import com.example.contactbook.dto.ContactResponse;
import com.example.contactbook.entity.Contact;
import com.example.contactbook.entity.User;
import com.example.contactbook.repository.ContactRepository;
import com.example.contactbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactResponse createContact(String userEmail, ContactRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setNotes(request.getNotes());
        contact.setUser(user);

        Contact saved = contactRepository.save(contact);
        return mapToResponse(saved);
    }

    public List<ContactResponse> getAllContacts(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return contactRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ContactResponse updateContact(String userEmail, UUID id, ContactRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contact contact = contactRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setNotes(request.getNotes());

        return mapToResponse(contactRepository.save(contact));
    }

    public void deleteContact(String userEmail, UUID id) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contact contact = contactRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contactRepository.delete(contact);
    }

    private ContactResponse mapToResponse(Contact contact) {
        ContactResponse res = new ContactResponse();
        res.setId(contact.getId());
        res.setName(contact.getName());
        res.setPhone(contact.getPhone());
        res.setEmail(contact.getEmail());
        res.setNotes(contact.getNotes());
        return res;
    }
}
