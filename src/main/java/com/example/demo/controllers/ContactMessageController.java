package com.example.demo.controllers;

import com.example.demo.model.ContactMessage;
import com.example.demo.repositories.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5501", "http://localhost:5501"})
@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

    @Autowired
    private ContactMessageRepository repository;

    // Save a new contact message (POST)
    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody ContactMessage message) {
        repository.save(message);
        return ResponseEntity.ok("Message received!");
    }

    // Get all contact messages (GET)
    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }

    // Delete a contact message by ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Message deleted!");
    }
}
