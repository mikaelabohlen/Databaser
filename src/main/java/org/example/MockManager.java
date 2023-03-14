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

    private Controller controller;

    public MockManager (Controller controller) {
        this.controller = controller;
    }

    public void createMockAddress() {
        Address address1 = new Address();
        address1.setStreetName("Violvägen");
        address1.setHouseNumber(4);
        address1.setCity("Kvissleby");
        address1.setZipCode(86234);
        controller.getAddressDAO().createAddress(address1);


        Address address2 = new Address();
        address2.setStreetName("Strandgatan");
        address2.setHouseNumber(14);
        address2.setCity("Mariestad");
        address2.setZipCode(54333);
        controller.getAddressDAO().createAddress(address2);


        Address address3 = new Address();
        address3.setStreetName("Norrgränden");
        address3.setHouseNumber(8);
        address3.setCity("Avesta");
        address3.setZipCode(77440);
        controller.getAddressDAO().createAddress(address3);


        Address address4 = new Address();
        address4.setStreetName("Krossgatan");
        address4.setHouseNumber(42);
        address4.setCity("Kristinehamn");
        address4.setZipCode(68198);
        controller.getAddressDAO().createAddress(address4);


        Address address5 = new Address();
        address5.setStreetName("Eldvägen");
        address5.setHouseNumber(12);
        address5.setCity("Ystad");
        address5.setZipCode(27898);
        controller.getAddressDAO().createAddress(address5);

        Address address6 = new Address();
        address6.setStreetName("Surikatvägen");
        address6.setHouseNumber(43);
        address6.setCity("Kiruna");
        address6.setZipCode(30672);
        controller.getAddressDAO().createAddress(address6);

        Address address7 = new Address();
        address7.setStreetName("Strandgatan");
        address7.setHouseNumber(6);
        address7.setCity("Sundsvall");
        address7.setZipCode(85689);
        controller.getAddressDAO().createAddress(address7);

        Address address8 = new Address();
        address8.setStreetName("Storgatan");
        address8.setHouseNumber(99);
        address8.setCity("Umeå");
        address8.setZipCode(28904);
        controller.getAddressDAO().createAddress(address8);

        Address address9 = new Address();
        address9.setStreetName("Guldvägen");
        address9.setHouseNumber(6);
        address9.setCity("Göteborg");
        address9.setZipCode(67592);
        controller.getAddressDAO().createAddress(address9);

        Address address10 = new Address();
        address10.setStreetName("Fågelgatan");
        address10.setHouseNumber(2);
        address10.setCity("Bromma");
        address10.setZipCode(70524);
        controller.getAddressDAO().createAddress(address10);
    }


    public void createMockArenas() {
        Arena arena = new Arena();
        arena.setName("TheArena");
        arena.setSetting(Setting.INSIDE);
        arena.setAddress(controller.getAddressDAO().getAddressById(10));
        controller.getArenaDAO().createArena(arena);


        Arena arena2 = new Arena();
        arena2.setName("Operan");
        arena2.setSetting(Setting.INSIDE);
        arena2.setAddress(controller.getAddressDAO().getAddressById(9));
        controller.getArenaDAO().createArena(arena2);


        Arena arena3 = new Arena();
        arena3.setName("Kulturhus");
        arena3.setSetting(Setting.OUTSIDE);
        arena3.setAddress(controller.getAddressDAO().getAddressById(8));
        controller.getArenaDAO().createArena(arena3);


        Arena arena4 = new Arena();
        arena4.setName("Waldorfshuset");
        arena4.setSetting(Setting.INSIDE);
        arena4.setAddress(controller.getAddressDAO().getAddressById(7));
        controller.getArenaDAO().createArena(arena4);


        Arena arena5 = new Arena();
        arena5.setName("Ystad Arena");
        arena5.setSetting(Setting.OUTSIDE);
        arena5.setAddress(controller.getAddressDAO().getAddressById(6));
        controller.getArenaDAO().createArena(arena5);
    }


    public void createMockCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Allan");
        customer1.setLastName("Edwall");
        customer1.setBirthdate(LocalDate.of(1987, 6, 30));
        customer1.setPhoneNumber("+46738073634");
        customer1.setAdress(controller.getAddressDAO().getAddressById(2));
        customer1.setAdmin(false);
        controller.getCustomerDAO().createCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Helga");
        customer2.setLastName("Sköld");
        customer2.setBirthdate(LocalDate.of(1999, 5, 19));
        customer2.setPhoneNumber("+46736552166");
        customer2.setAdress(controller.getAddressDAO().getAddressById(3));
        customer2.setAdmin(false);
        controller.getCustomerDAO().createCustomer(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Samira");
        customer3.setLastName("Noor");
        customer3.setBirthdate(LocalDate.of(2001, 10, 27));
        customer3.setPhoneNumber("+46756341662");
        customer3.setAdress(controller.getAddressDAO().getAddressById(1));
        customer3.setAdmin(false);
        controller.getCustomerDAO().createCustomer(customer3);

        Customer customer4 = new Customer();
        customer4.setFirstName("Nicholai");
        customer4.setLastName("Petrov");
        customer4.setBirthdate(LocalDate.of(1989, 1, 1));
        customer4.setPhoneNumber("+4674777196");
        customer4.setAdress(controller.getAddressDAO().getAddressById(4));
        customer4.setAdmin(false);
        controller.getCustomerDAO().createCustomer(customer4);

        Customer customer5 = new Customer();
        customer5.setFirstName("Agnes");
        customer5.setLastName("Lund");
        customer5.setBirthdate(LocalDate.of(1950, 3, 15));
        customer5.setPhoneNumber("+46755513628");
        customer5.setAdress(controller.getAddressDAO().getAddressById(5));
        customer5.setAdmin(false);
        controller.getCustomerDAO().createCustomer(customer5);

        Customer customer6 = new Customer();
        customer6.setFirstName("Britt");
        customer6.setLastName("Marie");
        customer6.setBirthdate(LocalDate.of(2010, 1, 12));
        customer6.setPhoneNumber("+46747677123");
        customer6.setAdress(controller.getAddressDAO().getAddressById(5));
        customer6.setAdmin(false);
        controller.getCustomerDAO().createCustomer(customer6);
    }


    public void createMockConcerts() {
        Concert concert1 = new Concert();
        concert1.setArtist("Babblarna");
        concert1.setDate(LocalDate.of(2023, 6, 5));
        concert1.setPrice(599.99);
        concert1.setAgeLimit(0);
        concert1.setArena(controller.getArenaDAO().getArenaById(1));
        controller.getConcertDAO().createConcert(concert1);


        Concert concert2 = new Concert();
        concert2.setArtist("Los Babblos");
        concert2.setDate(LocalDate.of(2023, 7, 30));
        concert2.setPrice(1599.99);
        concert2.setAgeLimit(3);
        concert2.setArena(controller.getArenaDAO().getArenaById(1));
        controller.getConcertDAO().createConcert(concert2);


        Concert concert3 = new Concert();
        concert3.setArtist("Kinesiska Operan");
        concert3.setDate(LocalDate.of(2023, 4, 1));
        concert3.setPrice(799.99);
        concert3.setAgeLimit(12);
        concert3.setArena(controller.getArenaDAO().getArenaById(1));
        controller.getConcertDAO().createConcert(concert3);


        Concert concert4 = new Concert();
        concert4.setArtist("Black Dresses");
        concert4.setDate(LocalDate.of(2023, 7, 1));
        concert4.setPrice(299.99);
        concert4.setAgeLimit(18);
        concert4.setArena(controller.getArenaDAO().getArenaById(1));
        controller.getConcertDAO().createConcert(concert4);

        Concert concert5 = new Concert();
        concert5.setArtist("Rebellerna");
        concert5.setDate(LocalDate.of(2023, 3, 30));
        concert5.setPrice(199.99);
        concert5.setAgeLimit(18);
        concert5.setArena(controller.getArenaDAO().getArenaById(1));
        controller.getConcertDAO().createConcert(concert5);

        Concert concert6 = new Concert();
        concert6.setArtist("Bamse On Ice");
        concert6.setDate(LocalDate.of(2023, 7, 15));
        concert6.setPrice(2000.00);
        concert6.setAgeLimit(0);
        concert6.setArena(controller.getArenaDAO().getArenaById(1));
        controller.getConcertDAO().createConcert(concert6);

    }


    public void createMockLinks(){
        //Denna bör göras klart först när DAON är klar?.

        //Mocklink customer1 consert ALL(hittills)=
        Customer customer1 = controller.getCustomerDAO().getCustomerById(1);
        List<Concert> concerts1 = new ArrayList<>();
        concerts1.add(controller.getConcertDAO().getConcertById(2));
        concerts1.add(controller.getConcertDAO().getConcertById(1));
        customer1.setConcerts(concerts1);
        controller.getCustomerDAO().updateCustomer(customer1);

        //Mocklink customer2 Concert 2
        Customer customer2 = controller.getCustomerDAO().getCustomerById(1);
        List<Concert> concerts2 = new ArrayList<>();
        concerts2.add(controller.getConcertDAO().getConcertById(4));
        concerts2.add(controller.getConcertDAO().getConcertById(5));
        customer2.setConcerts(concerts2);
        controller.getCustomerDAO().updateCustomer(customer2);

        //Mocklink customer 5 concert 3 and 4

        Customer customer5 = controller.getCustomerDAO().getCustomerById(5);
        List<Concert> concerts5 = new ArrayList<>();
        concerts5.add(controller.getConcertDAO().getConcertById(3));
        concerts5.add(controller.getConcertDAO().getConcertById(4));
        customer5.setConcerts(concerts5);
        controller.getCustomerDAO().updateCustomer(customer5);

        //Mocklink customer 4, concert all but 3
        Customer customer4 = controller.getCustomerDAO().getCustomerById(5);
        List<Concert> concerts4 = new ArrayList<>();
        concerts5.add(controller.getConcertDAO().getConcertById(1));
        concerts5.add(controller.getConcertDAO().getConcertById(2));
        concerts5.add(controller.getConcertDAO().getConcertById(4));
        concerts5.add(controller.getConcertDAO().getConcertById(5));
        concerts5.add(controller.getConcertDAO().getConcertById(6));
        customer5.setConcerts(concerts4);
        controller.getCustomerDAO().updateCustomer(customer4);

        //MockLink customer 6, concert 5 and 6
        Customer customer6 = controller.getCustomerDAO().getCustomerById(6);
        List<Concert> concerts6 = new ArrayList<>();
        concerts6.add(controller.getConcertDAO().getConcertById(5));
        concerts6.add(controller.getConcertDAO().getConcertById(6));
        customer6.setConcerts(concerts6);
        controller.getCustomerDAO().updateCustomer(customer6);
    }
}
