package org.example.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Gui {

    //CONDITION VARIABLES
    private final Stage primaryStage;
    private final GuiController controller;
    private boolean loggedIn, loggedInAdmin;

    //JAVA FX
    private BorderPane mainPane;
    private Scene mainScene;

    //GUI
    private Top top;
    private Center center;
    private Bottom bottom;

    //COMMON GUI CONFIGS
    private final int STANDARD_BUTTON_WIDTH = 100;
    private final int STANDARD_BUTTON_HEIGHT = 25;

    public Gui(Stage primaryStage, GuiController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    private class Top {
        private Label headerLabel, userNameLabel, passwordLabel;
        private VBox topVBox;
        private Button loginButton, logoutButton, registerButton;
        private GridPane startViewGridPane;
        private TextField usernameTextField;
        private PasswordField passwordPasswordField;
        private CheckBox adminCheckBox;

        public void setupTop() {
            headerLabel = new Label("Wigell Concerts");
            headerLabel.setStyle("-fx-font-size: 40");

            loginButton = new Button("Login");
            loginButton.setMaxHeight(STANDARD_BUTTON_HEIGHT);
            loginButton.setMinHeight(STANDARD_BUTTON_HEIGHT);
            loginButton.setMaxWidth(STANDARD_BUTTON_WIDTH);
            loginButton.setMinWidth(STANDARD_BUTTON_WIDTH);

            logoutButton = new Button("Logout");
            logoutButton.setMaxHeight(STANDARD_BUTTON_HEIGHT);
            logoutButton.setMinHeight(STANDARD_BUTTON_HEIGHT);
            logoutButton.setMaxWidth(STANDARD_BUTTON_WIDTH);
            logoutButton.setMinWidth(STANDARD_BUTTON_WIDTH);

            registerButton = new Button("Register");
            registerButton.setMaxHeight(STANDARD_BUTTON_HEIGHT);
            registerButton.setMinHeight(STANDARD_BUTTON_HEIGHT);
            registerButton.setMaxWidth(STANDARD_BUTTON_WIDTH);
            registerButton.setMinWidth(STANDARD_BUTTON_WIDTH);

            userNameLabel = new Label("Användarnamn:");
            passwordLabel = new Label("Lösenord:");

            usernameTextField = new TextField();

            passwordPasswordField = new PasswordField();

            adminCheckBox = new CheckBox("Admin login");

            startViewGridPane = new GridPane();
            startViewGridPane.setAlignment(Pos.TOP_LEFT);
            startViewGridPane.setPadding(new Insets(10,10,10,10));
            startViewGridPane.setHgap(10);
            startViewGridPane.setVgap(10);
            startViewGridPane.add(userNameLabel,0,0,1,1);
            startViewGridPane.add(usernameTextField,1,0,1,1);
            startViewGridPane.add(passwordLabel,0,1,1,1);
            startViewGridPane.add(passwordPasswordField,1,1,1,1);
            startViewGridPane.add(loginButton, 0,2,1,1);
            startViewGridPane.add(registerButton, 1,2,1,1);
            startViewGridPane.add(adminCheckBox, 2,2,1,1);


            topVBox = new VBox();
            topVBox.setAlignment(Pos.CENTER);
            topVBox.setPadding(new Insets(10, 10, 10, 10));
            topVBox.getChildren().addAll(headerLabel,startViewGridPane);
            mainPane.setTop(topVBox);

        }
    }

    private class Center {

        //START VIEW
        private Label startViewLabel, loggedInAdminLabel;

        //LOGGED IN VIEW
        private Button buyTicketsButton, boughtTicketsButton;
        private Label headerLabel;
        private HBox loggedInViewButtonHBox, loggedInViewHeaderHBox;
        private VBox loggedInViewMainVBox;


        //BUY TICKETS
        private GridPane concertList;
        private Label artistLabel, venueLabel, ageLimitLabel, dateLabel, priceLabel;
        private Label currencyLabel;
        private ChoiceBox<String> currencyChoiceBox;
        private ObservableList<String> currency;
        private Label[] prices;

        //LOGGED IN ADMIN VIEW

        public void setupCenter() {

            //START VIEW
            startViewLabel = new Label("StartView");

            //LOGGED IN VIEW
            headerLabel = new Label();

            currencyChoiceBox = new ChoiceBox<>();
            currencyChoiceBox.hide();
            currencyChoiceBox.setOnAction(this::currencyChoiceBoxHandler);

            currency = currencyChoiceBox.getItems();
            currency.add("Dollars $");
            currency.add("Euro €");
            currency.add("Swedish Krona Sek");

            buyTicketsButton = new Button("Buy Tickets");
            buyTicketsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            buyTicketsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            boughtTicketsButton = new Button("Bought Tickets");
            boughtTicketsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            boughtTicketsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            loggedInViewButtonHBox = new HBox();
            loggedInViewButtonHBox.setAlignment(Pos.TOP_CENTER);
            loggedInViewButtonHBox.setSpacing(10);
            loggedInViewButtonHBox.setPadding(new Insets(10,10,10,10));
            loggedInViewButtonHBox.getChildren().addAll(buyTicketsButton, boughtTicketsButton);

            loggedInViewHeaderHBox = new HBox();
            loggedInViewHeaderHBox.setAlignment(Pos.TOP_CENTER);
            loggedInViewHeaderHBox.setSpacing(10);
            loggedInViewHeaderHBox.setPadding(new Insets(10,10,10,10));
            loggedInViewHeaderHBox.getChildren().addAll(headerLabel);

            loggedInViewMainVBox = new VBox();
            loggedInViewMainVBox.setAlignment(Pos.TOP_CENTER);
            loggedInViewMainVBox.setSpacing(10);
            loggedInViewMainVBox.setPadding(new Insets(10,10,10,10));
            loggedInViewMainVBox.getChildren().addAll(loggedInViewHeaderHBox, loggedInViewButtonHBox);

            //BUY TICKETS
            artistLabel = new Label("Artist");
            venueLabel = new Label("Arena");
            ageLimitLabel = new Label("Age limit");
            dateLabel = new Label("Date");
            priceLabel = new Label("Price(Sek)");

            concertList = new GridPane();
            concertList.setPadding(new Insets(10,10,10,10));
            concertList.setVgap(10);
            concertList.setHgap(10);

            //LOGGED IN ADMIN VIEW
            loggedInAdminLabel = new Label("LoggedInAdminView");

            startView();
        }

        private void currencyChoiceBoxHandler(ActionEvent actionEvent) {
            String currency = currencyChoiceBox.getValue();

            if(currency.equals("Dollars $")) {
                center.priceLabel.setText("Dollars ($)");
                for(int i=0; i<center.prices.length; i++) {
                    double value = controller.getConcertDTOS().get(i).getPrice();
                    value = value * 0.09;
                    center.prices[i].setText(String.valueOf(value));
                }
            }

            if(currency.equals("Euro €")) {
                center.priceLabel.setText("Euro (€)");
                for(int i=0; i<center.prices.length; i++) {
                    double value = controller.getConcertDTOS().get(i).getPrice();
                    value = value * 0.08;
                    center.prices[i].setText(String.valueOf(value));
                }

            }

            if(currency.equals("Swedish Krona Sek")) {
                center.priceLabel.setText("Swedish Krona (Sek)");
                if(center.prices==null) {
                    return;
                }
                for(int i=0; i<center.prices.length; i++) {
                    center.prices[i].setText(String.valueOf(controller.getConcertDTOS().get(i).getPrice()));
                    System.out.println(controller.getConcertDTOS().get(i).getPrice());
                }
            }
        }

        public void startView() {
            mainPane.setCenter(null);
            mainPane.setCenter(startViewLabel);

        }

        public void loggedInView() {
            mainPane.setCenter(null);
            mainPane.setCenter(loggedInViewMainVBox);
        }

        public void loggedInAdminView() {
            mainPane.setCenter(null);
            mainPane.setCenter(loggedInAdminLabel);
        }
    }

    private class Bottom {
        private VBox bottomVBox;
        private Button exitButton, backButton;

        public void setupBottom() {
            exitButton = new Button("Exit");
            exitButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            exitButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            backButton = new Button("Back");
            backButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            backButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            backButton.setDisable(true);

            bottomVBox = new VBox();
            bottomVBox.setAlignment(Pos.CENTER);
            bottomVBox.setSpacing(10);
            bottomVBox.setPadding(new Insets(10,10,10,10));
            bottomVBox.getChildren().addAll(backButton, exitButton);

            mainPane.setBottom(bottomVBox);
        }
    }

    public void setupGui() {
        try {
            top = new Top();
            center = new Center();
            bottom = new Bottom();

            //MAIN VIEW
            mainPane = new BorderPane();
            mainScene = new Scene(mainPane, 600, 800);

            //MAIN GUI SETUP
            top.setupTop();
            center.setupCenter();
            bottom.setupBottom();

            //INITIATE MAIN VIEW
            center.startView();

            buttonActions();

            primaryStage.setResizable(false);
            primaryStage.setTitle("Wigell Concerts");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonActions() {
        //TOP BUTTONS
        handleExitButton();
        handleBackButton();
        handleLoginButton();
        handleLogoutButton();

        //LOGGED IN VIEW
        handleBuyTicketsButton();
        handleBoughtTicketsButton();


    }

    private void handleBoughtTicketsButton() {
        center.boughtTicketsButton.setOnMouseClicked(event-> {
            center.headerLabel.setText("Bought Tickets");
            clearList();
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().add(center.headerLabel);
        });
    }

    private void handleBuyTicketsButton() {
        center.buyTicketsButton.setOnMouseClicked(event -> {
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().addAll(center.headerLabel, center.currencyChoiceBox);
            center.currencyChoiceBox.setValue("Swedish Krona Sek");
            center.headerLabel.setText("Buy Tickets");

            center.loggedInViewMainVBox.getChildren().remove(center.concertList);
            center.loggedInViewMainVBox.getChildren().add(center.concertList);
            listConcerts();
        });
    }

    private void handleLoginButton() {
        top.loginButton.setOnMouseClicked(event-> {
            loggedInAdmin = false;
            loggedIn = false;

            if(top.adminCheckBox.isSelected()) {
                loggedInAdmin = controller.validateLoginAdmin(top.usernameTextField.getText(), top.passwordPasswordField.getText());
                if(loggedInAdmin) {
                    center.loggedInAdminView();
                    disableLoginNodes();

                    System.out.println("Logged in Admin!");
                }
                else {
                    System.out.println("Not Logged in admin");
                }
            }

            else {
                loggedIn = controller.validateLogin(top.usernameTextField.getText(), top.passwordPasswordField.getText());
                if(loggedIn) {
                    center.loggedInView();
                    disableLoginNodes();
                    clearList();
                    center.loggedInViewHeaderHBox.getChildren().clear();
                    System.out.println("Logged in user!");
                }
                else {
                    System.out.println("not logged in user");
                }
            }
        });
    }

    private void handleLogoutButton() {
        top.logoutButton.setOnMouseClicked(event-> {
            loggedIn = false;
            loggedInAdmin = false;
            top.registerButton.setDisable(false);
            top.usernameTextField.setDisable(false);
            top.usernameTextField.clear();
            top.passwordPasswordField.setDisable(false);
            top.passwordPasswordField.clear();
            top.adminCheckBox.setDisable(false);
            top.adminCheckBox.setSelected(false);
            top.startViewGridPane.getChildren().remove(top.logoutButton);
            top.startViewGridPane.add(top.loginButton,0,2,1,1);

            center.startView();
        });
    }

    private void handleBackButton() {
        bottom.backButton.setOnMouseClicked(event-> {

        });
    }

    private void handleExitButton() {
        bottom.exitButton.setOnMouseClicked(event-> {
            System.exit(0);
        });
    }

    private void disableLoginNodes() {
        top.registerButton.setDisable(true);
        top.usernameTextField.setDisable(true);
        top.passwordPasswordField.setDisable(true);
        top.adminCheckBox.setDisable(true);
        top.startViewGridPane.getChildren().remove(top.loginButton);
        top.startViewGridPane.add(top.logoutButton,0,2,1,1);
    }

    private void listConcerts() {
        int numOfConcerts = controller.getConcertDTOS().size();
        clearList();
        addListHeaders();
        center.prices = new Label[numOfConcerts];
        for(int i=0; i<numOfConcerts; i++) {

            Label artist = new Label();
            Label arena = new Label();
            Label ageLimit = new Label();
            Label date = new Label();
            Label price = new Label();

            artist.setText(controller.getConcertDTOS().get(i).getArtist());
            arena.setText(controller.getConcertDTOS().get(i).getArena().getName());
            ageLimit.setText(String.valueOf(controller.getConcertDTOS().get(i).getAgeLimit()));
            date.setText(String.valueOf(controller.getConcertDTOS().get(i).getDate()));
            price.setText(String.valueOf(controller.getConcertDTOS().get(i).getPrice()));

            center.concertList.add(artist, 0, i+1, 1, 1);
            center.concertList.add(arena, 1, i+1, 1, 1);
            center.concertList.add(ageLimit, 2, i+1, 1, 1);
            center.concertList.add(date, 3, i+1, 1, 1);
            center.concertList.add(price, 4,i+1,1,1);
            center.prices[i]=price;
        }
    }

    private void addListHeaders() {
        center.concertList.add(center.artistLabel,0,0,1,1);
        center.concertList.add(center.venueLabel,1,0,1,1);
        center.concertList.add(center.ageLimitLabel,2,0,1,1);
        center.concertList.add(center.dateLabel,3,0,1,1);
        center.concertList.add(center.priceLabel,4,0,1,1);
    }

    private void clearList() {
        center.concertList.getChildren().clear();
    }

}