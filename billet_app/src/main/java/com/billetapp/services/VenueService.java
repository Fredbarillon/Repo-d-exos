package com.billetapp.services;

import com.billetapp.interfaces.VenueInterface;
import com.billetapp.repository.VenueRepository;

import java.util.Scanner;

public class VenueService implements VenueInterface {
    private final VenueRepository venueRepository;
    private final Scanner scanner;
    public VenueService(VenueRepository venueRepository, Scanner scanner) {
        this.venueRepository = venueRepository;
        this.scanner = scanner;
    }

    @Override
    public void createVenue() {

    }

    @Override
    public void showVenue() {

    }

    @Override
    public void updateVenue() {

    }

    @Override
    public void deleteVenue() {

    }

    @Override
    public void showAllVenues() {

    }
}
