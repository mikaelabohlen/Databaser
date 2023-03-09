package org.example;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String artist;
    private LocalDate date;
    private Double price;
    private Integer ageLimit;
    @OneToOne
    @JoinColumn
    private Arena arena;

//    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
//    private List<Wc> wcs;
    @ManyToMany(mappedBy = "concerts")

//    @JoinTable(name = "wc",
//            joinColumns = {@JoinColumn(name = "concert_id")},
//            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customers;
    public Concert(){

    }

//    public Concert(List<Wc> wc) {
//        wc = new ArrayList<>();
//        this.wcs = wc;
//    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }
//    public List<Wc> getWcs() {
//        return wcs;
//    }
//
//    public void setWcs(List<Wc> wc) {
//        this.wcs = wc;
//    }
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}