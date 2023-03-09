package org.example.gui.guidto;

import java.time.LocalDate;

public class ConcertDTO {
    private String artist;
    private LocalDate date;
    private double price;
    private ArenaDTO arena;
    private int ageLimit;

    public ConcertDTO() {

    }

    public ConcertDTO(String artist, LocalDate date, double price, ArenaDTO arena, int ageLimit) {
        this.artist = artist;
        this.date = date;
        this.price = price;
        this.arena = arena;
        this.ageLimit = ageLimit;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArenaDTO getArena() {
        return arena;
    }

    public void setArena(ArenaDTO arena) {
        this.arena = arena;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
