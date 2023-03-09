package org.example;




import javafx.application.Application;
import javafx.stage.Stage;
import org.example.gui.Gui;
import org.example.gui.GuiController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//JAVA FX MAIN

public class Main extends Application {
    private Gui gui;
    private GuiController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new GuiController();
        gui = new Gui(primaryStage, controller);
        gui.setupGui();
    }

    public static void main(String[] args) {
        launch(args);
    }

}


//VANLIGA MAIN

/*public class Main {
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


    }
}*/
