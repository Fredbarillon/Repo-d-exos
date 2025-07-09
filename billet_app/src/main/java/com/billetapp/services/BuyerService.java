package com.billetapp.services;

import com.billetapp.entity.Buyer;
import com.billetapp.exceptions.InvalidInputException;
import com.billetapp.exceptions.NotFoundException;
import com.billetapp.interfaces.BuyerInterface;
import com.billetapp.repository.BuyerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerService implements BuyerInterface {
    private final BuyerRepository buyerRepository;
    private final Scanner scanner;

    public BuyerService(BuyerRepository repo, Scanner scanner) {
        this.buyerRepository = repo;
        this.scanner = scanner;
    }

    @Override
    public void createBuyer() {
        try {
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                throw new InvalidInputException("Le nom ne peut pas être vide.");
            }

            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                throw new InvalidInputException("Le prénom ne peut pas être vide.");
            }

            System.out.print("Adresse : ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                throw new InvalidInputException("L'adresse ne peut pas être vide.");
            }

            System.out.print("Âge : ");
            String ageInput = scanner.nextLine().trim();
            if (!ageInput.matches("\\d+")) {
                throw new InvalidInputException("L'âge doit être un nombre entier.");
            }

            System.out.print("Téléphone : ");
            String phoneInput = scanner.nextLine().trim();
            if (!phoneInput.matches("\\d+")) {
                throw new InvalidInputException("Le numéro de téléphone doit contenir uniquement des chiffres.");
            }
            int phoneNumber = Integer.parseInt(phoneInput);

            Buyer buyer = new Buyer(address, firstName, lastName, ageInput, phoneNumber, new ArrayList<>());
            buyerRepository.storeBuyer(buyer);
            System.out.println("Acheteur ajouté.");
        } catch (InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showBuyer() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();

            Buyer buyer = buyerRepository.findByFullName(firstName, lastName);
            System.out.println(buyer);
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void updateBuyer() {
        try {
            System.out.print("Prénom de l'acheteur à modifier : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom de l'acheteur à modifier : ");
            String lastName = scanner.nextLine().trim();

            Buyer buyer = buyerRepository.findByFullName(firstName, lastName);


            System.out.print("Nouvelle adresse (" + buyer.getAddress() + ") : ");
            String address = scanner.nextLine().trim();
            if (!address.isEmpty()) {
                buyer.setAddress(address);
            }

            System.out.print("Nouvel âge (" + buyer.getAge() + ") : ");
            String ageInput = scanner.nextLine().trim();
            if (!ageInput.isEmpty()) {
                if (!ageInput.matches("\\d+")) {
                    throw new InvalidInputException("L'âge doit être un entier.");
                }
                buyer.setAge(ageInput);
            }

            System.out.print("Nouveau téléphone (" + buyer.getPhoneNumber() + ") : ");
            String phoneInput = scanner.nextLine().trim();
            if (!phoneInput.isEmpty()) {
                if (!phoneInput.matches("\\d+")) {
                    throw new InvalidInputException("Le téléphone doit être numérique.");
                }
                buyer.setPhoneNumber(Integer.parseInt(phoneInput));
            }

            System.out.println("Acheteur mis à jour : " + buyer);
        } catch (NotFoundException | InvalidInputException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void deleteBuyer() {
        try {
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine().trim();
            buyerRepository.delete(firstName, lastName);
            System.out.println("Acheteur supprimé.");
        } catch (NotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void showAllBuyers() {
        List<Buyer> buyers = buyerRepository.getAllBuyers();
        for (Buyer b : buyers) {
            System.out.println(b);
        }
    }
}