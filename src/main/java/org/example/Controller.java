package org.example;

import org.example.dao.AddressDAO;
import org.example.dao.ArenaDAO;
import org.example.dao.ConcertDAO;
import org.example.dao.CustomerDAO;
import org.example.entities.Address;
import org.example.entities.Arena;
import org.example.entities.Concert;
import org.example.entities.Customer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Controller {
    List<Concert> concertList;
    List<Customer> customerList;
    List<Arena> arenaList;

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

    public void setUpMockData(){
        mockManager.createMockAddress();
        mockManager.createMockArenas();
        mockManager.createMockCustomers();
        mockManager.createMockConcerts();
        mockManager.createMockLinks();
    }

    public boolean addNewConcert(List<String> addConcertInputs){
        try {
            Concert concert = new Concert();
            concert.setArtist(addConcertInputs.get(0));
            concert.setDate(LocalDate.parse(addConcertInputs.get(1)));
            concert.setPrice(Double.parseDouble(addConcertInputs.get(2)));
            concert.setAgeLimit(Integer.parseInt(addConcertInputs.get(3)));

            arenaList = arenaDAO.getAllArenas();
            for (Arena arena : arenaList) {
                if (arena.getName().equals(addConcertInputs.get(4))) {
                    concert.setArena(arena);
                }
            }
            concertDAO.createConcert(concert);
            return true;

        }catch (Exception e) {
            return false;
        }
    }
    public boolean addNewArena(List<String> addArenaInputs) {
        try {
            Arena arena = new Arena();
            Address address= new Address();

            arena.setName(addArenaInputs.get(0));

            address.setStreetName(addArenaInputs.get(1));
            address.setHouseNumber(Integer.parseInt(addArenaInputs.get(2)));
            address.setZipCode(Integer.parseInt(addArenaInputs.get(3)));
            address.setCity(addArenaInputs.get(4));

            addressDAO.createAddress(address);
            arena.setAddress(address);
            arenaDAO.createArena(arena);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean validateLogin(String userName, String password) {
        customerList = customerDAO.getAllCustomers();
        for (Customer customer : customerList) {
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
        customerList = customerDAO.getAllCustomers();
        for (Customer customer : customerList) {
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
        concertList = concertDAO.getAllConcerts();
        for (Concert concert: concertList) {
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

//    public Arena setArenaValue(String value) {
//        for(int i=0; i<getArenaDAO().getAllArenas().size(); i++) {
//            if(getArenaDAO().getAllArenas().get(i).getName().equals(value)) {
//                return  getArenaDAO().getAllArenas().get(i);
//            }
//        }
//        return null;
//    }

    public void deleteConcert(int index) {
        Concert concert = concertDAO.getAllConcerts().get(index);
//        concert.getCustomers().clear();
//        concertDAO.updateConcert(concert);
        concertDAO.deleteConcert(concert.getId());
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
