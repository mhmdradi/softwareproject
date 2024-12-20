/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelapp;

import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private static ReservationManager instance;
    private final List<String> reservations = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    private ReservationManager() {}

    public static synchronized ReservationManager getInstance() {
        if (instance == null) {
            instance = new ReservationManager();
        }
        return instance;
    }

    public void addReservation(String reservationDetails) {
        reservations.add(reservationDetails);
        notifyObservers("New Reservation: " + reservationDetails);
    }

    public List<String> getReservations() {
        return new ArrayList<>(reservations);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}


