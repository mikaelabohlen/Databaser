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
        Address address1 = new Address();
        address1.setStreetName("Violvägen");
        address1.setHouseNumber(4);
        address1.setCity("Kvissleby");
        address1.setZipCode(86234);
        session.persist(address1);


        Address address2 = new Address();
        address2.setStreetName("Strandgatan");
        address2.setHouseNumber(14);
        address2.setCity("Mariestad");
        address2.setZipCode(54333);
        session.persist(address2);


        Address address3 = new Address();
        address3.setStreetName("Norrgränden");
        address3.setHouseNumber(8);
        address3.setCity("Avesta");
        address3.setZipCode(77440);
        session.persist(address3);


        Address address4 = new Address();
        address4.setStreetName("Krossgatan");
        address4.setHouseNumber(42);
        address4.setCity("Kristinehamn");
        address4.setZipCode(68198);
        session.persist(address4);


        Address address5 = new Address();
        address5.setStreetName("Eldvägen");
        address5.setHouseNumber(12);
        address5.setCity("Ystad");
        address5.setZipCode(27898);
        session.persist(address5);
    }


    public void createMockArenas(Session session) {
        Arena arena = new Arena();
        arena.setName("TheArena");
        arena.setSetting(Setting.INSIDE);
        arena.setAddress(session.get(Address.class, 1));
        session.persist(arena);


        Arena arena2 = new Arena();
        arena2.setName("Operan");
        arena2.setSetting(Setting.INSIDE);
        arena2.setAddress(session.get(Address.class, 1));
        session.persist(arena2);


        Arena arena3 = new Arena();
        arena3.setName("Kulturhus");
        arena3.setSetting(Setting.OUTSIDE);
        arena3.setAddress(session.get(Address.class, 1));
        session.persist(arena3);




        Arena arena4 = new Arena();
        arena4.setName("Waldorfshuset");
        arena4.setSetting(Setting.INSIDE);
        arena4.setAddress(session.get(Address.class, 1));
        session.persist(arena4);


        Arena arena5 = new Arena();
        arena5.setName("Ystad Arena");
        arena5.setSetting(Setting.OUTSIDE);
        arena5.setAddress(session.get(Address.class, 1));
        session.persist(arena5);
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
        customer2.setAdress(session.get(Address.class, 3));
        session.persist(customer2);


        Customer customer3 = new Customer();
        customer3.setFirstName("Samira");
        customer3.setLastName("Noor");
        customer3.setBirthdate(LocalDate.of(2001, 10, 27));
        customer3.setPhoneNumber("+46756341662");
        customer3.setAdress(session.get(Address.class, 2));
        session.persist(customer3);


        Customer customer4 = new Customer();
        customer4.setFirstName("Nicholai");
        customer4.setLastName("Petrov");
        customer4.setBirthdate(LocalDate.of(1989, 1, 1));
        customer4.setPhoneNumber("+4674777196");
        customer4.setAdress(session.get(Address.class, 5));
        session.persist(customer4);

        Customer customer5 = new Customer();
        customer5.setFirstName("Agnes");
        customer5.setLastName("Lund");
        customer5.setBirthdate(LocalDate.of(1950, 3, 15));
        customer5.setPhoneNumber("+46755513628");
        customer5.setAdress(session.get(Address.class, 4));
        session.persist(customer5);
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
        concert2.setArena(session.get(Arena.class, 5));
        session.persist(concert2);


        Concert concert3 = new Concert();
        concert3.setArtist("Kinesiska Operan");
        concert3.setDate(LocalDate.of(2023, 4, 1));
        concert3.setPrice(799.99);
        concert3.setAgeLimit(12);
        concert3.setArena(session.get(Arena.class, 2));
        session.persist(concert3);


        Concert concert4 = new Concert();
        concert4.setArtist("Black Dresses");
        concert4.setDate(LocalDate.of(2023, 7, 1));
        concert4.setPrice(299.99);
        concert4.setAgeLimit(18);
        concert4.setArena(session.get(Arena.class, 4));
        session.persist(concert4);

        Concert concert5 = new Concert();
        concert5.setArtist("Rebellerna");
        concert5.setDate(LocalDate.of(2023, 3, 30));
        concert5.setPrice(199.99);
        concert5.setAgeLimit(18);
        concert1.setArena(session.get(Arena.class, 3));
        session.persist(concert5);

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
