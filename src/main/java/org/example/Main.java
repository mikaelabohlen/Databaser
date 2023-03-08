package org.example;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Controller controller = new Controller();
        controller.setSession(session);

//        controller.createMockAddress();
//        controller.createMockArenas();
//        controller.createMockCustomers();
//        controller.createMockConcerts();
//        controller.createMockLinks();

        controller.listConcertsForSpecificCustomer(1);
        controller.listConcertsForSpecificCustomer(2);

        //Gör commit och stänger sessionen
        session.getTransaction().commit();
        session.close();

    }
}