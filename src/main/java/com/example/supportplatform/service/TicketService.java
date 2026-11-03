package com.example.supportplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.supportplatform.repository.TicketRepository;
import com.example.supportplatform.entity.Ticket;
import com.example.supportplatform.exception.TicketNotFoundException;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id){
        return ticketRepository.findById(id).
                orElseThrow(() -> new TicketNotFoundException(
                        "Ticket not found with id : " + id
                ));
    }

    public Ticket updateTicketStatus(Long id , String status){
        Ticket ticket = getTicketById(id);
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }
}
