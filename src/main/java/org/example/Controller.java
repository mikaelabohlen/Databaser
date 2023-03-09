package org.example;

import org.example.entities.Concert;
import org.example.entities.Customer;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Session session; //Controller ska innehålla daos som i sin tur innehåller sessions?
    private MockManager mockManager;

    public Controller() {
    }
    public void setSession(Session session) {
        this.session = session;
    }
    public Session getSession() {
        return session;
    }

    public MockManager getMockManager() {
        return mockManager;
    }

    public void setMockManager(MockManager mockManager) {
        this.mockManager = mockManager;
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

    public void linkCustomersConcerts(Customer customer, List<Concert> concerts) { //buyTicket eller dylikt
        customer.setConcerts(concerts);
        session.update(customer);

//        for(Concert concert: customer.getConcerts()){
//            session.update(concert);
//        }

        // behöver man loopa igenom alla concerts och update them to? eller gör hibernate det?
    }
}
