// src/main/java/com/billetapp/ihm/Ihm.java
package com.billetapp.ihm;

import com.billetapp.interfaces.BuyerInterface;
import com.billetapp.interfaces.EventInterface;
import com.billetapp.interfaces.TicketInterface;
import com.billetapp.interfaces.VenueInterface;

import java.util.Scanner;

public class Ihm {
    private final Scanner scanner;
    private final BuyerInterface buyerService;
    private final EventInterface eventService;
    private final TicketInterface ticketService;
    private final VenueInterface venueService;

    public Ihm(Scanner scanner,
               BuyerInterface buyerService,
               EventInterface eventService,
               TicketInterface ticketService,
               VenueInterface venueService) {
        this.scanner       = scanner;
        this.buyerService  = buyerService;
        this.eventService  = eventService;
        this.ticketService = ticketService;
        this.venueService  = venueService;
    }

    public void start() {
        while (true) {
            System.out.println("""
                
                --- MENU PRINCIPAL ---
                1. Gestion des acheteurs
                2. Gestion des lieux
                3. Gestion des événements
                4. Gestion des tickets
                5. Quitter
                """);
            System.out.print("Choix : ");
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> buyerMenu();
                case "2" -> venueMenu();
                case "3" -> eventMenu();
                case "4" -> ticketMenu();
                case "5" -> {
                    System.out.println("Fin de programme");
                    return;
                }
                default -> System.out.println("Option invalide.");
            }
        }
    }

    private void buyerMenu() {
        while (true) {
            System.out.println("""
                
                --- MENU ACHETEUR ---
                1. Créer un acheteur
                2. Afficher un acheteur
                3. Modifier un acheteur
                4. Supprimer un acheteur
                5. Lister tous les acheteurs
                6. Retour
                """);
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> buyerService.createBuyer();
                case "2" -> buyerService.showBuyer();
                case "3" -> buyerService.updateBuyer();
                case "4" -> buyerService.deleteBuyer();
                case "5" -> buyerService.showAllBuyers();
                case "6" -> { return; }
                default  -> System.out.println("Option invalide.");
            }
        }
    }

    private void venueMenu() {
        while (true) {
            System.out.println("""
                
                --- MENU LIEU (Venue) ---
                1. Créer un lieu
                2. Afficher un lieu
                3. Modifier un lieu
                4. Supprimer un lieu
                5. Lister tous les lieux
                6. Retour
                """);
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> venueService.createVenue();
                case "2" -> venueService.showVenue();
                case "3" -> venueService.updateVenue();
                case "4" -> venueService.deleteVenue();
                case "5" -> venueService.showAllVenues();
                case "6" -> { return; }
                default  -> System.out.println("Option invalide.");
            }
        }
    }

    private void eventMenu() {
        while (true) {
            System.out.println("""
                
                --- MENU ÉVÉNEMENT ---
                1. Créer un événement
                2. Afficher un événement
                3. Modifier un événement
                4. Supprimer un événement
                5. Lister les événements
                6. Afficher tickets par événement
                7. Retour
                """);
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> eventService.createEvent();
                case "2" -> eventService.showEvent();
                case "3" -> eventService.updateEvent();
                case "4" -> eventService.deleteEvent();
                case "5" -> eventService.showAllEvents();
                case "6" -> ticketService.showTicketsByEvent();
                case "7" -> { return; }
                default  -> System.out.println("Option invalide.");
            }
        }
    }

    private void ticketMenu() {
        while (true) {
            System.out.println("""
                
                --- MENU TICKET ---
                1. Réserver un ticket
                2. Annuler un ticket
                3. Changer le type de ticket
                4. Changer l'événement d'un ticket
                5. Afficher tickets par acheteur
                6. Retour
                """);
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> ticketService.bookTicket();
                case "2" -> ticketService.cancelTicket();
                case "3" -> ticketService.changeTicketType();
                case "4" -> ticketService.changeTicketEvent();
                case "5" -> ticketService.showTicketsByBuyer();
                case "6" -> { return; }
                default  -> System.out.println("Option invalide.");
            }
        }
    }
}
