package com.billetapp.repository;

import com.billetapp.entity.Event;
import com.billetapp.entity.Venue;
import com.billetapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private final List<Event> events = new ArrayList<>();


    public void addListFromMain(List<Event> eventsToAdd) {
        events.addAll(eventsToAdd);
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

    public void removeEvent(String name) {
        Event toDelete = findByName(name);
        events.removeIf(v -> v.getName().equalsIgnoreCase(name));
    }

    public List<Event> findAll() {
        return new ArrayList<>(events);
    }
}
