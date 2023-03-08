package org.example;

import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Session session;

    public Controller() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void createMockAddress() {
        Address address = new Address();
        address.setStreetName("Violvägen");
        address.setHouseNumber(4);
        address.setCity("Kvissleby");
        address.setZipCode(86234);

        session.persist(address);
    }

    public void createMockArenas() {
        Arena arena = new Arena();
        arena.setName("TheArena");
        arena.setSetting(Setting.INSIDE);
        arena.setAddress(session.get(Address.class, 1));
        session.persist(arena);
    }

    public void createMockCustomers() {
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

    public void createMockConcerts() {
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

    public List<Concert> getConcertsFromDatabase() {
        return session.createQuery("FROM Concert", Concert.class).list();
    }

    public List<Customer> getCustomersFromDatabase() {
        return session.createQuery("FROM Customer", Customer.class).list();
    }

    public void listAudience(String concert) {

    }

    public void listConcertsForSpecificCustomer(int id) {
        Customer customerRetrieved = session.get(Customer.class, id);
        System.out.println(customerRetrieved.getConcerts().size());
        System.out.println(customerRetrieved.getFirstName());
        System.out.println("Ska se:");
        for (Concert concert : customerRetrieved.getConcerts()) {
            System.out.println(concert.getArtist());
        }
    }

    public void createMockLinks(){
        //Mocklink customer1 consert ALL
        Customer customer1 = session.get(Customer.class, 1);
        customer1.setConcerts(getConcertsFromDatabase());
        session.update(customer1);

        //Mocklink customer2 Concert 2
        Customer customer2 = session.get(Customer.class, 2);
        List<Concert> concerts = new ArrayList<>();
        concerts.add(session.get(Concert.class, 2));
        customer2.setConcerts(concerts);
        session.update(customer2);
    }

    public void linkCustomersConcerts(Customer customer, List<Concert> concerts) { //buyTicket eller dylikt
        customer.setConcerts(concerts);
        session.update(customer);

//        for(Concert concert: customer.getConcerts()){
//            session.update(concert);
//        }

        // behöver man loopa igenom alla concerts och update them to? eller gör hibernate det?
    }
}
