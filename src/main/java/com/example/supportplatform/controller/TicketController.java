package com.example.supportplatform.controller;
import com.example.supportplatform.entity.TicketStatus;
import com.example.supportplatform.service.TicketService;
import com.example.supportplatform.entity.Ticket;
import com.example.supportplatform.dto.TicketStatusUpdateRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@Valid @RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }

    @GetMapping
    public Page<Ticket> getAllTickets(@RequestParam(required = false) TicketStatus status , @PageableDefault(size = 10 , sort = "createdAt" , direction = Sort.Direction.DESC) Pageable pageable){
        if(status == null){
            return ticketService.getAllTickets(pageable);
        }
        return ticketService.getTicketByStatus(status , pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id){
        Ticket ticket = ticketService.getTicketById(id);

        return ResponseEntity.ok(ticket);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(@PathVariable Long id, @RequestBody TicketStatusUpdateRequest request){
        Ticket updatedTicket = ticketService.updateTicketStatus(id , request.getStatus());
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicketById(@PathVariable Long id){
        ticketService.deleteTicketById(id);
    }
}
