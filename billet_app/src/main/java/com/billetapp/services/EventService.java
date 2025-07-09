package com.billetapp.services;

import com.billetapp.entity.Event;
import com.billetapp.entity.Venue;
import com.billetapp.exceptions.InvalidInputException;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.EventInterface;
import com.billetapp.repository.EventRepository;
import com.billetapp.repository.VenueRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventService implements EventInterface {
    private final EventRepository eventRepo;
    private final VenueRepository venueRepo;
    private final Scanner scanner;

    public EventService(EventRepository eventRepo, VenueRepository venueRepo, Scanner scanner) {
        this.eventRepo = eventRepo;
        this.venueRepo = venueRepo;
        this.scanner = scanner;
    }

    @Override
    public void createEvent() {
        try {
            System.out.print("Nom : ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                throw new InvalidInputException("Le nom de l'événement ne peut pas être vide.");
            }

            System.out.print("Nom du lieu : ");
            String venueName = scanner.nextLine().trim();
            Venue venue = venueRepo.findByName(venueName);

            System.out.print("Date (yyyy-MM-dd) : ");
            String dateStr = scanner.nextLine().trim();
            LocalDate date;
            try {
                date = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Format de date invalide, attendu yyyy-MM-dd.");
            }

            System.out.print("Heure (HH:mm) : ");
            String timeStr = scanner.nextLine().trim();
            LocalTime time;
            try {
                time = LocalTime.parse(timeStr);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Format de l'heure invalide, attendu HH:mm.");
            }

            System.out.print("Capacité totale : ");
            String totalStr = scanner.nextLine().trim();
            if (!totalStr.matches("\\d+")) {
                throw new InvalidInputException("La capacité doit être un nombre entier.");
            }
            int total = Integer.parseInt(totalStr);

            Event event = new Event(name, venue, time, date, total, new ArrayList<>());
            eventRepo.storeEvent(event);
            System.out.println("Événement créé.");
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showEvent() {
        try {
            System.out.print("Nom : ");
            String name = scanner.nextLine().trim();
            Event event = eventRepo.findByName(name);
            System.out.println(event);
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void updateEvent() {
        try {
            System.out.print("Nom de l'événement à modifier : ");
            String name = scanner.nextLine().trim();
            Event event = eventRepo.findByName(name);


            System.out.print("Nouveau nom (" + event.getName() + ") : ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                event.setName(newName);
            }

            System.out.print("Nouvelle capacité (" + event.getTotalTickets() + ") : ");
            String capStr = scanner.nextLine().trim();
            if (!capStr.isEmpty()) {
                if (!capStr.matches("\\d+")) {
                    throw new InvalidInputException("La capacité doit être un entier.");
                }
                event.setTotalTickets(Integer.parseInt(capStr));
            }

            System.out.println("Événement mis à jour : " + event);
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void deleteEvent() {
        try {
            System.out.print("Nom : ");
            String name = scanner.nextLine().trim();
            eventRepo.removeEvent(name);
            System.out.println("Événement supprimé.");
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showAllEvents() {
        List<Event> events = eventRepo.findAll();
        for (Event e : events) {
            System.out.println(e);
        }
    }
}
