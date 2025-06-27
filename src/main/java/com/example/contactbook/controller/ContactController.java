package com.example.contactbook.controller;

import com.example.contactbook.dto.ContactRequest;
import com.example.contactbook.dto.ContactResponse;
import com.example.contactbook.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    // Create new contact
    @PostMapping
    public ResponseEntity<ContactResponse> createContact(@RequestBody ContactRequest request,
                                                        Authentication authentication) {
        String userEmail = authentication.getName();
        System.out.println("📨 Authenticated user: " + userEmail);
        System.out.println("✅ Email from Spring Security: " + authentication.getName());
        return ResponseEntity.ok(contactService.createContact(userEmail, request));
    }


    // Get all contacts for current user
    @GetMapping
    public ResponseEntity<List<ContactResponse>> getContacts(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(contactService.getAllContacts(userEmail));
    }

    // Update contact
    @PutMapping("/{id}")
    public ResponseEntity<ContactResponse> updateContact(@PathVariable UUID id,
                                                         @RequestBody ContactRequest request,
                                                         Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(contactService.updateContact(userEmail, id, request));
    }

    // Delete contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable UUID id,
                                              Authentication authentication) {
        String userEmail = authentication.getName();
        contactService.deleteContact(userEmail, id);
        return ResponseEntity.noContent().build();
    }
}
