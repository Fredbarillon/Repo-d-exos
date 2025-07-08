
package com.billetapp.services;

import com.billetapp.entity.Event;
import com.billetapp.entity.Venue;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.EventInterface;
import com.billetapp.repository.EventRepository;
import com.billetapp.repository.VenueRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EventService implements EventInterface {
    private final EventRepository eventRepo;
    private final VenueRepository venueRepo;
    private final Scanner scanner;

    public EventService(EventRepository eventRepo,
                        VenueRepository venueRepo,
                        Scanner scanner) {
        this.eventRepo   = eventRepo;
        this.venueRepo   = venueRepo;
        this.scanner     = scanner;
    }

    @Override
    public void createEvent() {
        System.out.println(" Création d'un nouvel événement");
        System.out.print("Nom : ");
        String name = scanner.nextLine().trim();

        System.out.print("Nom du lieu : ");
        String venueName = scanner.nextLine().trim();
        Venue venue;
        try {
            venue = venueRepo.findByName(venueName);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Date (yyyy-MM-dd) : ");
        String dateStr = scanner.nextLine().trim();
        System.out.print("Heure (HH:mm)     : ");
        String timeStr = scanner.nextLine().trim();

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Format date invalide, attendu yyyy-MM-dd");
            return;
        }

        LocalTime time;
        try {
            time = LocalTime.parse(timeStr);
        } catch (DateTimeParseException e) {
            System.out.println("Format heure invalide, attendu HH:mm");
            return;
        }

        System.out.print("Capacité totale : ");
        int total;
        try {
            total = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Capacité invalide");
            return;
        }

        Event event = new Event(name, venue, time, date, total, List.of());
        eventRepo.storeEvent(event);
        System.out.println("Événement créé : " + event);
    }

    @Override
    public void showEvent() {
        System.out.println("Afficher un événement");
        System.out.print("Nom : ");
        String name = scanner.nextLine().trim();
        try {
            Event e = eventRepo.findByName(name);
            System.out.println(e);
        } catch (NotFoundException e) {
            System.out.println( e.getMessage());
        }
    }

    @Override
    public void updateEvent() {
        System.out.println(" Mise à jour d'un événement");
        System.out.print("Evénement à modifier : ");
        String name = scanner.nextLine().trim();

        Event event;
        try {
            event = eventRepo.findByName(name);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Nouveau nom  : ");
        String newName = scanner.nextLine().trim();
        if (!newName.isBlank()) event.setName(newName);

        System.out.print("Nouvelle capacité : ");
        String capStr = scanner.nextLine().trim();
        if (!capStr.isBlank()) {
            try {
                event.setTotalTickets(Integer.parseInt(capStr));
            } catch (NumberFormatException ex) {
                System.out.println("Capacité non modifiée ");
            }
        }

        System.out.println("Événement mis à jour:  " + event);
    }

    @Override
    public void deleteEvent() {
        System.out.println(" Suppression d'un événement ");
        System.out.print("Nom : ");
        String name = scanner.nextLine().trim();
        Event event;
        try {
            event = eventRepo.findByName(name);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        eventRepo.removeEvent(event);
        System.out.println("Événement supprimé : " + name);
    }

    @Override
    public void showAllEvents() {
        System.out.println("Liste de tous les événements ");
        List<Event> events = eventRepo.findAll();
        if (events.isEmpty()) {
            System.out.println("Aucun événement trouvé.");
        } else {
            events.forEach(e -> System.out.println("   • " + e));
        }
    }
}
