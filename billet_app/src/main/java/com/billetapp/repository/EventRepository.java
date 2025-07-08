package com.billetapp.repository;

import com.billetapp.entity.Event;
import com.billetapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private final List<Event> events = new ArrayList<>();


    public void addListFromMain(List<Event> eventsToAdd) {

        events.add(eventsToAdd);
    }

    public void storeEvent(Event event) {
        events.add(event);
    }
    /**
     * @throws NotFoundException quand l'événement ne porte pas ce nom.
     */
    public Event findByName(String name) {
        for (Event e : events) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        throw new NotFoundException("Événement non trouvé : " + name);
    }

    public void removeEvent(Event event) {

    }

    public List<Event> findAll() {
        return new ArrayList<>(events);
    }
}
