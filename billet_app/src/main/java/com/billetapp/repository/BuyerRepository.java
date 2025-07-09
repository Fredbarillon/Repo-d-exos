package com.billetapp.repository;

import com.billetapp.entity.Buyer;
import com.billetapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BuyerRepository {
    private final List<Buyer> buyers = new ArrayList<>();

    public void addListFromMain(List<Buyer> buyersToAdd) {
        buyers.addAll(buyersToAdd);
    }

    public void storeBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    public Buyer findByFullName(String firstName, String lastName) {
        for (Buyer b : buyers) {
            if (b.getFirstName().equalsIgnoreCase(firstName) && b.getLastName().equalsIgnoreCase(lastName)) return b;
        }
        throw new NotFoundException("Acheteur non trouvé : " + firstName + " " + lastName);
    }

    public void delete(String firstName, String lastName) {
        Buyer toDelete = findByFullName(firstName, lastName);
        buyers.removeIf(b -> b.getFirstName().equalsIgnoreCase(firstName) && b.getLastName().equalsIgnoreCase(lastName));
    }
    public List<Buyer> getAllBuyers() {
        return buyers;
    }
}
