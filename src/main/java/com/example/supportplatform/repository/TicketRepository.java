package com.example.supportplatform.repository;

import com.example.supportplatform.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.supportplatform.entity.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TicketRepository extends JpaRepository<Ticket,Long> , JpaSpecificationExecutor<Ticket>{

    Page<Ticket> findByStatus(TicketStatus status , Pageable pageable);

    Page<Ticket> findByEmailContainingIgnoreCase(String email , Pageable pageable);

    Page<Ticket> findBySubjectContainingIgnoreCase(String subject , Pageable pageable);
}
