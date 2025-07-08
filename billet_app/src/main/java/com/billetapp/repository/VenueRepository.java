package com.billetapp.repository;

import com.billetapp.entity.Venue;
import com.billetapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class VenueRepository {
    private final List<Venue> venues = new ArrayList<>();


    public void addListFromMain(Venue venue) {
        venues.add(venue);
    }

    /**
     * Recherche un lieu par son nom
     * @throws NotFoundException si aucun lieu ne porte ce nom
     */
    public Venue findByName(String name) {
        for (Venue v : venues) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        throw new NotFoundException("Lieu non trouvé : " + name);
    }

    /** Supprime un lieu existant */
    public void deleteVenue(Venue venue) {
        venues.remove(venue);
    }

    /** Renvoie la liste de tous les lieux */
    public List<Venue> findAll() {
        return new ArrayList<>(venues);
    }
}
