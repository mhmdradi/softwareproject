/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.hotelapp;

import javax.swing.*;
import java.awt.*;

public class HotelApp {
    public static void main(String[] args) {
        ReservationManager reservationManager = ReservationManager.getInstance();
        PaymentProcessor paymentProcessor = PaymentProcessor.getInstance();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hotel Reservation System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            // Main Panel
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            frame.add(panel);

            // Text Area to show bookings/payments
            JTextArea outputArea = new JTextArea(15, 40);
            outputArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outputArea);
            panel.add(scrollPane, BorderLayout.CENTER);

            // Buttons Panel
            JPanel buttonPanel = new JPanel();
            panel.add(buttonPanel, BorderLayout.SOUTH);

            JButton bookButton = new JButton("Book Room");
            bookButton.addActionListener(e -> {
    // Input Room Type
            String roomType = JOptionPane.showInputDialog("Enter Room Type (Standard/Deluxe):");
            Room room = RoomFactory.createRoom(roomType);

    // Add-ons Selection
            String addOn = JOptionPane.showInputDialog("Add-ons (Enter 'Breakfast' for breakfast, or 'None'):");

               if ("breakfast".equalsIgnoreCase(addOn)) {
                   room = new BreakfastDecorator(room);
                }

    // Input Customer Name and Phone
            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            String customerPhone = JOptionPane.showInputDialog("Enter Customer Phone:");

    // Add Reservation
           String reservationDetails = "Customer: " + customerName + " (" + customerPhone + "), Room: " + room.getRoomDetails();
           reservationManager.addReservation(reservationDetails);

    // Show Reservation in Output Area
           outputArea.append("New Reservation:\n" + reservationDetails + "\n\n");

           JOptionPane.showMessageDialog(frame, "Room Booked!");
         });


            JButton payButton = new JButton("Make Payment");
            payButton.addActionListener(e -> {
                // Select Payment Type
                String paymentType = JOptionPane.showInputDialog("Enter Payment Type (Credit/Cash):");
                PaymentStrategy payment;
                String paymentDetails;

                if ("credit".equalsIgnoreCase(paymentType)) {
                    payment = new CreditCardPayment();
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount:"));
                    String cardNumber = JOptionPane.showInputDialog("Enter Card Number:");
                    paymentDetails = payment.pay(amount) + " | Card Number: " + cardNumber;
                } else {
                    payment = new CashPayment();
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount:"));
                    paymentDetails = payment.pay(amount);
                }

                // Add Payment
                paymentProcessor.addPayment(paymentDetails);

                // Show Payment in Output Area
                outputArea.append("New Payment:\n" + paymentDetails + "\n\n");

                JOptionPane.showMessageDialog(frame, "Payment Completed!");
            });

            // Show Reservations Button
            JButton showReservationsButton = new JButton("Show Reservations");
            showReservationsButton.addActionListener(e -> {
                outputArea.append("All Reservations:\n");
                for (String reservation : reservationManager.getReservations()) {
                    outputArea.append(reservation + "\n");
                }
                outputArea.append("\n");
            });

            // Show Payments Button
            JButton showPaymentsButton = new JButton("Show Payments");
            showPaymentsButton.addActionListener(e -> {
                outputArea.append("All Payments:\n");
                for (String payment : paymentProcessor.getPayments()) {
                    outputArea.append(payment + "\n");
                }
                outputArea.append("\n");
            });

            buttonPanel.add(bookButton);
            buttonPanel.add(payButton);
            buttonPanel.add(showReservationsButton);
            buttonPanel.add(showPaymentsButton);

            frame.setVisible(true);
        });
    }
}
