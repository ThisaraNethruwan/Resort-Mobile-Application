package com.example.resort;

public class User {
    private int id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String preferences;

    public User(String email) {
        this.email = email;
        this.preferences = "Default Preferences";
    }

    public User(int id, String email, String fullName, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.preferences = "Default Preferences";
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}