package com.airline.airport_management_demo.services;

import com.airline.airport_management_demo.entities.Ticket;
import com.airline.airport_management_demo.reporitories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setTicketNumber(ticketDetails.getTicketNumber());
        ticket.setFlight(ticketDetails.getFlight());
        ticket.setPassenger(ticketDetails.getPassenger());
        ticket.setSeatNumber(ticketDetails.getSeatNumber());
        ticket.setPrice(ticketDetails.getPrice());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}

