package com.example.supportplatform.repository;

import com.example.supportplatform.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.supportplatform.entity.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TicketRepository extends JpaRepository<Ticket,Long>{
    Page<Ticket> findByStatus(TicketStatus status , Pageable pageable);
}
