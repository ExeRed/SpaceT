package com.example.SpringT.repo;

import com.example.SpringT.models.Email;
import com.example.SpringT.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
}
