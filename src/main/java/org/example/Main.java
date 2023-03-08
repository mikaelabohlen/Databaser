package org.example;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


//        Address address = new Address();
//        address.setStreetName("Violvägen");
//        address.setHouseNumber(4);
//        address.setCity("Kvissleby");
//        address.setZipCode(86234);
//
//        session.persist(address);
//
//        Arena arena = new Arena();
//        arena.setName("TheArena");
//        arena.setSetting(Setting.INSIDE);
//        arena.setAddress(session.get(Address.class, 1));
//
//        session.persist(arena);

//        Customer customer1 = new Customer();
//        customer1.setFirstName("Allan");
//        customer1.setLastName("Edwall");
//        customer1.setBirthdate(LocalDate.of(1987,6,30));
//        customer1.setPhoneNumber("+46738073634");
//        customer1.setAdress(session.get(Address.class, 1));
//        System.out.println(customer1.getBirthdate());
//
//        Customer customer2 = new Customer();
//        customer2.setFirstName("Helga");
//        customer2.setLastName("Sköld");
//        customer2.setBirthdate(LocalDate.of(1999,5,19));
//        customer2.setPhoneNumber("+46736552166");
//        customer2.setAdress(session.get(Address.class, 1));
//
//
//
//
//        session.persist(customer1);
//        session.persist(customer2);
//
//        Concert concert1 = new Concert();
//        concert1.setArtist("Babblarna");
//        concert1.setDate(LocalDate.of(2023,6,5));
//        concert1.setPrice(599.99);
//        concert1.setAgeLimit(0);
//        concert1.setArena(session.get(Arena.class, 1));
//
//        session.persist(concert1);
//
//
//        Wc wc1 = new Wc();
//        wc1.setName("WC1");
//        wc1.setConcert(concert1);
//        wc1.setCustomer(customer1);
//        session.persist(wc1);
//
//        Wc wc2= new Wc();
//        wc2.setName("WC2");
//        wc2.setConcert(concert1);
//        wc2.setCustomer(customer2);
//        session.persist(wc2);

        Concert concert = session.get(Concert.class,1);
        System.out.println(concert.getWcs());
        System.out.println(concert.getCustomers());

        //Gör commit och stänger sessionen
        session.getTransaction().commit();
        session.close();


    }
}