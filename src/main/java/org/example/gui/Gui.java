package org.example.gui;

import com.google.protobuf.StringValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.Controller;
import org.example.entities.Arena;
import org.example.entities.Concert;
import org.example.entities.Customer;

import java.math.RoundingMode;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Gui {

    //CONDITION VARIABLES
    private final Stage primaryStage;

    //    private final GuiController controller;
    private Controller controller;
    private boolean loggedIn, loggedInAdmin;

    //JAVA FX
    private BorderPane mainPane;
    private Scene mainScene;

    //GUI
    private Top top;
    private Center center;
    private Bottom bottom;
    private Register register;
    private CustomerList customerList;

    //COMMON GUI CONFIGS
    private final int STANDARD_BUTTON_WIDTH = 100;
    private final int STANDARD_BUTTON_HEIGHT = 25;

    public Gui(Stage primaryStage, Controller controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    private class Top {
        //TOP MAIN
        private Label headerLabel, userNameLabel, passwordLabel;
        private VBox topVBox;
        private Button loginButton, logoutButton, registerButton;
        private GridPane startViewGridPane;
        private TextField usernameTextField;
        private PasswordField passwordPasswordField;
        private CheckBox adminCheckBox;

        //NAV-BAR
        private HBox navBarHBox;

        //LOGGED IN NAV-BAR
        private Button buyTicketsButton, boughtTicketsButton, userInfoButton;

        //ADMIN LOGGED IN NAV-BAR
        private Button addConcertButton, concertsButton, updateConcertButton, addArenasButton, deleteArenasButton;

        public void setupTop() {
            //TOP MAIN
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
            startViewGridPane.setPadding(new Insets(10, 10, 10, 10));
            startViewGridPane.setHgap(10);
            startViewGridPane.setVgap(10);
            startViewGridPane.add(userNameLabel, 0, 0, 1, 1);
            startViewGridPane.add(usernameTextField, 1, 0, 1, 1);
            startViewGridPane.add(passwordLabel, 0, 1, 1, 1);
            startViewGridPane.add(passwordPasswordField, 1, 1, 1, 1);
            startViewGridPane.add(loginButton, 0, 2, 1, 1);
            startViewGridPane.add(registerButton, 1, 2, 1, 1);
            startViewGridPane.add(adminCheckBox, 2, 2, 1, 1);

            topVBox = new VBox();
            topVBox.setAlignment(Pos.CENTER);
            topVBox.setPadding(new Insets(10, 10, 10, 10));
            topVBox.getChildren().addAll(headerLabel, startViewGridPane);
            mainPane.setTop(topVBox);

            //NAV-BAR
            navBarHBox = new HBox();
            navBarHBox.setAlignment(Pos.CENTER);
            navBarHBox.setSpacing(10);
            navBarHBox.setPadding(new Insets(10, 10, 10, 10));
            topVBox.getChildren().add(navBarHBox);

            //LOGGED IN NAV-BAR
            buyTicketsButton = new Button("Buy Tickets");
            buyTicketsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            buyTicketsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            boughtTicketsButton = new Button("Bought Tickets");
            boughtTicketsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            boughtTicketsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            userInfoButton = new Button("User Info");
            userInfoButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            userInfoButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            //ADMIN LOGGED IN NAV-BAR
            addConcertButton = new Button("Add Concert");
            addConcertButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            addConcertButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            concertsButton = new Button("Concerts");
            concertsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            concertsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            updateConcertButton = new Button("Update Concert");
            updateConcertButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            updateConcertButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            addArenasButton = new Button("Add Arenas");
            addArenasButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            addArenasButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            deleteArenasButton = new Button("Delete Arenas");
            deleteArenasButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            deleteArenasButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

        }
    }

    private class Center {

        //START VIEW
        private Label startViewLabel, loggedInAdminLabel;

        //LOGGED IN ADMIN VIEW
        private VBox loggedinAdminViewVBox;

        //LOGGED IN VIEW
        private Label headerLabel;
        private HBox loggedInViewHeaderHBox;
        private VBox loggedInViewMainVBox;

        //USER BUY TICKETS
        private GridPane concertListUser;
        private Label artistLabel, venueLabel, ageLimitLabel, dateLabel, priceLabel;
        private ChoiceBox<String> currencyChoiceBox;
        private ObservableList<String> currency;
        private Label[] prices;
        private ArrayList<Button> buys;

        //USER USER INFO VIEW
        private GridPane userInfoGridPane;
        private Label userFirstnameLabel, userLastnameLabel, userBirthdateLabel, userPhoneLabel;
        private Label userStreetNameLabel, userHouseNumberLabel, userZipcodeLabel, userCityLabel;
        private TextField userFirstnameTextField, userLastnameTextField, userBirthdateTextField, userPhoneTextField;
        private TextField userStreetTextField, userHouseNumberTextField, userZipcodeTextField, userCityTextField;
        private Button userUpdateButton, userRemoveAccountButton;

        //ADMIN ADD ARENAS VIEW
        private GridPane adminAddArenaGridPane;
        private Label arenaNameLabel, arenaAdressStreetLabel, arenaAdressHouseNumberLabel, arenaAdressZipCodeLabel, arenaAdressCityLabel, arenaInsideLabel;
        private TextField arenaNameTextField, arenaAdressStreetTextField, arenaAdressHouseNumberTextField, arenaAdressZipCodeTextField, arenaAdressCityTextField;
        private Button arenaSubmitButton;
        private CheckBox arenaInsideCheckBox;

        //ADMIN ADD CONCERT VIEW
        private GridPane adminAddConcertGridPane;
        private Label concertArtistLabel, concertDateLabel, concertPriceLabel, concertAgeLimitLabel, concertArenaLabel;
        private TextField concertArtistTextField, concertDateTextField, concertPriceTextField, concertAgeLimitTextField;
        private ChoiceBox<String> arenasChoiceBox;
        private ObservableList<String> arenas;
        private Button concertSubmitButton;


        //ADMIN CONCERTS VIEW
        private GridPane concertListAdmin;
        private ArrayList<Button> cancels;
        private ArrayList<Button> customerLists;

        //ADMIN UPDATE CONCERT VIEW
        private ChoiceBox<String> concertChoiceBox;
        private ObservableList<String> concerts;
        private GridPane updateGridPane;
        private TextField updateArtistTextField, updateDateTextField, updatePriceTextField, updateAgelimitTextField;
        private Label updateArtistLabel, updateDateLabel, updatePriceLabel, updateAgelimitLabel, updateArenaLabel;
        private Button updateSubmitButton;

        //ADMIN DELETE ARENA VIEW
        private VBox deleteArenaVBox;
        private Button deleteArenaDeleteButton;

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

            loggedInViewHeaderHBox = new HBox();
            loggedInViewHeaderHBox.setAlignment(Pos.TOP_CENTER);
            loggedInViewHeaderHBox.setSpacing(10);
            loggedInViewHeaderHBox.setPadding(new Insets(10, 10, 10, 10));
            loggedInViewHeaderHBox.getChildren().addAll(headerLabel);

            loggedInViewMainVBox = new VBox();
            loggedInViewMainVBox.setAlignment(Pos.TOP_CENTER);
            loggedInViewMainVBox.setSpacing(10);
            loggedInViewMainVBox.setPadding(new Insets(10, 10, 10, 10));
            loggedInViewMainVBox.getChildren().addAll(loggedInViewHeaderHBox);

            //BUY TICKETS
            artistLabel = new Label("Artist");
            venueLabel = new Label("Arena");
            ageLimitLabel = new Label("Age limit");
            dateLabel = new Label("Date");
            priceLabel = new Label("Price(Sek)");

            concertListUser = new GridPane();
            concertListUser.setPadding(new Insets(10, 10, 10, 10));
            concertListUser.setVgap(10);
            concertListUser.setHgap(10);

            //USER USERINFO
            userInfoGridPane = new GridPane();
            userInfoGridPane.setPadding(new Insets(10, 10, 10, 10));
            userInfoGridPane.setHgap(10);
            userInfoGridPane.setVgap(10);

            userFirstnameLabel = new Label("Firstname:");
            userLastnameLabel = new Label("Lastname:");
            userBirthdateLabel = new Label("Birthdate");
            userPhoneLabel = new Label("Phone:");
            userStreetNameLabel = new Label("Street:");
            userHouseNumberLabel = new Label("Housenumber:");
            userZipcodeLabel = new Label("Zipcode:");
            userCityLabel = new Label("City:");

            userFirstnameTextField = new TextField();
            userFirstnameTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userLastnameTextField = new TextField();
            userLastnameTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userBirthdateTextField = new TextField();
            userBirthdateTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userPhoneTextField = new TextField();
            userPhoneTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userStreetTextField = new TextField();
            userStreetTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userHouseNumberTextField = new TextField();
            userHouseNumberTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userZipcodeTextField = new TextField();
            userZipcodeTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userCityTextField = new TextField();
            userCityTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            userUpdateButton = new Button("Update");
            userUpdateButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            userUpdateButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            userRemoveAccountButton = new Button("Remove Account");
            userRemoveAccountButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            userRemoveAccountButton.setPrefWidth(120);

            userInfoGridPane.add(userFirstnameLabel, 0, 0, 1, 1);
            userInfoGridPane.add(userFirstnameTextField, 1, 0, 1, 1);
            userInfoGridPane.add(userLastnameLabel, 0, 1, 1, 1);
            userInfoGridPane.add(userLastnameTextField, 1, 1, 1, 1);
            userInfoGridPane.add(userBirthdateLabel, 0, 2, 1, 1);
            userInfoGridPane.add(userBirthdateTextField, 1, 2, 1, 1);
            userBirthdateTextField.setDisable(true);
            userInfoGridPane.add(userPhoneLabel, 0, 3, 1, 1);
            userInfoGridPane.add(userPhoneTextField, 1, 3, 1, 1);
            userInfoGridPane.add(userStreetNameLabel, 0, 4, 1, 1);
            userInfoGridPane.add(userStreetTextField, 1, 4, 1, 1);
            userInfoGridPane.add(userHouseNumberLabel, 0, 5, 1, 1);
            userInfoGridPane.add(userHouseNumberTextField, 1, 5, 1, 1);
            userInfoGridPane.add(userZipcodeLabel, 0, 6, 1, 1);
            userInfoGridPane.add(userZipcodeTextField, 1, 6, 1, 1);
            userInfoGridPane.add(userCityLabel, 0, 7, 1, 1);
            userInfoGridPane.add(userCityTextField, 1, 7, 1, 1);
            userInfoGridPane.add(userUpdateButton, 0, 8, 1, 1);
            userInfoGridPane.add(userRemoveAccountButton, 1, 8, 1, 1);


            //LOGGED IN ADMIN VIEW
            loggedInAdminLabel = new Label();

            loggedinAdminViewVBox = new VBox();
            loggedinAdminViewVBox.setAlignment(Pos.TOP_CENTER);
            loggedinAdminViewVBox.setSpacing(10);
            loggedinAdminViewVBox.setPadding(new Insets(10, 10, 10, 10));
            loggedinAdminViewVBox.getChildren().addAll(loggedInAdminLabel);

            //ADD ARENAS VIEW
            adminAddArenaGridPane = new GridPane();
            adminAddArenaGridPane.setPadding(new Insets(10, 10, 10, 10));
            adminAddArenaGridPane.setVgap(10);
            adminAddArenaGridPane.setHgap(10);

            arenaSubmitButton = new Button("Submit");

            arenaInsideCheckBox = new CheckBox();

            arenaNameLabel = new Label("Name:");
            arenaAdressStreetLabel = new Label("Street:");
            arenaAdressHouseNumberLabel = new Label("Nr:");
            arenaAdressZipCodeLabel = new Label("ZipCode:");
            arenaAdressCityLabel = new Label("City:");
            arenaInsideLabel = new Label("Inside:");

            arenaNameTextField = new TextField();
            arenaAdressStreetTextField = new TextField();
            arenaAdressHouseNumberTextField = new TextField();
            arenaAdressZipCodeTextField = new TextField();
            arenaAdressCityTextField = new TextField();

            adminAddArenaGridPane.add(arenaNameLabel, 0, 0, 1, 1);
            adminAddArenaGridPane.add(arenaNameTextField, 1, 0, 1, 1);
            adminAddArenaGridPane.add(arenaAdressStreetLabel, 0, 1, 1, 1);
            adminAddArenaGridPane.add(arenaAdressStreetTextField, 1, 1, 1, 1);
            adminAddArenaGridPane.add(arenaAdressHouseNumberLabel, 0, 2, 1, 1);
            adminAddArenaGridPane.add(arenaAdressHouseNumberTextField, 1, 2, 1, 1);
            adminAddArenaGridPane.add(arenaAdressZipCodeLabel, 0, 3, 1, 1);
            adminAddArenaGridPane.add(arenaAdressZipCodeTextField, 1, 3, 1, 1);
            adminAddArenaGridPane.add(arenaAdressCityLabel, 0, 4, 1, 1);
            adminAddArenaGridPane.add(arenaAdressCityTextField, 1, 4, 1, 1);
            adminAddArenaGridPane.add(arenaInsideLabel, 0, 5, 1, 1);
            adminAddArenaGridPane.add(arenaInsideCheckBox, 1, 5, 1, 1);
            adminAddArenaGridPane.add(arenaSubmitButton, 0, 6, 1, 1);

            //ADD CONCERT VIEW
            adminAddConcertGridPane = new GridPane();
            adminAddConcertGridPane.setPadding(new Insets(10, 10, 10, 10));
            adminAddConcertGridPane.setVgap(10);
            adminAddConcertGridPane.setHgap(10);

            concertSubmitButton = new Button("Submit");

            concertArtistLabel = new Label("Artist");
            concertDateLabel = new Label("Date:");
            concertPriceLabel = new Label("Price:");
            concertAgeLimitLabel = new Label("Age Limit:");
            concertArenaLabel = new Label("Arena:");


            concertArtistTextField = new TextField();
            concertDateTextField = new TextField();
            concertDateTextField.setPromptText("yyyy-mm-dd");
            concertPriceTextField = new TextField();
            concertAgeLimitTextField = new TextField();

            arenasChoiceBox = new ChoiceBox<>();
            addArenasToChoiceBox();

            adminAddConcertGridPane.add(concertArtistLabel, 0, 0, 1, 1);
            adminAddConcertGridPane.add(concertArtistTextField, 1, 0, 1, 1);
            adminAddConcertGridPane.add(concertDateLabel, 0, 1, 1, 1);
            adminAddConcertGridPane.add(concertDateTextField, 1, 1, 1, 1);
            adminAddConcertGridPane.add(concertPriceLabel, 0, 2, 1, 1);
            adminAddConcertGridPane.add(concertPriceTextField, 1, 2, 1, 1);
            adminAddConcertGridPane.add(concertAgeLimitLabel, 0, 3, 1, 1);
            adminAddConcertGridPane.add(concertAgeLimitTextField, 1, 3, 1, 1);
            adminAddConcertGridPane.add(concertArenaLabel, 0, 4, 1, 1);
            adminAddConcertGridPane.add(arenasChoiceBox, 1, 4, 1, 1);
            adminAddConcertGridPane.add(concertSubmitButton, 0, 5, 1, 1);

            //ADMIN DELETE CONCERT VIEW
            concertListAdmin = new GridPane();
            concertListAdmin.setPadding(new Insets(10, 10, 10, 10));
            concertListAdmin.setVgap(10);
            concertListAdmin.setHgap(10);

            //ADMIN UPDATE CONCERT VIEW
            updateGridPane = new GridPane();
            updateGridPane.setAlignment(Pos.CENTER);
            updateGridPane.setVgap(10);
            updateGridPane.setHgap(10);

            updateSubmitButton = new Button("Update");
            updateSubmitButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            updateSubmitButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            updateArtistLabel = new Label("Artist:");
            updateDateLabel = new Label("Date:");
            updateAgelimitLabel = new Label("Age Limit:");
            updatePriceLabel = new Label("Price:");
            updateArenaLabel = new Label("Arena:");

            updateArtistTextField = new TextField();
            updateArtistTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            updateDateTextField = new TextField();
            updateDateTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            updateAgelimitTextField = new TextField();
            updateAgelimitTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            updatePriceTextField = new TextField();
            updatePriceTextField.setMaxWidth(STANDARD_BUTTON_WIDTH);

            concertChoiceBox = new ChoiceBox<>();
            addConcertsToChoiceBox();
            concertChoiceBox.setOnAction(this::concertsChoiceBoxHandler);

            //ADMIN DELETE ARENAS VIEW
            deleteArenaVBox = new VBox();
            deleteArenaVBox.setAlignment(Pos.CENTER);
            deleteArenaVBox.setSpacing(10);
            deleteArenaVBox.setPadding(new Insets(10, 10, 10, 10));
            deleteArenaDeleteButton = new Button("Delete");
        }

        private void concertsChoiceBoxHandler(ActionEvent actionEvent) {
            if(concertChoiceBox.getValue()==null) {
                return;
            }

            String[] concertInputStrings = concertChoiceBox.getValue().split("/");
            String concertInput = concertInputStrings[0];
            System.out.println(concertInput + "==============================================");
//            String concertInput = concertChoiceBox.getValue();
            Concert concert = controller.getConcertDAO().getConcertByArtist(concertInput);
            System.out.println("Artist: " + concert.getArtist());
//            concertInputStrings = null;

            if (updateGridPane != null) {
                updateGridPane.getChildren().clear();
            }
            try {
                updateArtistTextField.setText(concert.getArtist());
                updateDateTextField.setText(String.valueOf(concert.getDate()));
                updateAgelimitTextField.setText(String.valueOf(concert.getAgeLimit()));
                updatePriceTextField.setText(String.valueOf(concert.getPrice()));
                arenasChoiceBox.setValue(concert.getArena().getName());

                updateGridPane.add(updateArtistLabel, 0, 0, 1, 1);
                updateGridPane.add(updateArtistTextField, 1, 0, 1, 1);
                updateGridPane.add(updateDateLabel, 0, 1, 1, 1);
                updateGridPane.add(updateDateTextField, 1, 1, 1, 1);
                updateGridPane.add(updateAgelimitLabel, 0, 2, 1, 1);
                updateGridPane.add(updateAgelimitTextField, 1, 2, 1, 1);
                updateGridPane.add(updatePriceLabel, 0, 3, 1, 1);
                updateGridPane.add(updatePriceTextField, 1, 3, 1, 1);
                updateGridPane.add(updateArenaLabel, 0, 4, 1, 1);
                updateGridPane.add(arenasChoiceBox, 1, 4, 1, 1);
                updateGridPane.add(updateSubmitButton, 0, 5, 1, 1);

            } catch (NullPointerException ignored) {

            }

        }

        private void currencyChoiceBoxHandler(ActionEvent actionEvent) {
            String currency = currencyChoiceBox.getValue();
            if (currency.equals("Dollars $")) {
                center.priceLabel.setText("Dollars ($)");
                for (int i = 0; i < center.prices.length; i++) {
                    double value = controller.getConcertDAO().getAllConcerts().get(i).getPrice();
                    value = value * 0.09;
                    center.prices[i].setText(String.format("%.2f", value));
                }
            }

            if (currency.equals("Euro €")) {
                center.priceLabel.setText("Euro (€)");
                for (int i = 0; i < center.prices.length; i++) {
                    double value = controller.getConcertDAO().getAllConcerts().get(i).getPrice();
                    value = value * 0.08;
                    center.prices[i].setText(String.format("%.2f", value));
                }

            }

            if (currency.equals("Swedish Krona Sek")) {
                center.priceLabel.setText("Swedish Krona (Sek)");
                if (center.prices == null) {
                    return;
                }
                for (int i = 0; i < center.prices.length; i++) {
                    center.prices[i].setText(String.format("%.2f",controller.getConcertDAO().getAllConcerts().get(i).getPrice()));
                    System.out.println(controller.getConcertDAO().getAllConcerts().get(i).getPrice());
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
            mainPane.setCenter(loggedinAdminViewVBox);
        }
    }

    private class Bottom {
        private VBox bottomVBox;
        private Button exitButton;

        public void setupBottom() {
            exitButton = new Button("Exit");
            exitButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            exitButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            bottomVBox = new VBox();
            bottomVBox.setAlignment(Pos.CENTER);
            bottomVBox.setSpacing(10);
            bottomVBox.setPadding(new Insets(10, 10, 10, 10));
            bottomVBox.getChildren().addAll(exitButton);

            mainPane.setBottom(bottomVBox);
        }
    }

    private class Register {
        private Stage registerStage;
        private BorderPane registerPane;
        private Scene registerScene;

        private Label registerHeaderLabel, firstnameRegisterLabel, lastnameRegisterLabel, birthdateRegisterLabel, phonenumberRegisterLabel, passwordRegisterLabel, passwordComfirmRegisterLabel;
        private Label registerAdressStreetLabel, registerAdressHouseNumberLabel, registerAdressZipCodeLabel, registerAdressCityLabel, registerAdressAdressLabel;
        private TextField firstnameRegisterTextField, lastnameRegisterTextField, birthdayRegisterTextField, phonenumberRegisterTextField, registerAdressStreetTextField, registerAdressHouseNumberTextField, registerAdressZipCodeTextField, registerAdressCityTextField;
        private PasswordField passwordRegisterPasswordField, passwordConfirmRegisterPasswordField;
        private Button registerSubmitButton, registerCancelButton;
        private GridPane registerMainGrid;

        public void setupRegister() {
            registerStage = new Stage();
            registerPane = new BorderPane();
            registerScene = new Scene(registerPane, 230, 475);
            registerStage.setScene(registerScene);

            registerMainGrid = new GridPane();
            registerMainGrid.setPadding(new Insets(10, 10, 10, 10));
            registerMainGrid.setVgap(10);
            registerMainGrid.setHgap(10);

            registerHeaderLabel = new Label("Register new User");
            registerHeaderLabel.setStyle("-fx-font-size: 25");
            registerHeaderLabel.setAlignment(Pos.CENTER);

            firstnameRegisterLabel = new Label("Firstname:");
            lastnameRegisterLabel = new Label("Lastname:");
            birthdateRegisterLabel = new Label("Birthdate:");
            phonenumberRegisterLabel = new Label("Phonenumber:");
            passwordRegisterLabel = new Label("Password:");
            passwordComfirmRegisterLabel = new Label("Confirm Password:");
            registerAdressAdressLabel = new Label("Adress");
            registerAdressStreetLabel = new Label("Street:");
            registerAdressHouseNumberLabel = new Label("Nr:");
            registerAdressZipCodeLabel = new Label("ZipCode:");
            registerAdressCityLabel = new Label("City:");

            firstnameRegisterTextField = new TextField();
            firstnameRegisterTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            firstnameRegisterTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            lastnameRegisterTextField = new TextField();
            lastnameRegisterTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            lastnameRegisterTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            birthdayRegisterTextField = new TextField();
            birthdayRegisterTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            birthdayRegisterTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);
            birthdayRegisterTextField.setPromptText("YYYY-MM-DD");

            phonenumberRegisterTextField = new TextField();
            phonenumberRegisterTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            phonenumberRegisterTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            registerAdressStreetTextField = new TextField();
            registerAdressStreetTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            registerAdressStreetTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            registerAdressHouseNumberTextField = new TextField();
            registerAdressHouseNumberTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);
            registerAdressHouseNumberTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            registerAdressZipCodeTextField = new TextField();
            registerAdressZipCodeTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            registerAdressZipCodeTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            registerAdressCityTextField = new TextField();
            registerAdressCityTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);
            registerAdressCityTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            passwordRegisterPasswordField = new PasswordField();
            passwordRegisterPasswordField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            passwordRegisterPasswordField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            passwordConfirmRegisterPasswordField = new PasswordField();
            passwordConfirmRegisterPasswordField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            passwordConfirmRegisterPasswordField.setPrefWidth(STANDARD_BUTTON_WIDTH);

            registerSubmitButton = new Button("Submit");
            registerSubmitButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            registerSubmitButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            registerCancelButton = new Button("Cancel");
            registerCancelButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            registerCancelButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            registerMainGrid.add(firstnameRegisterLabel, 0, 0, 1, 1);
            registerMainGrid.add(firstnameRegisterTextField, 1, 0, 1, 1);
            registerMainGrid.add(lastnameRegisterLabel, 0, 1, 1, 1);
            registerMainGrid.add(lastnameRegisterTextField, 1, 1, 1, 1);
            registerMainGrid.add(birthdateRegisterLabel, 0, 2, 1, 1);
            registerMainGrid.add(birthdayRegisterTextField, 1, 2, 1, 1);
            registerMainGrid.add(phonenumberRegisterLabel, 0, 3, 1, 1);
            registerMainGrid.add(phonenumberRegisterTextField, 1, 3, 1, 1);
            registerMainGrid.add(passwordRegisterLabel, 0, 4, 1, 1);
            registerMainGrid.add(passwordRegisterPasswordField, 1, 4, 1, 1);
            registerMainGrid.add(passwordComfirmRegisterLabel, 0, 5, 1, 1);
            registerMainGrid.add(passwordConfirmRegisterPasswordField, 1, 5, 1, 1);
            registerMainGrid.add(registerAdressAdressLabel, 0, 6, 1, 1);
            registerMainGrid.add(registerAdressStreetLabel, 0, 7, 1, 1);
            registerMainGrid.add(registerAdressStreetTextField, 1, 7, 1, 1);
            registerMainGrid.add(registerAdressHouseNumberLabel, 0, 8, 1, 1);
            registerMainGrid.add(registerAdressHouseNumberTextField, 1, 8, 1, 1);
            registerMainGrid.add(registerAdressZipCodeLabel, 0, 9, 1, 1);
            registerMainGrid.add(registerAdressZipCodeTextField, 1, 9, 1, 1);
            registerMainGrid.add(registerAdressCityLabel, 0, 10, 1, 1);
            registerMainGrid.add(registerAdressCityTextField, 1, 10, 1, 1);
            registerMainGrid.add(registerSubmitButton, 0, 11, 1, 1);
            registerMainGrid.add(registerCancelButton, 1, 11, 1, 1);

            registerPane.setTop(registerHeaderLabel);
            registerPane.setCenter(registerMainGrid);
        }
    }

    private class CustomerList {
        private Stage customerListStage;
        private BorderPane customerListPane;
        private Scene customerListScene;

        private Label customerListHeaderLabel;

        private GridPane customerListGridPane;
        private HBox customerListTopHBox;

        public void setupCustomerList() {
            customerListStage = new Stage();
            customerListPane = new BorderPane();
            customerListScene = new Scene(customerListPane, 350, 600);
            customerListStage.setTitle("CustomerList");
            customerListStage.setScene(customerListScene);


            customerListGridPane = new GridPane();
            customerListGridPane.setAlignment(Pos.TOP_CENTER);
            customerListGridPane.setVgap(10);
            customerListGridPane.setHgap(10);

            customerListHeaderLabel = new Label("Customer List");
            customerListHeaderLabel.setStyle("-fx-font-size: 25");
            customerListHeaderLabel.setAlignment(Pos.CENTER);

            customerListTopHBox = new HBox();
            customerListTopHBox.setAlignment(Pos.CENTER);
            customerListTopHBox.setPadding(new Insets(10, 10, 10, 10));
            customerListTopHBox.setSpacing(10);
            customerListTopHBox.getChildren().add(customerListHeaderLabel);

            customerListPane.setTop(customerListTopHBox);
            customerListPane.setCenter(customerListGridPane);
        }
    }

    public void setupGui() {
        try {
            top = new Top();
            center = new Center();
            bottom = new Bottom();
            register = new Register();
            customerList = new CustomerList();

            //MAIN VIEW
            mainPane = new BorderPane();
            mainScene = new Scene(mainPane, 600, 800);

            //MAIN GUI SETUP
            top.setupTop();
            center.setupCenter();
            bottom.setupBottom();
            register.setupRegister();
            customerList.setupCustomerList();

            //INITIATE MAIN VIEW
            center.startView();

            buttonActions();

            primaryStage.setResizable(false);
            primaryStage.setTitle("Wigell Concerts");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonActions() {
        //MAIN BUTTONS
        handleExitButton();
        handleLoginButton();
        handleLogoutButton();
        handleRegisterButton();

        //LOGGED IN VIEW
        handleBuyTicketsButton();
        handleBoughtTicketsButton();
        handleUserInfoButton();
        handleUserInfoUpdateButton();
        handleUserInfoRemoveAccountButton();

        //ADMIN VIEW
        handleAddArenaButton();
        handleAddArenaSubmitButton();
        handleDeleteArenaButton();
        handleDeleteArenaSubmitButton();
        handleAddConcertButton();
        handleAddConcertSubmitButton();
        handleUpdateConcertButton();
        handleUpdateConcertSubmitButton();
        handleConcertsButton();

        //REGISTER
        handleRegisterCancelButton();
        handleRegisterSubmitButton();

    }

    //ADMIN-VIEW BUTTONS
    private void handleAddArenaButton() {
        top.addArenasButton.setOnMouseClicked(event -> {
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Add Arena");
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.adminAddArenaGridPane);
        });
    }

    private void handleDeleteArenaButton() {
        top.deleteArenasButton.setOnMouseClicked(event -> {
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Delete Arena");
            center.deleteArenaVBox.getChildren().clear();
            center.deleteArenaVBox.getChildren().addAll(center.arenasChoiceBox, center.deleteArenaDeleteButton);
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.deleteArenaVBox);
        });
    }

    private void handleAddConcertButton() {
        top.addConcertButton.setOnMouseClicked(event -> {
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Add Concert");

            try {
                center.adminAddConcertGridPane.add(center.arenasChoiceBox, 1, 4, 1, 1);
            } catch (IllegalArgumentException ignored) {

            }

            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.adminAddConcertGridPane);
        });
    }

    private void handleConcertsButton() {
        top.concertsButton.setOnMouseClicked(event -> {
            clearConcertsAdmin();
            listConcertsAdmin();
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Concerts");
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.concertListAdmin);

            handleCancelButtons();
            handleCustomerListButtons();
        });

    }

    private void handleUpdateConcertButton() {
        top.updateConcertButton.setOnMouseClicked(event -> {
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Update Concert");
            center.updateGridPane.getChildren().clear();
            center.concertChoiceBox.setValue(null);
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.concertChoiceBox, center.updateGridPane);
        });
    }

    private void handleAddArenaSubmitButton() {
        center.arenaSubmitButton.setOnMouseClicked(event -> {
            boolean arenaAdded;
            boolean inside = false;

            List<String> addArenaInputs = new ArrayList<>();
            addArenaInputs.add(center.arenaNameTextField.getText());
            addArenaInputs.add(center.arenaAdressStreetTextField.getText());
            addArenaInputs.add(center.arenaAdressHouseNumberTextField.getText());
            addArenaInputs.add(center.arenaAdressZipCodeTextField.getText().replace(" ", ""));
            addArenaInputs.add(center.arenaAdressCityTextField.getText());

            if (center.arenaInsideCheckBox.isSelected()) {
                inside = true;
            }

            arenaAdded = controller.addNewArena(addArenaInputs, inside);

            if (arenaAdded) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Arena added");
                alert.setHeaderText("Arena: " + center.arenaNameTextField.getText());
                alert.setContentText("Adress: " + center.arenaAdressStreetTextField.getText() + center.arenaAdressHouseNumberTextField.getText() + "\n" +
                        "City: " + center.arenaAdressCityTextField.getText());
                alert.showAndWait();
                center.arenaNameTextField.clear();
                center.arenaAdressStreetTextField.clear();
                center.arenaAdressHouseNumberTextField.clear();
                center.arenaAdressZipCodeTextField.clear();
                center.arenaAdressCityTextField.clear();

                addArenasToChoiceBox();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Arena not added");
                alert.setHeaderText("Something went wrong");
                alert.showAndWait();
            }
        });
    }

    private void handleAddConcertSubmitButton() {
        center.concertSubmitButton.setOnMouseClicked(event -> {
            boolean concertAdded;
            List<String> addConcertInputs = new ArrayList<>();
            addConcertInputs.add(center.concertArtistTextField.getText());
            addConcertInputs.add(center.concertDateTextField.getText());
            addConcertInputs.add(center.concertPriceTextField.getText());
            addConcertInputs.add(center.concertAgeLimitTextField.getText());
            addConcertInputs.add(center.arenasChoiceBox.getValue());

            concertAdded = controller.addNewConcert(addConcertInputs);

            if (concertAdded) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Concert added");
                alert.setHeaderText("Artist: " + center.concertArtistTextField.getText());
                alert.setContentText("Date: " + center.concertDateTextField.getText() + "\n" +
                        "Arena: " + center.arenasChoiceBox.getValue());
                alert.showAndWait();

                center.concertDateTextField.clear();
                center.concertArtistTextField.clear();
                center.concertPriceTextField.clear();
                center.concertAgeLimitTextField.clear();
                addConcertsToChoiceBox();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Concert not added");
                alert.setHeaderText("Something went wrong");
                alert.showAndWait();
            }
        });


    }

    private void handleUpdateConcertSubmitButton() {
        center.updateSubmitButton.setOnMouseClicked(event -> {
            List<String> editConcertInputs = new ArrayList<>();

            String[] concertInputStrings = center.concertChoiceBox.getValue().split("/");
            String concertInput = concertInputStrings[0];

            editConcertInputs.add(concertInput);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to update this concert?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                editConcertInputs.add(center.updateArtistTextField.getText());
                editConcertInputs.add(center.updateDateTextField.getText());
                editConcertInputs.add(center.updateAgelimitTextField.getText());
                editConcertInputs.add(center.updatePriceTextField.getText());
                editConcertInputs.add(center.arenasChoiceBox.getValue());
                controller.updateConcert(editConcertInputs);
                addConcertsToChoiceBox();

                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setHeaderText("The concert is updated!");
                alert2.showAndWait();

//                center.updateArtistTextField.clear();
//                center.updateDateTextField.clear();
//                center.updateAgelimitTextField.clear();
//                center.updatePriceTextField.clear();
//                center.arenasChoiceBox.getValue();
                center.loggedinAdminViewVBox.getChildren().clear();
                center.loggedInAdminLabel.setText("Update Concert");
                center.updateGridPane.getChildren().clear();
                center.concertChoiceBox.setValue(null);
                center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.concertChoiceBox, center.updateGridPane);

            }
        });
    }

    private void handleCancelButtons() {
        List<Concert> concerts = controller.getConcertDAO().getAllConcerts();
        for (int i = 0; i < center.cancels.size(); i++) {
            int index = i;
            center.cancels.get(i).setOnMouseClicked(event -> {
                center.cancels.get(index).setText("Canceled");
                center.cancels.get(index).setDisable(true);
                center.cancels.remove(index);
                center.customerLists.get(index).setDisable(true);
                center.customerLists.remove(index);
                System.out.println(index);
                System.out.println(concerts.get(index).getArtist());
                controller.deleteConcert(index); // Ropa på delete concert metod
                handleCancelButtons();
                /*handleCustomerListButtons();*/
                addConcertsToChoiceBox();
            });
        }
    }

    private void handleCustomerListButtons() {
        List<Concert> concerts = controller.getConcertDAO().getAllConcerts();
        for (int i = 0; i < center.customerLists.size(); i++) {
            int index = i;
            center.customerLists.get(i).setOnMouseClicked(event -> {
//                concerts.get(index).getCustomers();
                customerList.customerListGridPane.getChildren().clear();
                customerList.customerListHeaderLabel.setText(concerts.get(index).getArtist() + " " + concerts.get(index).getDate());
                for (int j = 0; j < concerts.get(index).getCustomers().size(); j++) {

                    Label firstname = new Label();
                    Label lastname = new Label();
                    Label birthDate = new Label();
                    Label phone = new Label();

                    firstname.setText(concerts.get(index).getCustomers().get(j).getFirstName());
                    customerList.customerListGridPane.add(firstname, 0, j, 1, 1);
                    lastname.setText(concerts.get(index).getCustomers().get(j).getLastName());
                    customerList.customerListGridPane.add(lastname, 1, j, 1, 1);
                    birthDate.setText(String.valueOf(concerts.get(index).getCustomers().get(j).getBirthdate()));
                    customerList.customerListGridPane.add(birthDate, 2, j, 1, 1);
                    phone.setText(concerts.get(index).getCustomers().get(j).getPhoneNumber());
                    customerList.customerListGridPane.add(phone, 3, j, 1, 1);
                }
                customerList.customerListStage.show();
            });
        }
    }

    private void handleDeleteArenaSubmitButton() {
        center.deleteArenaDeleteButton.setOnMouseClicked(event -> {

            Arena arena = controller.getArenaDAO().getArenaByName(center.arenasChoiceBox.getValue());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to delete " + arena.getName() + "?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == yesButton) {
                if (controller.deleteArena(arena)) {
                    addArenasToChoiceBox();
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setHeaderText("You deleted arena " + arena.getName() + "!");
                    alert2.showAndWait();
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setHeaderText("You must first cancel all concerts on " + arena.getName() + "!");
                    alert2.showAndWait();
                }
            }
        });
    }

    //REGISTER BUTTONS
    private void handleRegisterCancelButton() {
        register.registerCancelButton.setOnMouseClicked(event -> {
            register.registerStage.hide();
            register.firstnameRegisterTextField.clear();
            register.passwordRegisterPasswordField.clear();
            register.passwordConfirmRegisterPasswordField.clear();
        });
    }

    private void handleRegisterSubmitButton() {

        register.registerSubmitButton.setOnMouseClicked(event -> {
            boolean userAdded;
            List<String> addRegisterInputs = new ArrayList<>();
            addRegisterInputs.add(register.firstnameRegisterTextField.getText());
            addRegisterInputs.add(register.lastnameRegisterTextField.getText());
            addRegisterInputs.add(register.birthdayRegisterTextField.getText());
            addRegisterInputs.add(register.phonenumberRegisterTextField.getText());
            addRegisterInputs.add(register.passwordRegisterPasswordField.getText());
            addRegisterInputs.add(register.passwordConfirmRegisterPasswordField.getText());
            addRegisterInputs.add(register.registerAdressStreetTextField.getText());
            addRegisterInputs.add(register.registerAdressHouseNumberTextField.getText());
            addRegisterInputs.add(register.registerAdressZipCodeTextField.getText());
            addRegisterInputs.add(register.registerAdressCityTextField.getText());

            userAdded = controller.registerNewUser(addRegisterInputs);

            if (userAdded) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Registration successful");
                alert.setHeaderText("Username: " + register.firstnameRegisterTextField.getText());
                alert.showAndWait();

                register.firstnameRegisterTextField.clear();
                register.passwordRegisterPasswordField.clear();
                register.passwordConfirmRegisterPasswordField.clear();
                register.registerAdressStreetTextField.clear();
                register.registerAdressHouseNumberTextField.clear();
                register.registerAdressZipCodeTextField.clear();
                register.registerAdressCityTextField.clear();
                register.registerStage.hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Registration unsuccessful");
                alert.setHeaderText("Something went wrong");
                alert.showAndWait();
            }
        });
    }

    private void handleBuyButton() {
        List<Concert> concerts = controller.getConcertDAO().getAllConcerts();

        for (int i = 0; i < center.buys.size(); i++) {
            int index = i;
            center.buys.get(i).setOnMouseClicked(event -> {

                if (controller.addConcertsToCustomer(concerts.get(index).getId())) {
                    center.buys.get(index).setText("Bought");
                    center.buys.get(index).setDisable(true);
                    System.out.println(index);
                    System.out.println(concerts.get(index).getArtist());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ticket Bought");
                    alert.setHeaderText("You bought a ticket for " + concerts.get(index).getArtist());
                    alert.setContentText("Date: " + concerts.get(index).getDate());
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Under Aged!");
                    alert.setHeaderText("You can't buy a ticket for " + concerts.get(index).getArtist());
                    alert.showAndWait();
                }
            });
        }
    }

    //LOGGED USER IN BUTTONS
    private void handleBoughtTicketsButton() {
        top.boughtTicketsButton.setOnMouseClicked(event -> {
            center.headerLabel.setText("Bought Tickets");
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().add(center.headerLabel);
            center.loggedInViewMainVBox.getChildren().clear();
            center.loggedInViewMainVBox.getChildren().addAll(center.loggedInViewHeaderHBox, center.concertListUser);
            listUserBoughtConcerts();
        });
    }

    private void handleBuyTicketsButton() {
        top.buyTicketsButton.setOnMouseClicked(event -> {
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().addAll(center.headerLabel, center.currencyChoiceBox);
            center.currencyChoiceBox.setValue("Swedish Krona Sek");
            center.headerLabel.setText("Buy Tickets");
            center.loggedInViewMainVBox.getChildren().clear();
            center.loggedInViewMainVBox.getChildren().addAll(center.loggedInViewHeaderHBox, center.concertListUser);
/*            for(int i=0; i<controller.getActiveUser().getConcerts().size(); i++) {
                System.out.println(controller.getActiveUser().getConcerts().get(i).getArtist());
            }*/
            listAvailableConcertsUser();
            handleBuyButton();
        });
    }

    private void handleUserInfoButton() {
        top.userInfoButton.setOnMouseClicked(event -> {
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().add(center.headerLabel);
            center.headerLabel.setText("User information");
            center.loggedInViewMainVBox.getChildren().clear();
            center.loggedInViewMainVBox.getChildren().addAll(center.loggedInViewHeaderHBox, center.userInfoGridPane);
            getUserInfoFilled();
        });
    }

    private void handleUserInfoUpdateButton() {
        center.userUpdateButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to update your info?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                List<String> editUserInputs = new ArrayList<>();
                editUserInputs.add(center.userFirstnameTextField.getText());
                editUserInputs.add(center.userLastnameTextField.getText());
                editUserInputs.add(center.userBirthdateTextField.getText());
                editUserInputs.add(center.userPhoneTextField.getText());
                editUserInputs.add(center.userStreetTextField.getText());
                editUserInputs.add(center.userHouseNumberTextField.getText());
                editUserInputs.add(center.userZipcodeTextField.getText());
                editUserInputs.add(center.userCityTextField.getText());
                controller.updateCustomer(editUserInputs);
            }
        });
    }

    private void handleUserInfoRemoveAccountButton() {
        center.userRemoveAccountButton.setOnMouseClicked(event -> {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to delete your account?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                controller.getCustomerDAO().deleteCustomer(controller.getCurrentCustomer().getId());
                controller.setCurrentCustomer(null);
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
                top.startViewGridPane.add(top.loginButton, 0, 2, 1, 1);
                top.navBarHBox.getChildren().clear();

                center.startView();
            }

        });
    }

    //MAIN BUTTONS
    private void handleLoginButton() {
        top.loginButton.setOnMouseClicked(event -> {
            loggedInAdmin = false;
            loggedIn = false;

            if (top.adminCheckBox.isSelected()) {
                loggedInAdmin = controller.validateLoginAdmin(top.usernameTextField.getText(), top.passwordPasswordField.getText());
                if (loggedInAdmin) {
                    center.loggedInAdminView();
                    disableLoginNodes();
                    top.navBarHBox.getChildren().clear();
                    top.navBarHBox.getChildren().add(top.addConcertButton);
                    top.navBarHBox.getChildren().add(top.updateConcertButton);
                    top.navBarHBox.getChildren().add(top.concertsButton);
                    top.navBarHBox.getChildren().add(top.addArenasButton);
                    top.navBarHBox.getChildren().add(top.deleteArenasButton);

                    System.out.println("Logged in Admin!");
                } else {
                    System.out.println("Not Logged in admin");
                }
            } else {
                loggedIn = controller.validateLogin(top.usernameTextField.getText(), top.passwordPasswordField.getText());
                if (loggedIn) {
                    center.loggedInView();
                    disableLoginNodes();
                    clearList();
                    center.loggedInViewHeaderHBox.getChildren().clear();
                    top.navBarHBox.getChildren().clear();
                    top.navBarHBox.getChildren().add(top.buyTicketsButton);
                    top.navBarHBox.getChildren().add(top.boughtTicketsButton);
                    top.navBarHBox.getChildren().add(top.userInfoButton);
                    getUserInfoFilled();//TODO Ska denna vara här? Släpar i userinformationview annars om man loggar ut och sedan in med ny kund.
                    System.out.println("Logged in user!");
                } else {
                    System.out.println("not logged in user");
                }
            }
        });
    }

    private void handleLogoutButton() {
        top.logoutButton.setOnMouseClicked(event -> {
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
            top.startViewGridPane.add(top.loginButton, 0, 2, 1, 1);
            top.navBarHBox.getChildren().clear();
            controller.logOut();

            center.startView();
        });
    }

    private void handleRegisterButton() {
        top.registerButton.setOnMouseClicked(event -> {
            register.registerStage.show();
        });

    }

    private void handleExitButton() {
        bottom.exitButton.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

    //GUI METHODS
    private void disableLoginNodes() {
        top.registerButton.setDisable(true);
        top.usernameTextField.setDisable(true);
        top.passwordPasswordField.setDisable(true);
        top.adminCheckBox.setDisable(true);
        top.startViewGridPane.getChildren().remove(top.loginButton);
        top.startViewGridPane.add(top.logoutButton, 0, 2, 1, 1);
    }

    private void listAvailableConcertsUser() {
        List<Concert> concerts = controller.getConcertDAO().getAllConcerts();
        List<Concert> userConcerts = controller.getCurrentCustomer().getConcerts();
        int numOfConcerts = concerts.size();
        center.buys = new ArrayList<>();
        clearList();
        addListHeaders();
        center.prices = new Label[numOfConcerts];

        for (int i = 0; i < numOfConcerts; i++) {
            Concert concert = concerts.get(i);

            Label artist = new Label();
            Label arena = new Label();
            Label ageLimit = new Label();
            Label date = new Label();
            Label price = new Label();
            Button buy = new Button("Buy");

            artist.setText(concert.getArtist());
            arena.setText(concert.getArena().getName());
            ageLimit.setText(String.valueOf(concert.getAgeLimit()));
            date.setText(String.valueOf(concert.getDate()));
            price.setText(String.format("%.2f",concert.getPrice()));

           /* artist.setText(controller.getConcertDTOS().get(i).getArtist());
            arena.setText(controller.getConcertDTOS().get(i).getArena().getName());
            ageLimit.setText(String.valueOf(controller.getConcertDTOS().get(i).getAgeLimit()));
            date.setText(String.valueOf(controller.getConcertDTOS().get(i).getDate()));
            price.setText(String.valueOf(controller.getConcertDTOS().get(i).getPrice()));*/

            center.concertListUser.add(artist, 0, i + 1, 1, 1);
            center.concertListUser.add(arena, 1, i + 1, 1, 1);
            center.concertListUser.add(ageLimit, 2, i + 1, 1, 1);
            center.concertListUser.add(date, 3, i + 1, 1, 1);
            center.concertListUser.add(price, 4, i + 1, 1, 1);

            try {
                for (int j = 0; j < userConcerts.size(); j++) {
                    if (userConcerts.get(j).getArtist().equals(concerts.get(i).getArtist())) {
                        buy.setText("Bought");
                        buy.setDisable(true);
                    }

                }
            } catch (IndexOutOfBoundsException ignored) {

            }

            center.concertListUser.add(buy, 5, i + 1, 1, 1);
            center.buys.add(buy);
            center.prices[i] = price;

        }
    }

    private void listUserBoughtConcerts() {
        try {
            List<Concert> userConcerts = controller.getCurrentCustomer().getConcerts();

            int numOfConcerts = userConcerts.size();
            clearList();
            for (int i = 0; i < numOfConcerts; i++) {
                Concert concert = userConcerts.get(i);

                Label artist = new Label();
                Label arena = new Label();
                Label ageLimit = new Label();
                Label date = new Label();
                Label price = new Label();

                artist.setText(concert.getArtist());
                arena.setText(concert.getArena().getName());
                ageLimit.setText(String.valueOf(concert.getAgeLimit()));
                date.setText(String.valueOf(concert.getDate()));
                price.setText(String.valueOf(concert.getPrice()));

                center.concertListUser.add(artist, 0, i + 1, 1, 1);
                center.concertListUser.add(arena, 1, i + 1, 1, 1);
                center.concertListUser.add(ageLimit, 2, i + 1, 1, 1);
                center.concertListUser.add(date, 3, i + 1, 1, 1);
                center.concertListUser.add(price, 4, i + 1, 1, 1);
            }
        } catch (NullPointerException ignored) {

        }

    }

    private void listConcertsAdmin() {
        List<Concert> adminConcerts = controller.getConcertDAO().getAllConcerts();
        int numOfConcerts = adminConcerts.size();
        center.customerLists = new ArrayList<>();
        center.cancels = new ArrayList();
        for (int i = 0; i < numOfConcerts; i++) {
            Concert concert = adminConcerts.get(i);

            Label artist = new Label();
            Label arena = new Label();
            Label ageLimit = new Label();
            Label date = new Label();
            Label price = new Label();
            Button cancel = new Button("Cancel");
            Button customerList = new Button("Customer List");

            artist.setText(concert.getArtist());
            arena.setText(concert.getArena().getName());
            ageLimit.setText(String.valueOf(concert.getAgeLimit()));
            date.setText(String.valueOf(concert.getDate()));
            price.setText(String.valueOf(concert.getPrice()));

            center.concertListAdmin.add(artist, 0, i + 1, 1, 1);
            center.concertListAdmin.add(arena, 1, i + 1, 1, 1);
            center.concertListAdmin.add(ageLimit, 2, i + 1, 1, 1);
            center.concertListAdmin.add(date, 3, i + 1, 1, 1);
            center.concertListAdmin.add(price, 4, i + 1, 1, 1);
            center.concertListAdmin.add(cancel, 5, i + 1, 1, 1);
            center.concertListAdmin.add(customerList, 6, i + 1, 1, 1);

            center.cancels.add(cancel);
            center.customerLists.add(customerList);
        }
    }

    private void addListHeaders() {
        center.concertListUser.add(center.artistLabel, 0, 0, 1, 1);
        center.concertListUser.add(center.venueLabel, 1, 0, 1, 1);
        center.concertListUser.add(center.ageLimitLabel, 2, 0, 1, 1);
        center.concertListUser.add(center.dateLabel, 3, 0, 1, 1);
        center.concertListUser.add(center.priceLabel, 4, 0, 1, 1);
    }

    private void clearList() {
        center.concertListUser.getChildren().clear();
    }

    private void clearConcertsAdmin() {
        center.concertListAdmin.getChildren().clear();
    }

    private void addArenasToChoiceBox() {
        if (center.arenas != null) {
            center.arenas.clear();
        }

        List<Arena> arenas = controller.getArenaDAO().getAllArenas();

        center.arenas = center.arenasChoiceBox.getItems();
        for (int i = 0; i < arenas.size(); i++) {
            center.arenas.add(arenas.get(i).getName());
        }
    }

    private void addConcertsToChoiceBox() {
        if (center.concerts != null) {
            center.concerts.clear();
        }

        List<Concert> concerts = controller.getConcertDAO().getAllConcerts();
        center.concerts = center.concertChoiceBox.getItems();
        for (int i = 0; i < concerts.size(); i++) {
            center.concerts.add(concerts.get(i).getArtist() + "/" + concerts.get(i).getDate() + "/" + concerts.get(i).getArena().getName());
        }
    }

    private void getUserInfoFilled() {
        Customer customer = controller.getCurrentCustomer();
        center.userFirstnameTextField.setText(customer.getFirstName());
        center.userLastnameTextField.setText(customer.getLastName());
        center.userBirthdateTextField.setText(String.valueOf(customer.getBirthdate()));
        center.userPhoneTextField.setText(String.valueOf(customer.getPhoneNumber()));
        center.userStreetTextField.setText(customer.getAddress().getStreetName());
        center.userHouseNumberTextField.setText(String.valueOf(customer.getAddress().getHouseNumber()));
        center.userZipcodeTextField.setText(String.valueOf(customer.getAddress().getZipCode()));
        center.userCityTextField.setText(customer.getAddress().getCity());
    }
}