package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
public class Wc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    @ManyToOne
    @JoinColumn
    private Concert concert;

    public Wc() {
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
