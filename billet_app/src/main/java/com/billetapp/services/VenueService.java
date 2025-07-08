package com.billetapp.services;

import com.billetapp.entity.Venue;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.VenueInterface;
import com.billetapp.repository.VenueRepository;

import java.util.List;
import java.util.Scanner;

public class VenueService implements VenueInterface {
    private final VenueRepository venueRepo;
    private final Scanner scanner;

    public VenueService(VenueRepository venueRepo, Scanner scanner) {
        this.venueRepo = venueRepo;
        this.scanner   = scanner;
    }

    @Override
    public void createVenue() {
        System.out.println("Création d'un nouveau lieu");
        System.out.print("Nom : ");
        String name   = scanner.nextLine().trim();
        System.out.print("Adresse: ");
        String street = scanner.nextLine().trim();
        System.out.print("Ville: ");
        String city   = scanner.nextLine().trim();
        System.out.print("Capacité: ");
        int capacity;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Capacité invalide");
            return;
        }

        Venue v = new Venue(name, street, city, capacity);
        venueRepo.storeVenue(v);
        System.out.println("Lieu créé : ");
    }

    @Override
    public void showVenue() {
        System.out.println("Afficher un lieu");
        System.out.print("Nom du lieu : ");
        String name = scanner.nextLine().trim();
        try {
            Venue v = venueRepo.findByName(name);
            System.out.println(v);
        } catch (NotFoundException e) {
            System.out.println( e.getMessage());
        }
    }

    @Override
    public void updateVenue() {
        System.out.println("Mise à jour d'un lieu");
        System.out.print("Nom du lieu: ");
        String name = scanner.nextLine().trim();

        Venue v;
        try {
            v = venueRepo.findByName(name);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Nouveau nom : ");
        String newName = scanner.nextLine().trim();
        if (!newName.isBlank()) v.setName(newName);

        System.out.print("Nouvelle adresse : ");
        String newStreet = scanner.nextLine().trim();
        if (!newStreet.isBlank()) v.setStreet(newStreet);

        System.out.print("Nouvelle ville: ");
        String newCity = scanner.nextLine().trim();
        if (!newCity.isBlank()) v.setCity(newCity);

        System.out.print("Nouvelle capacité : ");
        String capStr = scanner.nextLine().trim();
        if (!capStr.isBlank()) {
            try {
                v.setCapacity(Integer.parseInt(capStr));
            } catch (NumberFormatException e) {
                System.out.println("format invalide.");
            }
        }

        System.out.println("Lieu mis à jour : " + v);
    }

    @Override
    public void deleteVenue() {
        System.out.println("Suppression d'un lieu");
        System.out.print("Nom du lieu : ");
        String name = scanner.nextLine().trim();
        try {
            Venue v = venueRepo.findByName(name);
            venueRepo.deleteVenue(v);
            System.out.println("Lieu supprimé :");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showAllVenues() {
        System.out.println(" Liste des lieux ");
        List<Venue> list = venueRepo.getAllVenues();
        if (list.isEmpty()) {
            System.out.println("Aucun lieux.");
        } else {
            list.forEach(v -> System.out.println("   • " + v));
        }
    }
}