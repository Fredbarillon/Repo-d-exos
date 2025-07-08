package com.billetapp.services;

import com.billetapp.entity.Buyer;
import com.billetapp.repository.BuyerRepository;
import com.billetapp.interfaces.BuyerInterface;

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
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();

        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();

        System.out.print("Adresse : ");
        String address = scanner.nextLine();

        System.out.print("Âge : ");
        String age = scanner.nextLine();

        System.out.print("Téléphone : ");
        int phone = Integer.parseInt(scanner.nextLine());

        Buyer buyer = new Buyer(address, firstName, lastName, age, phone, new ArrayList<>());
        buyerRepository.storeBuyer(buyer);
        System.out.println("Client ajoute");
    }

    @Override
    public void showBuyer() {
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();

        Buyer b = buyerRepository.findByFullName(firstName, lastName);
        if (b != null) System.out.println(b);
        else System.out.println("Client introuvable");
    }

    @Override
    public void updateBuyer() {
        System.out.println("Mise à jour d'un cLient");
        System.out.print("Prenom: ");
        String firstName = scanner.nextLine();
        System.out.print("Nom: ");
        String lastName = scanner.nextLine();

        Buyer buyer = buyerRepository.findByFullName(firstName, lastName);
        if (buyer == null) {
            System.out.println("Client introuvable.");
            return;
        }

        System.out.print("Nouvelle adresse: ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isBlank()) {
            buyer.setAddress(newAddress);
        }

        System.out.print("Nouveau prenom: ");
        String newFirstName = scanner.nextLine();
        if (!newFirstName.isBlank()) {
            buyer.setFirstName(newFirstName);
        }

        System.out.print("Nouveau nom : ");
        String newLastName = scanner.nextLine();
        if (!newLastName.isBlank()) {
            buyer.setLastName(newLastName);
        }

        System.out.print("Nouvel âge : ");
        String newAge = scanner.nextLine();
        if (!newAge.isBlank()) {
            buyer.setAge(newAge);
        }

        System.out.print("Nouveau téléphone (" + buyer.getPhoneNumber() + ") : ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isBlank()) {
            try {
                int phone = Integer.parseInt(newPhone);
                buyer.setPhoneNumber(phone);
            } catch (NumberFormatException e) {
                System.out.println("Numéro invalide.");
            }
        }

        System.out.println("Client mis à jour");
    }


    @Override
    public void deleteBuyer() {
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();

        Buyer b = buyerRepository.findByFullName(firstName, lastName);
        if (b != null) {
            buyerRepository.delete(b);
            System.out.println("Client supprimé.");
        } else {
            System.out.println("Client non trouvé");
        }
    }

    @Override
    public void showAllBuyers() {
        List<Buyer> buyers = buyerRepository.getAllBuyers();
        if (buyers.isEmpty()) {
            System.out.println("Client non trouvé");
            return;
        }
        System.out.println("Liste des clients :\n");
        for (Buyer b : buyers) {
            System.out.println(b);
            System.out.println("---------------");
        }
    }


}