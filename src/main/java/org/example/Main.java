package org.example;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Controller controller = new Controller();
        controller.setSession(session);

        MockManager mockManager = new MockManager();
        controller.setMockManager(mockManager);

        mockManager.createMockAddress(controller.getSession());
        mockManager.createMockArenas(controller.getSession());
        mockManager.createMockCustomers(controller.getSession());
        mockManager.createMockConcerts(controller.getSession());
        mockManager.createMockLinks(controller.getSession());

        controller.listConcertsForSpecificCustomer(1);
        controller.listConcertsForSpecificCustomer(2);

        //Gör commit och stänger sessionen
        session.getTransaction().commit();
        session.close();

        System.out.println("HEJ JAG HETER JIM DETTA ÄR MIN COMMIT TILL DETTA PROGRAMMET");

    }


}