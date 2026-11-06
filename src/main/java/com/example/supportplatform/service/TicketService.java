package com.example.supportplatform.service;

import java.util.List;

import com.example.supportplatform.exception.InvalidTicketStatusTransitionException;
import com.example.supportplatform.specification.TicketSpecification;
import org.springframework.stereotype.Service;
import com.example.supportplatform.repository.TicketRepository;
import com.example.supportplatform.entity.Ticket;
import com.example.supportplatform.exception.TicketNotFoundException;
import com.example.supportplatform.entity.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Page<Ticket> getAllTickets(Pageable pageable){
        return ticketRepository.findAll(pageable);
    }

    public Ticket getTicketById(Long id){
        return ticketRepository.findById(id).
                orElseThrow(() -> new TicketNotFoundException(
                        "Ticket not found with id : " + id
                ));
    }

    public Ticket updateTicketStatus(Long id , TicketStatus status){
        Ticket ticket = getTicketById(id);
        TicketStatus currentStatus = ticket.getStatus();
        validateStatusTransition(currentStatus , status);
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    public void deleteTicketById(Long id){
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }

    public Page<Ticket> getTicketByStatus(TicketStatus ticketStatus , Pageable pageable){
        return ticketRepository.findByStatus(ticketStatus , pageable);
    }

    public Page<Ticket> searchTicketByEmail(String email , Pageable pageable){
        return ticketRepository.findByEmailContainingIgnoreCase(email , pageable);
    }

    public Page<Ticket> searchTicketBySubject(String subject , Pageable pageable){
        return ticketRepository.findBySubjectContainingIgnoreCase(subject , pageable);
    }

    public Page<Ticket> searchTickets(TicketStatus status , String email , String subject , Pageable pageable){
        Specification<Ticket>  specification = (root , query , criteriaBuilder) -> criteriaBuilder.conjunction();

        if(status != null){
            specification = specification.and(TicketSpecification.hasStatus(status));
        }

        if(email != null && !email.isBlank()){
            specification = specification.and(TicketSpecification.hasEmail(email));
        }

        if(subject != null && !subject.isBlank()){
            specification = specification.and(TicketSpecification.hasSubject(subject));
        }

        return ticketRepository.findAll(specification , pageable);
    }

    private void validateStatusTransition(TicketStatus currentStatus , TicketStatus newStatus){
        boolean isValidTransition = switch(currentStatus){
            case OPEN -> newStatus == TicketStatus.IN_PROGRESS || newStatus == TicketStatus.CLOSED;
            case IN_PROGRESS -> newStatus == TicketStatus.RESOLVED || newStatus == TicketStatus.CLOSED;
            case RESOLVED -> newStatus == TicketStatus.CLOSED;
            case CLOSED -> false;
        };

        if(!isValidTransition){
            throw new InvalidTicketStatusTransitionException(
                    "Cannot change ticket status from "
                    + currentStatus
                    + " to "
                    + newStatus
            );
        }
    }
}
