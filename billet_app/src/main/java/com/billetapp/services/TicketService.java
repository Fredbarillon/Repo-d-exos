package com.billetapp.services;

import com.billetapp.entity.Buyer;
import com.billetapp.entity.Event;
import com.billetapp.entity.Ticket;
import com.billetapp.enums.Type;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.TicketInterface;
import com.billetapp.repository.BuyerRepository;
import com.billetapp.repository.EventRepository;
import com.billetapp.repository.TicketRepository;

import java.util.List;
import java.util.Scanner;

public class TicketService implements TicketInterface {
    private  final BuyerRepository buyerRepository;
    private  final EventRepository eventRepository;
    private  final TicketRepository ticketRepository;
    private  final Scanner scanner;

    public TicketService(BuyerRepository buyerRepo, EventRepository eventRepo,
                         TicketRepository ticketRepo, Scanner scanner) {
        this.buyerRepository = buyerRepo;
        this.eventRepository = eventRepo;
        this.ticketRepository = ticketRepo;
        this.scanner = scanner;
    }

    @Override
    public void bookTicket() {
        System.out.println("Création d'un nouveau ticket");
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();
        Buyer buyer = buyerRepository.findByFullName(firstName, lastName);
        if (buyer == null) {
            System.out.println("Client non trouvé");
            return;
        }

        System.out.print("Nom de l'événement : ");
        String eventName = scanner.nextLine();
        Event event = eventRepository.findByName(eventName);
        if (event == null) {
            System.out.println("Événement non trouvé");
            return;
        }

        int soldTickets = event.getTickets().size();
        int capacity = event.getTotalTickets();
        if (soldTickets >= capacity) {
            System.out.println("Evenement complet ");
            return;
        }

        System.out.print("Type de billet (STANDARD/GOLD/VIP) : ");
        String typeInput = scanner.nextLine().trim().toUpperCase();
        Type ticketType;
        try {
            ticketType = Type.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println(" Type de billet invalide");
            return;
        }

        int ticketNumber = soldTickets + 1;

        Ticket newTicket = new Ticket(buyer, ticketNumber, event, ticketType);
        ticketRepository.storeTicket(newTicket);
        buyer.getBuyerTickets().add(newTicket);
        event.getTickets().add(newTicket);
        System.out.println("✅ Ticket créé avec succès : " + newTicket);
    }

    @Override
    public void cancelTicket() {
        System.out.println("---Annulation d'un ticket---");
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();
        Buyer buyer = buyerRepository.findByFullName(firstName, lastName);
        if (buyer == null) {
            System.out.println("client non trouvé.");
            return;
        }
        System.out.print("événement : ");
        String eventName = scanner.nextLine();
        Event event = eventRepository.findByName(eventName);
        if (event == null) {
            System.out.println("Evénement non trouvé.");
            return;
        }

        Ticket ticketToCancel = ticketRepository.findByBuyerAndEvent(buyer, event);
        if (ticketToCancel == null) {
            System.out.println("Aucun ticket trouvé.");
            return;
        }

        ticketRepository.deleteTicket(ticketToCancel);
        buyer.getBuyerTickets().remove(ticketToCancel);
        event.getTickets().remove(ticketToCancel);
        System.out.println(" Ticket annulé : " + ticketToCancel);
    }

    @Override
    public void changeTicketType() {
        System.out.println("--- Changer le type d'un ticket ---");

        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();
        Buyer buyer;
        try {
            buyer = buyerRepository.findByFullName(firstName, lastName);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nom de l'événement : ");
        String eventName = scanner.nextLine();
        Event event = eventRepository.findByName(eventName);
        if (event == null) {
            System.out.println("Evénement introuvable.");
            return;
        }
        Ticket ticket = ticketRepository.findByBuyerAndEvent(buyer, event);
        if (ticket == null) {
            System.out.println("Ticket introuvable");
            return;
        }

        System.out.print("Nouveau type (STANDARD/VIP) : ");
        String typeInput = scanner.nextLine().trim().toUpperCase();
        Type newType;
        try {
            newType = Type.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Type de billet invalide.");
            return;
        }

        ticket.setType(newType);
        System.out.println("Ticket mis à jour : " + ticket);
    }

    @Override
    public void changeTicketEvent() {
        System.out.println("--- Changer l'événement d'un ticket ---");
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();
        Buyer buyer = buyerRepository.findByFullName(firstName, lastName);
        if (buyer == null) {
            System.out.println("Acheteur introuvable.");
            return;
        }
        System.out.print("Événement actuel : ");
        String oldEventName = scanner.nextLine();
        Event oldEvent = eventRepository.findByName(oldEventName);
        if (oldEvent == null) {
            System.out.println(" Événement introuvable.");
            return;
        }
        Ticket ticket = ticketRepository.findByBuyerAndEvent(buyer, oldEvent);
        if (ticket == null) {
            System.out.println("Aucun ticket trouvé.");
            return;
        }

        System.out.print("Nouvel événement : ");
        String newEventName = scanner.nextLine();
        Event newEvent = eventRepository.findByName(newEventName);
        if (newEvent == null) {
            System.out.println("Evénement introuvable.");
            return;
        }

        oldEvent.getTickets().remove(ticket);
        ticket.setEvent(newEvent);
        newEvent.getTickets().add(ticket);
        System.out.println("Ticket transféré vers le nouvel événement : " + ticket);
    }

    @Override
    public void showTicketsByBuyer() {
        System.out.println("---Afficher les tickets par acheteur---");
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();
        Buyer buyer;
        try {
            buyer = buyerRepository.findByFullName(firstName, lastName);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Ticket> tickets = buyer.getBuyerTickets();
        if (tickets.isEmpty()) {
            System.out.println("Aucun ticket réservé ");
        } else {
            System.out.println("Tickets:");
            for (Ticket t : tickets) {
                System.out.println(" -> " + t);
            }
        }
    }

    @Override
    public void showTicketsByEvent() {
        System.out.println("---Afficher les tickets par événement---");
        System.out.print("Nom de l'événement : ");
        String eventName = scanner.nextLine();

        Event event = eventRepository.findByName(eventName);
        if (event == null) {
            System.out.println("Événement introuvable.");
            return;
        }

        List<Ticket> tickets = event.getTickets();
        if (tickets == null || tickets.isEmpty()) {
            System.out.println("Aucun ticket réservé pour l'événement ");
        } else {
            System.out.println("Tickets pour l'événement  " + eventName + " :");
            for (Ticket t : tickets) {
                System.out.println(" -> " + t);
            }
        }
    }
}
