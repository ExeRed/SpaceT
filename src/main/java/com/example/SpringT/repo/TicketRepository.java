package com.example.SpringT.repo;

import com.example.SpringT.models.Ticket;
import com.example.SpringT.models.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {


    @Modifying
    @Query("DELETE FROM Ticket t WHERE t.token = :tokenValue")
    void deleteByToken(@Param("tokenValue") String tokenValue);


    void findTicketByToken(UUID token);
    List<Ticket> findAll();

}