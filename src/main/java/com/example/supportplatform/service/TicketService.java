package com.example.supportplatform.service;

import org.springframework.stereotype.Service;
import com.example.supportplatform.repository.TicketRepository;
import com.example.supportplatform.entity.Ticket;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
