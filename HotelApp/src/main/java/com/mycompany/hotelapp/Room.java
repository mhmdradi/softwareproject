/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelapp;

public abstract class Room {
    public abstract String getRoomDetails();
}

// Standard Room
class StandardRoom extends Room {
    @Override
    public String getRoomDetails() {
        return "Standard Room: Basic amenities, single bed.";
    }
}

// Deluxe Room
class DeluxeRoom extends Room {
    @Override
    public String getRoomDetails() {
        return "Deluxe Room: Upgraded amenities, double bed, minibar.";
    }
}

// Room Factory
class RoomFactory {
    public static Room createRoom(String type) {
        if (type == null) return null;
        switch (type.toLowerCase()) {
            case "standard":
                return new StandardRoom();
            case "deluxe":
                return new DeluxeRoom();
            default:
                throw new IllegalArgumentException("Invalid Room Type: " + type);
        }
    }
}
