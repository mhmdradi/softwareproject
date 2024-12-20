/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelapp;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessor {
    private static PaymentProcessor instance;
    private final List<String> payments = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    private PaymentProcessor() {}

    public static synchronized PaymentProcessor getInstance() {
        if (instance == null) {
            instance = new PaymentProcessor();
        }
        return instance;
    }

    public void addPayment(String paymentDetails) {
        payments.add(paymentDetails);
        notifyObservers("New Payment: " + paymentDetails);
    }

    public List<String> getPayments() {
        return new ArrayList<>(payments);
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
