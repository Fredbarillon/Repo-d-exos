// src/main/java/com/billetapp/repository/TicketRepository.java
package com.billetapp.repository;

import com.billetapp.entity.Buyer;
import com.billetapp.entity.Event;
import com.billetapp.entity.Ticket;
import com.billetapp.entity.Venue;
import com.billetapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private final List<Ticket> tickets = new ArrayList<>();


    public void storeTicket(Ticket ticket) {
        tickets.add(ticket);
    }


    public Ticket findByBuyerAndEvent(Buyer buyer, Event event) {
        for (Ticket t : tickets) {
            if (t.getBuyer().equals(buyer) && t.getEvent().equals(event)) {
                return t;
            }
        }
        throw new NotFoundException("Ticket non trouvé pour "
                + buyer.getFirstName() + " " + buyer.getLastName()
                + event.getName() + "\"");
    }


    public Ticket findByNb(int ticketNumber) {
        for (Ticket t : tickets) {
            if (t.getTicketNumber() == ticketNumber) {
                return t;
            }
        }
        throw new NotFoundException("Ticket non trouvé " + ticketNumber);
    }

    public void deleteTicket(int ticketNumber) {
        Ticket toDelete = findByNb(ticketNumber);
        tickets.removeIf(t -> t.getTicketNumber() == ticketNumber);
    }
}
