package org.example;

import org.example.dao.AddressDAO;
import org.example.dao.ArenaDAO;
import org.example.dao.ConcertDAO;
import org.example.dao.CustomerDAO;
import org.example.entities.Address;
import org.example.entities.Arena;
import org.example.entities.Concert;
import org.example.entities.Customer;
import org.example.gui.guidto.AdressDTO;
import org.example.gui.guidto.UserDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Controller {
    private List<Concert> concertList;
    private List<Customer> customerList;
    private List<Arena> arenaList;

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

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void setUpMockData(){
        if(addressDAO.getAllAddresses().size()<1) {
            mockManager.createMockAddress();
        }

        if(customerDAO.getAllCustomers().size()<1) {
            mockManager.createMockCustomers();
        }

        if(arenaDAO.getAllArenas().size()<1) {
            mockManager.createMockArenas();
        }

        if(concertDAO.getAllConcerts().size()<1) {
            mockManager.createMockConcerts();
            mockManager.createMockLinks();
        }
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

    public boolean registerNewUser(List<String> addRegisterInputs) {
        try {
            Customer customer = new Customer();
            Address address = new Address();

            customer.setFirstName(addRegisterInputs.get(0));
            customer.setLastName(addRegisterInputs.get(1));
            customer.setBirthdate(LocalDate.parse(addRegisterInputs.get(2)));
            customer.setPhoneNumber(addRegisterInputs.get(3));
            customer.setPassword(validatePassword(addRegisterInputs.get(4), addRegisterInputs.get(5)));

            address.setStreetName(addRegisterInputs.get(6));
            address.setHouseNumber(Integer.parseInt(addRegisterInputs.get(7)));
            address.setZipCode(Integer.parseInt(addRegisterInputs.get(8)));
            address.setCity(addRegisterInputs.get(9));

            addressDAO.createAddress(address);
            customer.setAddress(address);
            customerDAO.createCustomer(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String validatePassword(String password, String confirmPassword) {
        if(password.equals(confirmPassword)) {
            return password;
        }
        else {
            return null;
        }
    }

    public boolean validateLogin(String userName, String password) {
        customerList = customerDAO.getAllCustomers();
        for (Customer customer : customerList) {
            if (customer.isAdmin()) {
                continue;
            }
            if (customer.getFirstName().equals(userName) && customer.getPassword().equals(password)) {
                currentCustomer = customer;
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

    public boolean addConcertsToCustomer(int id){
        Concert concert = concertDAO.getConcertById(id);

        if(checkAgeLimit(currentCustomer, concert)){
            currentCustomer.getConcerts().add(concert);
            customerDAO.updateCustomer(currentCustomer);
            currentCustomer = customerDAO.getCustomerById(currentCustomer.getId());
            return true;
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

    public boolean checkAgeLimit(Customer customer, Concert concert) {
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(customer.getBirthdate(), now);
        System.out.println(years);

        return concert.getAgeLimit() <= years;
    }

    public void deleteConcert(int index) {
        Concert concert = concertDAO.getAllConcerts().get(index);
        concertDAO.deleteConcert(concert.getId());
    }
}
