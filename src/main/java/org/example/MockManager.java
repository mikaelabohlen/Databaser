package org.example;

import org.example.entities.Address;
import org.example.entities.Arena;
import org.example.entities.Concert;
import org.example.entities.Customer;
import org.example.enums.Setting;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockManager {

    public void createMockAddress(Session session) {
        Address address = new Address();
        address.setStreetName("Violvägen");
        address.setHouseNumber(4);
        address.setCity("Kvissleby");
        address.setZipCode(86234);
        session.persist(address);
    }

    public void createMockArenas(Session session) {
        Arena arena = new Arena();
        arena.setName("TheArena");
        arena.setSetting(Setting.INSIDE);
        arena.setAddress(session.get(Address.class, 1));
        session.persist(arena);
    }

    public void createMockCustomers(Session session) {
        Customer customer1 = new Customer();
        customer1.setFirstName("Allan");
        customer1.setLastName("Edwall");
        customer1.setBirthdate(LocalDate.of(1987, 6, 30));
        customer1.setPhoneNumber("+46738073634");
        customer1.setAdress(session.get(Address.class, 1));
        session.persist(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Helga");
        customer2.setLastName("Sköld");
        customer2.setBirthdate(LocalDate.of(1999, 5, 19));
        customer2.setPhoneNumber("+46736552166");
        customer2.setAdress(session.get(Address.class, 1));
        session.persist(customer2);
    }

    public void createMockConcerts(Session session) {
        Concert concert1 = new Concert();
        concert1.setArtist("Babblarna");
        concert1.setDate(LocalDate.of(2023, 6, 5));
        concert1.setPrice(599.99);
        concert1.setAgeLimit(0);
        concert1.setArena(session.get(Arena.class, 1));
        session.persist(concert1);

        Concert concert2 = new Concert();
        concert2.setArtist("Los Babblos");
        concert2.setDate(LocalDate.of(2023, 7, 30));
        concert2.setPrice(1599.99);
        concert2.setAgeLimit(3);
        concert2.setArena(session.get(Arena.class, 1));
        session.persist(concert2);
    }

    public void createMockLinks(Session session){
        //Denna bör göras klart först när DAON är klar?.

        //Mocklink customer1 consert ALL(hittills)=
        Customer customer1 = session.get(Customer.class, 1);
        List<Concert> concerts1 = new ArrayList<>();
        concerts1.add(session.get(Concert.class, 2));
        concerts1.add(session.get(Concert.class, 1));
        customer1.setConcerts(concerts1);

        session.update(customer1);

        //Mocklink customer2 Concert 2
        Customer customer2 = session.get(Customer.class, 2);
        List<Concert> concerts2 = new ArrayList<>();
        concerts2.add(session.get(Concert.class, 2));
        customer2.setConcerts(concerts2);
        session.update(customer2);
    }
}
