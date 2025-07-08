package com.billetapp;

import com.billetapp.entity.*;
import com.billetapp.ihm.Ihm;
import com.billetapp.interfaces.BuyerInterface;
import com.billetapp.interfaces.EventInterface;
import com.billetapp.interfaces.TicketInterface;
import com.billetapp.interfaces.VenueInterface;
import com.billetapp.repository.BuyerRepository;
import com.billetapp.repository.EventRepository;
import com.billetapp.repository.TicketRepository;
import com.billetapp.repository.VenueRepository;
import com.billetapp.services.BuyerService;
import com.billetapp.services.EventService;
import com.billetapp.services.TicketService;
import com.billetapp.services.VenueService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Venue> venuesToAdd = List.of(
         new Venue("Le Bataclan", "50 Boulevard Voltaire", "Paris", 1500),
         new Venue("Stade Gerland", "353 Avenue Jean Jaurès", "Lyon", 35000),
         new Venue("La Belle Électrique", "12 Esplanade Andry Farcy", "Grenoble", 900)
         );
        VenueRepository venueRepo  = new VenueRepository();
        venuesToAdd.forEach(venueRepo::addListFromMain);
        Venue v1 = venuesToAdd.get(0);
        Venue v2 = venuesToAdd.get(1);
        Venue v3 = venuesToAdd.get(2);

        List<Event> eventsToAdd = List.of(
                new Event("Rock en Seine", v1, LocalTime.of(20, 0), LocalDate.of(2025, 8, 24), 1500, new ArrayList<>()),
                new Event("Lyon Metal Fest", v2, LocalTime.of(18, 30), LocalDate.of(2025, 7, 15), 20000, new ArrayList<>()),
                new Event("Techno Parade", v1, LocalTime.of(22, 0), LocalDate.of(2025, 9, 7), 1200, new ArrayList<>()),
                new Event("Jazz à Vienne", v3, LocalTime.of(19, 0), LocalDate.of(2025, 6, 28), 900, new ArrayList<>()),
                new Event("Electro Night", v3, LocalTime.of(23, 0), LocalDate.of(2025, 10, 31), 850, new ArrayList<>())
        );
        EventRepository eventRepo = new EventRepository();
        eventRepo.addListFromMain(eventsToAdd);

        List<Buyer> buyersToAdd = List.of(
                new Buyer("10 rue Victor Hugo, Paris", "Alice", "Dupont", "25", 612345678, new ArrayList<>()),
                new Buyer("21 avenue des Champs, Lyon", "Bruno", "Martin", "32", 634567890, new ArrayList<>()),
                new Buyer("33 impasse des Lilas, Marseille", "Carla", "Moreau", "28", 676543210, new ArrayList<>()),
                new Buyer("48 place Bellecour, Lyon", "David", "Leroy", "35", 698123456, new ArrayList<>()),
                new Buyer("55 rue de la République, Lille", "Emma", "Dubois", "22", 645321789, new ArrayList<>()),
                new Buyer("12 allée des Cyprès, Nantes", "Fabien", "Rousseau", "30", 678945123, new ArrayList<>()),
                new Buyer("89 boulevard Haussmann, Paris", "Gaëlle", "Lefevre", "29", 690012345, new ArrayList<>()),
                new Buyer("17 chemin des Vignes, Bordeaux", "Hugo", "Noel", "31", 699876543, new ArrayList<>()),
                new Buyer("3 rue du Stade, Toulouse", "Inès", "Petit", "27", 655555555, new ArrayList<>()),
                new Buyer("5 quai de la Douane, Strasbourg", "Jules", "Fabre", "26", 667788990, new ArrayList<>())
        );
        BuyerRepository buyerRepo = new BuyerRepository();
        buyerRepo.addListFromMain(buyersToAdd);

        Scanner scanner = new Scanner(System.in);
        TicketRepository ticketRepo = new TicketRepository();

        TicketInterface ticketService = new TicketService(buyerRepo, eventRepo, ticketRepo, scanner);
        EventInterface eventService = new EventService(eventRepo, venueRepo, scanner);
        BuyerInterface buyerService = new BuyerService(buyerRepo, scanner);
        VenueInterface venueService = new VenueService(venueRepo, scanner);
        Ihm ihm = new Ihm(scanner, buyerService, eventService, ticketService,venueService);
        ihm.start();



    }

}
