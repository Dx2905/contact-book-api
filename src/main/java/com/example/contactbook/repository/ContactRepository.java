package com.example.contactbook.repository;

import com.example.contactbook.entity.Contact;
import com.example.contactbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByUser(User user);
    List<Contact> findByUserAndNameContainingIgnoreCase(User user, String name); // for optional search
}
