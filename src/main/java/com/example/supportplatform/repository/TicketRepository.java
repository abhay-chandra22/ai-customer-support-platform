package com.example.supportplatform.repository;

import com.example.supportplatform.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long>{

}
