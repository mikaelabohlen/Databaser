package org.example;

import org.example.dao.AddressDAO;
import org.example.dao.ArenaDAO;
import org.example.dao.ConcertDAO;
import org.example.dao.CustomerDAO;
import org.example.entities.Concert;
import org.example.entities.Customer;
import org.example.gui.guidto.UserDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Controller {

    private Customer currentCustomer;
    private AddressDAO addressDAO;
    private ArenaDAO arenaDAO;
    private ConcertDAO concertDAO;
    private CustomerDAO customerDAO;
    private MockManager mockManager;

    public Controller() {
        this.addressDAO = new AddressDAO();
        this.arenaDAO = new ArenaDAO();
        this.concertDAO = new ConcertDAO();
        this.customerDAO = new CustomerDAO();
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public ArenaDAO getArenaDAO() {
        return arenaDAO;
    }

    public void setArenaDAO(ArenaDAO arenaDAO) {
        this.arenaDAO = arenaDAO;
    }

    public ConcertDAO getConcertDAO() {
        return concertDAO;
    }

    public void setConcertDAO(ConcertDAO concertDAO) {
        this.concertDAO = concertDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public MockManager getMockManager() {
        return mockManager;
    }

    public void setMockManager(MockManager mockManager) {
        this.mockManager = mockManager;
    }

    public boolean validateLogin(String userName, String password) {
        for (Customer customer : customerDAO.getAllCustomers()) {
            if (customer.isAdmin()) {
                continue;
            }
            if (customer.getFirstName().equals(userName) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public boolean validateLoginAdmin(String userName, String password) {
        for (Customer customer : customerDAO.getAllCustomers()) {
            if (!customer.isAdmin()) {
                continue;
            }
            if (customer.getFirstName().equals(userName) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public void listCustomersForAllConcerts() {
        for (Concert concert : concertDAO.getAllConcerts()) {
            System.out.println("\n" + concert.getArtist() + ":");
            for (int i = 0; i < concert.getCustomers().size(); i++) {
                System.out.println(concert.getCustomers().get(i).getFirstName() + " " + concert.getCustomers().get(i).getLastName());
            }
        }
    }

    public void listConcertsForSpecificCustomer(int id) {
        Customer customerRetrieved = customerDAO.getCustomerById(id);
        System.out.println(customerRetrieved.getFirstName() + " ska se: ");
        for (Concert concert : customerRetrieved.getConcerts()) {
            System.out.println(concert.getArtist());
        }
    }

    public boolean checkAgeLimit(int id) {
        Customer customer = customerDAO.getCustomerById(id);
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(customer.getBirthdate(), now);
        System.out.println(years);
        Concert concert = concertDAO.getConcertById(id);

        return concert.getAgeLimit() <= years;
    }

    public void buyTicket() {

    }
//
//    public void linkCustomersConcerts(Customer customer, List<Concert> concerts) { //buyTicket eller dylikt
//        customer.setConcerts(concerts);
//        session.update(customer);
//
////        for(Concert concert: customer.getConcerts()){
////            session.update(concert);
////        }
//
//        // behöver man loopa igenom alla concerts och update them to? eller gör hibernate det?
//    }
}
