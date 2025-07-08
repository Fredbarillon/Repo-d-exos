package com.billetapp.entity;

import java.util.List;

public class Buyer {
    private String lastName;
    private String firstName;
    private String address;
    private String age;
    private int phoneNumber;
    private List<Ticket> buyerTickets;

    public Buyer(String address, String firstName, String lastName, String age, int phoneNumber, List<Ticket> buyerTickets) {
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.buyerTickets = buyerTickets;
    }

    public List<Ticket> getBuyerTickets() {
        return buyerTickets;
    }

    public void setBuyerTickets(List<Ticket> buyerTickets) {
        this.buyerTickets = buyerTickets;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", buyerTickets=" + buyerTickets +
                '}';
    }
}
