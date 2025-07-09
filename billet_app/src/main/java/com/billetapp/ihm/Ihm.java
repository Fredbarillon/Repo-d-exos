package com.billetapp.ihm;

import com.billetapp.exceptions.InvalidInputException;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.BuyerInterface;
import com.billetapp.interfaces.EventInterface;
import com.billetapp.interfaces.TicketInterface;
import com.billetapp.interfaces.VenueInterface;

import java.util.Scanner;

public class Ihm {
    private final Scanner scanner;
    private final BuyerInterface buyerService;
    private final VenueInterface venueService;
    private final EventInterface eventService;
    private final TicketInterface ticketService;

    public Ihm(Scanner scanner,
               BuyerInterface buyerService,
               EventInterface eventService,
               TicketInterface ticketService,
               VenueInterface venueService) {
        this.scanner = scanner;
        this.buyerService = buyerService;
        this.venueService = venueService;
        this.eventService = eventService;
        this.ticketService = ticketService;
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
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> {
                    try { buyerService.createBuyer(); }
                    catch (InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "2" -> {
                    try { buyerService.showBuyer(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "3" -> {
                    try { buyerService.updateBuyer(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "4" -> {
                    try { buyerService.deleteBuyer(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "5" -> buyerService.showAllBuyers();
                case "6" -> { return; }
                default -> System.out.println("Option invalide.");
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
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> {
                    try { venueService.createVenue(); }
                    catch (InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "2" -> {
                    try { venueService.showVenue(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "3" -> {
                    try { venueService.updateVenue(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "4" -> {
                    try { venueService.deleteVenue(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "5" -> venueService.showAllVenues();
                case "6" -> { return; }
                default -> System.out.println("Option invalide.");
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
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> {
                    try { eventService.createEvent(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "2" -> {
                    try { eventService.showEvent(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "3" -> {
                    try { eventService.updateEvent(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "4" -> {
                    try { eventService.deleteEvent(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "5" -> eventService.showAllEvents();
                case "6" -> {
                    try { ticketService.showTicketsByEvent(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "7" -> { return; }
                default -> System.out.println("Option invalide.");
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
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> {
                    try { ticketService.bookTicket(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "2" -> {
                    try { ticketService.cancelTicket(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "3" -> {
                    try { ticketService.changeTicketType(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "4" -> {
                    try { ticketService.changeTicketEvent(); }
                    catch (NotFoundException | InvalidInputException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "5" -> {
                    try { ticketService.showTicketsByBuyer(); }
                    catch (NotFoundException e) { System.out.println("Erreur : " + e.getMessage()); }
                }
                case "6" -> { return; }
                default -> System.out.println("Option invalide.");
            }
        }
    }
}