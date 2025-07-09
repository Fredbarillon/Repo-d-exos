package com.billetapp.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Event {
    private String name;
    private Venue venue;
    private LocalDate date;
    private LocalTime time;
    private int totalTickets;
    private List<Ticket> tickets;

    public Event(String name, Venue venue, LocalTime time, LocalDate date, int totalTickets, List<Ticket> tickets) {
        this.name = name;
        this.venue = venue;
        this.time = time;
        this.date = date;
        this.totalTickets = totalTickets;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", venue=" + venue +
                ", date=" + date +
                ", time=" + time +
                ", totalTickets=" + totalTickets +
                ", tickets=" + tickets +
                '}';
    }
}
