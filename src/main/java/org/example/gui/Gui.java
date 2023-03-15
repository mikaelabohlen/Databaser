package org.example.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.gui.guidto.ArenaDTO;
import org.example.gui.guidto.ConcertDTO;
import org.example.gui.guidto.UserDTO;

import java.util.ArrayList;
import java.util.List;


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
    private Register register;

    //COMMON GUI CONFIGS
    private final int STANDARD_BUTTON_WIDTH = 100;
    private final int STANDARD_BUTTON_HEIGHT = 25;

    public Gui(Stage primaryStage, GuiController controller) {
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
        private Button buyTicketsButton, boughtTicketsButton;

        //ADMIN LOGGED IN NAV-BAR
        private Button addConcertButton, deleteConcertButton, updateConcertButton, addArenasButton;

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

            //NAV-BAR
            navBarHBox = new HBox();
            navBarHBox.setAlignment(Pos.CENTER);
            navBarHBox.setSpacing(10);
            navBarHBox.setPadding(new Insets(10,10,10,10));
            topVBox.getChildren().add(navBarHBox);

            //LOGGED IN NAV-BAR
            buyTicketsButton = new Button("Buy Tickets");
            buyTicketsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            buyTicketsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            boughtTicketsButton = new Button("Bought Tickets");
            boughtTicketsButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            boughtTicketsButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            //ADMIN LOGGED IN NAV-BAR
            addConcertButton = new Button("Add Concert");
            addConcertButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            addConcertButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            deleteConcertButton = new Button("Delete Concert");
            deleteConcertButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            deleteConcertButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

            updateConcertButton = new Button("Update Concert");
            updateConcertButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            updateConcertButton.setPrefWidth(STANDARD_BUTTON_WIDTH);

            addArenasButton = new Button("Add Arenas");
            addArenasButton.setPrefWidth(STANDARD_BUTTON_WIDTH);
            addArenasButton.setPrefHeight(STANDARD_BUTTON_HEIGHT);

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

        //ADMIN ADD ARENAS VIEW
        private GridPane adminAddArenaGridPane;
        private Label arenaNameLabel, arenaAdressStreetLabel, arenaAdressHouseNumberLabel, arenaAdressZipCodeLabel, arenaAdressCityLabel;
        private TextField arenaNameTextField, arenaAdressStreetTextField, arenaAdressHouseNumberTextField, arenaAdressZipCodeTextField, arenaAdressCityTextField;
        private Button arenaSubmitButton;

        //ADMIN ADD CONCERT VIEW
        private GridPane adminAddConcertGridPane;
        private Label concertArtistLabel, concertDateLabel, concertPriceLabel, concertAgeLimitLabel, concertArenaLabel;
        private TextField concertArtistTextField, concertDateTextField, concertPriceTextField, concertAgeLimitTextField;
        private ChoiceBox<String> arenasChoiceBox;
        private ObservableList<String> arenas;
        private Button concertSubmitButton;

        //ADMIN DELETE CONCERT VIEW
        private GridPane concertListAdmin;
        private ArrayList<Button>cancels;

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
            loggedInViewHeaderHBox.setPadding(new Insets(10,10,10,10));
            loggedInViewHeaderHBox.getChildren().addAll(headerLabel);

            loggedInViewMainVBox = new VBox();
            loggedInViewMainVBox.setAlignment(Pos.TOP_CENTER);
            loggedInViewMainVBox.setSpacing(10);
            loggedInViewMainVBox.setPadding(new Insets(10,10,10,10));
            loggedInViewMainVBox.getChildren().addAll(loggedInViewHeaderHBox);

            //BUY TICKETS
            artistLabel = new Label("Artist");
            venueLabel = new Label("Arena");
            ageLimitLabel = new Label("Age limit");
            dateLabel = new Label("Date");
            priceLabel = new Label("Price(Sek)");

            concertListUser = new GridPane();
            concertListUser.setPadding(new Insets(10,10,10,10));
            concertListUser.setVgap(10);
            concertListUser.setHgap(10);

            //LOGGED IN ADMIN VIEW
            loggedInAdminLabel = new Label();

            loggedinAdminViewVBox = new VBox();
            loggedinAdminViewVBox.setAlignment(Pos.TOP_CENTER);
            loggedinAdminViewVBox.setSpacing(10);
            loggedinAdminViewVBox.setPadding(new Insets(10,10,10,10));
            loggedinAdminViewVBox.getChildren().addAll(loggedInAdminLabel);

            //ADD ARENAS VIEW
            adminAddArenaGridPane = new GridPane();
            adminAddArenaGridPane.setPadding(new Insets(10,10,10,10));
            adminAddArenaGridPane.setVgap(10);
            adminAddArenaGridPane.setHgap(10);

            arenaSubmitButton = new Button("Submit");

            arenaNameLabel = new Label("Name:");
            arenaAdressStreetLabel = new Label("Street:");
            arenaAdressHouseNumberLabel = new Label("Nr:");
            arenaAdressZipCodeLabel = new Label("ZipCode:");
            arenaAdressCityLabel = new Label("City:");

            arenaNameTextField = new TextField();
            arenaAdressStreetTextField = new TextField();
            arenaAdressHouseNumberTextField = new TextField();
            arenaAdressZipCodeTextField = new TextField();
            arenaAdressCityTextField = new TextField();

            adminAddArenaGridPane.add(arenaNameLabel,0,0,1,1);
            adminAddArenaGridPane.add(arenaNameTextField,1,0,1,1);
            adminAddArenaGridPane.add(arenaAdressStreetLabel,0,1,1,1);
            adminAddArenaGridPane.add(arenaAdressStreetTextField,1,1,1,1);
            adminAddArenaGridPane.add(arenaAdressHouseNumberLabel,0,2,1,1);
            adminAddArenaGridPane.add(arenaAdressHouseNumberTextField,1,2,1,1);
            adminAddArenaGridPane.add(arenaAdressZipCodeLabel,0,3,1,1);
            adminAddArenaGridPane.add(arenaAdressZipCodeTextField,1,3,1,1);
            adminAddArenaGridPane.add(arenaAdressCityLabel,0,4,1,1);
            adminAddArenaGridPane.add(arenaAdressCityTextField, 1,4,1,1);
            adminAddArenaGridPane.add(arenaSubmitButton,0,5,1,1);

            //ADD CONCERT VIEW
            adminAddConcertGridPane = new GridPane();
            adminAddConcertGridPane.setPadding(new Insets(10,10,10,10));
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

            adminAddConcertGridPane.add(concertArtistLabel,0,0,1,1);
            adminAddConcertGridPane.add(concertArtistTextField,1,0,1,1);
            adminAddConcertGridPane.add(concertDateLabel, 0,1,1,1);
            adminAddConcertGridPane.add(concertDateTextField,1,1,1,1);
            adminAddConcertGridPane.add(concertPriceLabel,0,2,1,1);
            adminAddConcertGridPane.add(concertPriceTextField,1,2 ,1,1);
            adminAddConcertGridPane.add(concertAgeLimitLabel,0,3,1,1);
            adminAddConcertGridPane.add(concertAgeLimitTextField,1,3,1,1);
            adminAddConcertGridPane.add(concertArenaLabel,0,4,1,1);
            adminAddConcertGridPane.add(arenasChoiceBox,1,4,1,1);
            adminAddConcertGridPane.add(concertSubmitButton,0,5,1,1);

            //ADMIN DELETE CONCERT VIEW
            concertListAdmin = new GridPane();
            concertListAdmin.setPadding(new Insets(10,10,10,10));
            concertListAdmin.setVgap(10);
            concertListAdmin.setHgap(10);

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
            mainPane.setCenter(loggedinAdminViewVBox);
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

    private class Register {
        private Stage registerStage;
        private BorderPane registerPane;
        private Scene registerScene;

        private Label registerHeaderLabel, usernameRegisterLabel, passwordRegisterLabel, passwordComfirmRegisterLabel;
        private Label registerAdressStreetLabel, registerAdressHouseNumberLabel, registerAdressZipCodeLabel, registerAdressCityLabel, registerAdressAdressLabel;
        private TextField usernameRegisterTextField, registerAdressStreetTextField, registerAdressHouseNumberTextField,registerAdressZipCodeTextField,registerAdressCityTextField;
        private PasswordField passwordRegisterPasswordField, passwordConfirmRegisterPasswordField;
        private Button registerSubmitButton, registerCancelButton;
        private GridPane registerMainGrid;

        public void setupRegister() {
            registerStage = new Stage();
            registerPane = new BorderPane();
            registerScene = new Scene(registerPane, 230, 350);
            registerStage.setScene(registerScene);

            registerMainGrid = new GridPane();
            registerMainGrid.setPadding(new Insets(10,10,10,10));
            registerMainGrid.setVgap(10);
            registerMainGrid.setHgap(10);

            registerHeaderLabel = new Label("Register new User");
            registerHeaderLabel.setStyle("-fx-font-size: 25");
            registerHeaderLabel.setAlignment(Pos.CENTER);

            usernameRegisterLabel = new Label("Username:");
            passwordRegisterLabel = new Label("Password:");
            passwordComfirmRegisterLabel = new Label("Confirm Password:");
            registerAdressAdressLabel = new Label("Adress");
            registerAdressStreetLabel = new Label("Street:");
            registerAdressHouseNumberLabel = new Label("Nr:");
            registerAdressZipCodeLabel = new Label("ZipCode:");
            registerAdressCityLabel = new Label("City:");

            usernameRegisterTextField = new TextField();
            usernameRegisterTextField.setPrefHeight(STANDARD_BUTTON_HEIGHT);
            usernameRegisterTextField.setPrefWidth(STANDARD_BUTTON_WIDTH);

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

            registerMainGrid.add(usernameRegisterLabel,0,0,1,1);
            registerMainGrid.add(usernameRegisterTextField,1,0,1,1);
            registerMainGrid.add(passwordRegisterLabel, 0,1,1,1);
            registerMainGrid.add(passwordRegisterPasswordField, 1,1,1,1);
            registerMainGrid.add(passwordComfirmRegisterLabel, 0,2,1,1);
            registerMainGrid.add(passwordConfirmRegisterPasswordField, 1,2,1,1);
            registerMainGrid.add(registerAdressAdressLabel, 0,3,1,1);
            registerMainGrid.add(registerAdressStreetLabel,0,4,1,1);
            registerMainGrid.add(registerAdressStreetTextField, 1,4,1,1);
            registerMainGrid.add(registerAdressHouseNumberLabel,0,5,1,1);
            registerMainGrid.add(registerAdressHouseNumberTextField,1,5,1,1);
            registerMainGrid.add(registerAdressZipCodeLabel,0,6,1,1);
            registerMainGrid.add(registerAdressZipCodeTextField,1,6,1,1);
            registerMainGrid.add(registerAdressCityLabel,0,7,1,1);
            registerMainGrid.add(registerAdressCityTextField,1,7,1,1);
            registerMainGrid.add(registerSubmitButton, 0,8,1,1);
            registerMainGrid.add(registerCancelButton, 1,8,1,1);

            registerPane.setTop(registerHeaderLabel);
            registerPane.setCenter(registerMainGrid);
        }
    }
    public void setupGui() {
        try {
            top = new Top();
            center = new Center();
            bottom = new Bottom();
            register = new Register();


            //MAIN VIEW
            mainPane = new BorderPane();
            mainScene = new Scene(mainPane, 600, 800);

            //MAIN GUI SETUP
            top.setupTop();
            center.setupCenter();
            bottom.setupBottom();
            register.setupRegister();

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
        handleRegisterButton();

        //LOGGED IN VIEW
        handleBuyTicketsButton();
        handleBoughtTicketsButton();

        //ADMIN VIEW
        handleAddArenaButton();
        handleAddArenaSubmitButton();
        handleAddConcertButton();
        handleAddConcertSubmitButton();
        handleDeleteConcertButton();


        //REGISTER
        handleRegisterCancelButton();
        handleRegisterSubmitButton();

    }

    //ADMIN-VIEW BUTTONS
    private void handleAddArenaButton() {
        top.addArenasButton.setOnMouseClicked(event-> {
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Add Arena");
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.adminAddArenaGridPane);
        });
    }

    private void handleAddConcertButton() {
        top.addConcertButton.setOnMouseClicked(event-> {
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Add Concert");
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.adminAddConcertGridPane);
        });
    }

    private void handleDeleteConcertButton() {
        top.deleteConcertButton.setOnMouseClicked(event-> {
            clearConcertsAdmin();
            listConcertsAdmin();
            center.loggedinAdminViewVBox.getChildren().clear();
            center.loggedInAdminLabel.setText("Delete Concert");
            center.loggedinAdminViewVBox.getChildren().addAll(center.loggedInAdminLabel, center.concertListAdmin);

            handleCancelButtons();
        });

    }

    private void handleAddArenaSubmitButton() {
        center.arenaSubmitButton.setOnMouseClicked(event-> {
            boolean arenaAdded;
            List<String> addArenaInputs = new ArrayList<>();
            addArenaInputs.add(center.arenaNameTextField.getText());
            addArenaInputs.add(center.arenaAdressStreetTextField.getText());
            addArenaInputs.add(center.arenaAdressHouseNumberTextField.getText());
            addArenaInputs.add(center.arenaAdressZipCodeTextField.getText().replace(" ", ""));
            addArenaInputs.add(center.arenaAdressCityTextField.getText());

            arenaAdded = controller.addnewArena(addArenaInputs);

            if(arenaAdded) {

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
            }

            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Arena not added");
                alert.setHeaderText("Something went wrong");
                alert.showAndWait();
            }
        });
    }

    private void handleAddConcertSubmitButton() {
        center.concertSubmitButton.setOnMouseClicked(event-> {
            boolean concertAdded;
            List<String> addConcertInputs = new ArrayList<>();
            addConcertInputs.add(center.concertArtistTextField.getText());
            addConcertInputs.add(center.concertDateTextField.getText());
            addConcertInputs.add(center.concertPriceTextField.getText());
            addConcertInputs.add(center.concertAgeLimitTextField.getText());
            addConcertInputs.add(center.arenasChoiceBox.getValue());

            concertAdded = controller.addNewConcert(addConcertInputs);

            if(concertAdded) {
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
            }

            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Concert not added");
                alert.setHeaderText("Something went wrong");
                alert.showAndWait();
            }
        });


    }

    private void handleCancelButtons() {
        List<ConcertDTO> concerts = controller.getConcertDTOS();
        for (int i = 0; i <center.cancels.size(); i++) {
            int index = i;
            center.cancels.get(i).setOnMouseClicked(event -> {
                center.cancels.get(index).setText("Canceled");
                center.cancels.get(index).setDisable(true);
                center.cancels.remove(index);
                System.out.println(index);
                System.out.println(concerts.get(index).getArtist());
                controller.getConcertDTOS().remove(index); // Ropa på delete concert metod
                handleCancelButtons();
            });
        }
    }

    //REGISTER BUTTONS
    private void handleRegisterCancelButton() {
        register.registerCancelButton.setOnMouseClicked(event-> {
            register.registerStage.hide();
            register.usernameRegisterTextField.clear();
            register.passwordRegisterPasswordField.clear();
            register.passwordConfirmRegisterPasswordField.clear();
        });
    }

    private void handleRegisterSubmitButton() {

        register.registerSubmitButton.setOnMouseClicked(event-> {
            boolean userAdded;
            List<String> addRegisterInputs = new ArrayList<>();
            addRegisterInputs.add(register.usernameRegisterTextField.getText());
            addRegisterInputs.add(register.passwordRegisterPasswordField.getText());
            addRegisterInputs.add(register.passwordConfirmRegisterPasswordField.getText());
            addRegisterInputs.add(register.registerAdressStreetTextField.getText());
            addRegisterInputs.add(register.registerAdressHouseNumberTextField.getText());
            addRegisterInputs.add(register.registerAdressZipCodeTextField.getText());
            addRegisterInputs.add(register.registerAdressCityTextField.getText());

            userAdded = controller.registerNewUser(addRegisterInputs);

            if(userAdded) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Registration successful");
                alert.setHeaderText("Username: " + register.usernameRegisterTextField.getText());
                alert.showAndWait();

                register.usernameRegisterTextField.clear();
                register.passwordRegisterPasswordField.clear();
                register.passwordConfirmRegisterPasswordField.clear();
                register.registerAdressStreetTextField.clear();
                register.registerAdressHouseNumberTextField.clear();
                register.registerAdressZipCodeTextField.clear();
                register.registerAdressCityTextField.clear();
                register.registerStage.hide();
            }

            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Registration unsuccessful");
                alert.setHeaderText("Something went wrong");
                alert.showAndWait();
            }
        });
    }

    private void handleBuyButton() {
        List<ConcertDTO> concerts = controller.getConcertDTOS();

        for(int i=0; i<center.buys.size(); i++) {
            int index = i;
            center.buys.get(i).setOnMouseClicked(event-> {

                center.buys.get(index).setText("Bought");
                center.buys.get(index).setDisable(true);
                System.out.println(index);
                System.out.println(concerts.get(index).getArtist());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ticket Bought");
                alert.setHeaderText("You bought a ticket for " + concerts.get(index).getArtist());
                alert.setContentText("Date: " + concerts.get(index).getDate());
                alert.showAndWait();

                controller.addToUserConcert(index);
                /*center.buys.get(index).setText("Bought");
                center.buys.get(index).setDisable(true);
                System.out.println(index);
                System.out.println(controller.getConcertDTOS().get(index).getArtist());
                controller.addToUserConcert(index);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ticket Bought");
                alert.setHeaderText("You bought a ticket for " + controller.getConcertDTOS().get(index).getArtist());
                alert.setContentText("Date: " + controller.getConcertDTOS().get(index).getDate());
                alert.showAndWait();*/
            });
        }
    }

    //LOGGED USER IN BUTTONS
    private void handleBoughtTicketsButton() {
        top.boughtTicketsButton.setOnMouseClicked(event-> {
            center.headerLabel.setText("Bought Tickets");
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().add(center.headerLabel);
            center.loggedInViewMainVBox.getChildren().remove(center.concertListUser);
            center.loggedInViewMainVBox.getChildren().add(center.concertListUser);
            listUserBoughtConcerts();
        });
    }

    private void handleBuyTicketsButton() {
        top.buyTicketsButton.setOnMouseClicked(event -> {
            center.loggedInViewHeaderHBox.getChildren().clear();
            center.loggedInViewHeaderHBox.getChildren().addAll(center.headerLabel, center.currencyChoiceBox);
            center.currencyChoiceBox.setValue("Swedish Krona Sek");
            center.headerLabel.setText("Buy Tickets");
            center.loggedInViewMainVBox.getChildren().remove(center.concertListUser);
            center.loggedInViewMainVBox.getChildren().add(center.concertListUser);
/*            for(int i=0; i<controller.getActiveUser().getConcerts().size(); i++) {
                System.out.println(controller.getActiveUser().getConcerts().get(i).getArtist());
            }*/
            listAvailableConcertsUser();
            handleBuyButton();
        });
    }

    //MAIN BUTTONS
    private void handleLoginButton() {
        top.loginButton.setOnMouseClicked(event-> {
            loggedInAdmin = false;
            loggedIn = false;

            if(top.adminCheckBox.isSelected()) {
                loggedInAdmin = controller.validateLoginAdmin(top.usernameTextField.getText(), top.passwordPasswordField.getText());
                if(loggedInAdmin) {
                    center.loggedInAdminView();
                    disableLoginNodes();
                    top.navBarHBox.getChildren().clear();
                    top.navBarHBox.getChildren().add(top.addConcertButton);
                    top.navBarHBox.getChildren().add(top.deleteConcertButton);
                    top.navBarHBox.getChildren().add(top.updateConcertButton);
                    top.navBarHBox.getChildren().add(top.addArenasButton);

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
                    top.navBarHBox.getChildren().clear();
                    top.navBarHBox.getChildren().add(top.buyTicketsButton);
                    top.navBarHBox.getChildren().add(top.boughtTicketsButton);
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
            top.navBarHBox.getChildren().clear();

            center.startView();
        });
    }

    private void handleRegisterButton() {
        top.registerButton.setOnMouseClicked(event-> {
            register.registerStage.show();
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

    //GUI METHODS
    private void disableLoginNodes() {
        top.registerButton.setDisable(true);
        top.usernameTextField.setDisable(true);
        top.passwordPasswordField.setDisable(true);
        top.adminCheckBox.setDisable(true);
        top.startViewGridPane.getChildren().remove(top.loginButton);
        top.startViewGridPane.add(top.logoutButton,0,2,1,1);
    }

    private void listAvailableConcertsUser() {
        List<ConcertDTO> concerts = controller.getConcertDTOS();
        int numOfConcerts = concerts.size();
        center.buys = new ArrayList<>();
        clearList();
        addListHeaders();
        center.prices = new Label[numOfConcerts];

        for(int i=0; i<numOfConcerts; i++) {
            ConcertDTO concert = concerts.get(i);

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
            price.setText(String.valueOf(concert.getPrice()));

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
                for(int j=0; j<concerts.size(); j++) {
                    if(concerts.equals(artist.getText())) {
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
            List<ConcertDTO> userConcerts = controller.getActiveUser().getConcerts();

            int numOfConcerts = userConcerts.size();
            clearList();
            for(int i=0; i<numOfConcerts; i++) {
                ConcertDTO concert = userConcerts.get(i);

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
        //TODO Flytta cancel knappen längst till höger
        List<ConcertDTO> adminConcerts = controller.getConcertDTOS();
        int numOfConcerts = adminConcerts.size();
        center.cancels = new ArrayList();
        for(int i=0; i<numOfConcerts; i++) {
            ConcertDTO concert = adminConcerts.get(i);

            Label artist = new Label();
            Label arena = new Label();
            Label ageLimit = new Label();
            Label date = new Label();
            Label price = new Label();
            Button cancel = new Button("Cancel");

            artist.setText(concert.getArtist());
            arena.setText(concert.getArena().getName());
            ageLimit.setText(String.valueOf(concert.getAgeLimit()));
            date.setText(String.valueOf(concert.getDate()));
            price.setText(String.valueOf(concert.getPrice()));

/*            Label artist = new Label();
            Label arena = new Label();
            Label ageLimit = new Label();
            Label date = new Label();
            Label price = new Label();
            Button cancel = new Button("Cancel");

            artist.setText(controller.getConcertDTOS().get(i).getArtist());
            arena.setText(controller.getConcertDTOS().get(i).getArena().getName());
            ageLimit.setText(String.valueOf(controller.getConcertDTOS().get(i).getAgeLimit()));
            date.setText(String.valueOf(controller.getConcertDTOS().get(i).getDate()));
            price.setText(String.valueOf(controller.getConcertDTOS().get(i).getPrice()));*/

            center.concertListAdmin.add(cancel,0,i+1,1,1);
            center.concertListAdmin.add(artist, 1, i+1, 1, 1);
            center.concertListAdmin.add(arena, 2, i+1, 1, 1);
            center.concertListAdmin.add(ageLimit, 3, i+1, 1, 1);
            center.concertListAdmin.add(date, 4, i+1, 1, 1);
            center.concertListAdmin.add(price, 5,i+1,1,1);

            center.cancels.add(cancel);
        }
    }

    private void addListHeaders() {
        center.concertListUser.add(center.artistLabel,0,0,1,1);
        center.concertListUser.add(center.venueLabel,1,0,1,1);
        center.concertListUser.add(center.ageLimitLabel,2,0,1,1);
        center.concertListUser.add(center.dateLabel,3,0,1,1);
        center.concertListUser.add(center.priceLabel,4,0,1,1);
    }

    private void clearList() {
        center.concertListUser.getChildren().clear();
    }

    private void clearConcertsAdmin() {
        center.concertListAdmin.getChildren().clear();
    }

    private void addArenasToChoiceBox() {
        if(center.arenas!=null){
            center.arenas.clear();
        }

        List<ArenaDTO> arenas = controller.getArenaDTOS();

        center.arenas = center.arenasChoiceBox.getItems();
        for(int i=0; i<arenas.size(); i++) {
            center.arenas.add(arenas.get(i).getName());
        }
    }
}