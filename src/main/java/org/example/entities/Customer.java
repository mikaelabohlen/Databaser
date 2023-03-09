package org.example.entities;

import org.example.entities.Address;
import org.example.entities.Concert;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
//import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
//    private Date birthdate;
    private LocalDate birthdate;
    private String phoneNumber;
    @OneToOne
    @JoinColumn
    private Address adress;
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Wc> wcs;

    @ManyToMany
//    @JoinTable(name = "wc",joinColumns = {@JoinColumn}, inverseJoinColumns = {@JoinColumn})
    @JoinTable(name = "wc",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "concert_id")})
    private List<Concert> concerts;
    public Customer(){

    }
//    public Customer(List<Wc> wc) {
//        wc = new ArrayList<>();
//        this.wcs = wc;
//    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

//    public List<Wc> getWcs() {
//        return wcs;
//    }
//
//    public void setWcs(List<Wc> wc) {
//        this.wcs = wc;
//    }


    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

}
