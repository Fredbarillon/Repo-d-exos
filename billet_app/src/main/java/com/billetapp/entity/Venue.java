package com.billetapp.entity;

public class Venue extends Address {
    private String name;
    private int capacity;

    public Venue(String name, String street, String city, int capacity) {
        super(street, city);
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", capacity= " + capacity +
                "address= " + super.toString() +
                '}';
    }
}
