package com.example.SpringT.repo;

import com.example.SpringT.models.Ticket;
import com.example.SpringT.models.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Transactional
    void deleteByToken(UUID token);
    void findTicketByToken(UUID token);
    List<Ticket> findAll();
}