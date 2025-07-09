package com.billetapp.services;

import com.billetapp.entity.Buyer;
import com.billetapp.entity.Event;
import com.billetapp.entity.Ticket;
import com.billetapp.enums.Type;
import com.billetapp.exceptions.InvalidInputException;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.TicketInterface;
import com.billetapp.repository.BuyerRepository;
import com.billetapp.repository.EventRepository;
import com.billetapp.repository.TicketRepository;

import java.util.List;
import java.util.Scanner;

public class TicketService implements TicketInterface {
    private final BuyerRepository buyerRepo;
    private final EventRepository eventRepo;
    private final TicketRepository ticketRepo;
    private final Scanner scanner;

    public TicketService(BuyerRepository buyerRepo,
                         EventRepository eventRepo,
                         TicketRepository ticketRepo,
                         Scanner scanner) {
        this.buyerRepo = buyerRepo;
        this.eventRepo = eventRepo;
        this.ticketRepo = ticketRepo;
        this.scanner = scanner;
    }

    @Override
    public void bookTicket() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();
            Buyer buyer = buyerRepo.findByFullName(firstName, lastName);

            System.out.print("Nom de l'événement : ");
            String eventName = scanner.nextLine().trim();
            Event event = eventRepo.findByName(eventName);

            int sold = event.getTickets().size();
            if (sold >= event.getTotalTickets()) {
                throw new InvalidInputException("Ticket non disponible, événement complet.");
            }

            System.out.print("Type (STANDARD/GOLD/VIP) : ");
            String typeInput = scanner.nextLine().trim().toUpperCase();
            Type type;
            try {
                type = Type.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException("Type invalide. Choix: STANDARD, GOLD ou VIP.");
            }

            int ticketNumber = sold + 1;
            Ticket ticket = new Ticket(buyer, ticketNumber, event, type);
            ticketRepo.storeTicket(ticket);
            buyer.getBuyerTickets().add(ticket);
            event.getTickets().add(ticket);

            System.out.println("Ticket réservé.");
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void cancelTicket() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();
            Buyer buyer = buyerRepo.findByFullName(firstName, lastName);

            System.out.print("Nom de l'événement : ");
            String eventName = scanner.nextLine().trim();
            Event event = eventRepo.findByName(eventName);

            Ticket ticket = ticketRepo.findByBuyerAndEvent(buyer, event);
            ticketRepo.deleteTicket(ticket.getTicketNumber());
            buyer.getBuyerTickets().remove(ticket);
            event.getTickets().remove(ticket);

            System.out.println("Ticket annulé.");
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void changeTicketType() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();
            Buyer buyer = buyerRepo.findByFullName(firstName, lastName);

            System.out.print("Nom de l'événement : ");
            String eventName = scanner.nextLine().trim();
            Event event = eventRepo.findByName(eventName);

            Ticket ticket = ticketRepo.findByBuyerAndEvent(buyer, event);

            System.out.print("Nouveau type (STANDARD/GOLD/VIP) : ");
            String typeInput = scanner.nextLine().trim().toUpperCase();
            Type type;
            try {
                type = Type.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException("Type invalide. Choix: STANDARD, GOLD ou VIP.");
            }

            ticket.setType(type);
            System.out.println("Type de ticket modifié.");
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void changeTicketEvent() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();
            Buyer buyer = buyerRepo.findByFullName(firstName, lastName);

            System.out.print("Événement actuel : ");
            String oldName = scanner.nextLine().trim();
            Event oldEvent = eventRepo.findByName(oldName);

            Ticket ticket = ticketRepo.findByBuyerAndEvent(buyer, oldEvent);

            System.out.print("Nouvel événement : ");
            String newName = scanner.nextLine().trim();
            Event newEvent = eventRepo.findByName(newName);

            oldEvent.getTickets().remove(ticket);
            ticket.setEvent(newEvent);
            newEvent.getTickets().add(ticket);

            System.out.println("Événement du ticket modifié.");
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showTicketsByBuyer() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();

            Buyer buyer = buyerRepo.findByFullName(firstName, lastName);
            for (Ticket t : buyer.getBuyerTickets()) {
                System.out.println(t);
            }
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showTicketsByEvent() {
        try {
            System.out.print("Nom de l'événement : ");
            String eventName = scanner.nextLine().trim();
            Event event = eventRepo.findByName(eventName);
            for (Ticket t : event.getTickets()) {
                System.out.println(t);
            }
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}