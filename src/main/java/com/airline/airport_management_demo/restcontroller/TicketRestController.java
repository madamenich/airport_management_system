package com.airline.airport_management_demo.restcontroller;


import com.airline.airport_management_demo.entities.Ticket;
import com.airline.airport_management_demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/tickets")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.createTicket(ticket);
        return ResponseEntity.status(201).body(newTicket); // Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok) // 200 OK
                .orElseGet(() -> ResponseEntity.status(404).build()); // Not Found
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets); // 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        try {
            Ticket updatedTicket = ticketService.updateTicket(id, ticketDetails);
            return ResponseEntity.ok(updatedTicket); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Not Found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        try {
            ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Not Found
        }
    }
}
