package com.example.resort;

public class Room {
    private String roomType;
    private String description;
    private String imageUrl;
    private double pricePerNight;

    public Room(String roomType, String description, String imageUrl, double pricePerNight) {
        this.roomType = roomType;
        this.description = description;
        this.imageUrl = imageUrl;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}
