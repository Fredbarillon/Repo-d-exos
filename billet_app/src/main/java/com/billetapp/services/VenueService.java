package com.billetapp.services;

import com.billetapp.entity.Venue;
import com.billetapp.exceptions.InvalidInputException;
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
        this.scanner = scanner;
    }

    @Override
    public void createVenue() {
        try {
            System.out.print("Nom du lieu : ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                throw new InvalidInputException("Le nom du lieu ne peut pas être vide.");
            }

            System.out.print("Rue : ");
            String street = scanner.nextLine().trim();
            if (street.isEmpty()) {
                throw new InvalidInputException("La rue ne peut pas être vide.");
            }

            System.out.print("Ville : ");
            String city = scanner.nextLine().trim();
            if (city.isEmpty()) {
                throw new InvalidInputException("La ville ne peut pas être vide.");
            }

            System.out.print("Capacité : ");
            String capStr = scanner.nextLine().trim();
            if (!capStr.matches("\\d+")) {
                throw new InvalidInputException("La capacité doit être un nombre entier.");
            }
            int capacity = Integer.parseInt(capStr);

            Venue venue = new Venue(name, street, city, capacity);
            venueRepo.storeVenue(venue);
            System.out.println("Lieu créé.");
        } catch (InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showVenue() {
        try {
            System.out.print("Nom du lieu : ");
            String name = scanner.nextLine().trim();
            Venue venue = venueRepo.findByName(name);
            System.out.println(venue);
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void updateVenue() {
        try {
            System.out.print("Nom du lieu à modifier : ");
            String name = scanner.nextLine().trim();
            Venue venue = venueRepo.findByName(name);


            System.out.print("Nouveau nom (" + venue.getName() + ") : ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                venue.setName(newName);
            }

            System.out.print("Nouvelle rue (" + venue.getStreet() + ") : ");
            String newStreet = scanner.nextLine().trim();
            if (!newStreet.isEmpty()) {
                venue.setStreet(newStreet);
            }

            System.out.print("Nouvelle ville (" + venue.getCity() + ") : ");
            String newCity = scanner.nextLine().trim();
            if (!newCity.isEmpty()) {
                venue.setCity(newCity);
            }

            System.out.print("Nouvelle capacité (" + venue.getCapacity() + ") : ");
            String capStr2 = scanner.nextLine().trim();
            if (!capStr2.isEmpty()) {
                if (!capStr2.matches("\\d+")) {
                    throw new InvalidInputException("La capacité doit être un entier.");
                }
                venue.setCapacity(Integer.parseInt(capStr2));
            }

            System.out.println("Lieu mis à jour.");
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void deleteVenue() {
        System.out.print("Nom du lieu à supprimer : ");
        String name = scanner.nextLine().trim();
        try {
            venueRepo.deleteVenue(name);
            System.out.println("Lieu supprimé.");
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    @Override
    public void showAllVenues() {
        List<Venue> venues = venueRepo.getAllVenues();
        for (Venue v : venues) {
            System.out.println(v);
        }
    }
}