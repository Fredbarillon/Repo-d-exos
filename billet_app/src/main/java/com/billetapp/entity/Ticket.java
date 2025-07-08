package com.billetapp.entity;

import com.billetapp.enums.Type;

public class Ticket {
    private int ticketNumber;
    private Buyer buyer;
    private Event event;
    private Type type;

    public Ticket(Buyer buyer, int ticketNumber, Event event, Type type) {
        this.buyer = buyer;
        this.ticketNumber = ticketNumber;
        this.event = event;
        this.type = type;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "n°" + ticketNumber +
                ", type=" + type +
                ", event=" + event.getName() +
                ", buyer=" + buyer.getFirstName() + " " + buyer.getLastName() +
                '}';
    }
}
