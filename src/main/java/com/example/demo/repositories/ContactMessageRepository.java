// repository/ContactMessageRepository.java
package com.example.demo.repositories;


import com.example.demo.model.ContactMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactMessageRepository extends MongoRepository<ContactMessage, String> {
    // Add custom query methods if needed
}