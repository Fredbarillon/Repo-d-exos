package com.billetapp.repository;

import com.billetapp.entity.Venue;
import com.billetapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class VenueRepository {
    private final List<Venue> venues = new ArrayList<>();

    public void addListFromMain(List<Venue> venuesToAdd) {
        venues.addAll(venuesToAdd);
    }

    public void storeVenue(Venue venue) {
        venues.add(venue);
    }

    public Venue findByName(String name) {
        for (Venue v : venues) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        throw new NotFoundException("Lieu non trouvé : ");
    }

    public void deleteVenue(String name) {

        Venue toDelete = findByName(name);
        venues.removeIf(v -> v.getName().equalsIgnoreCase(name));
    }

    public List<Venue> getAllVenues() {
        return new ArrayList<>(venues);
    }
}
