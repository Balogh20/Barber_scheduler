package hu.unideb.inf;

import hu.unideb.inf.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {
    private PersonDao<Customer> customerDao = new PersonDao<>(Customer.class);
    private PersonDao<Barber> barberDao = new PersonDao<>(Barber.class);
    private AppointmentDao appointmentDao = new AppointmentDao();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hairdresser Appointment Booking");

        // Initialize database with some data
        initializeDatabase();
        // Login Scene
        GridPane loginPane = new GridPane();
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Log in");
        Label loginLabel = new Label("Please log in");
        loginPane.add(loginLabel, 0, 0);
        loginPane.add(usernameField, 0, 1);
        loginPane.add(passwordField, 0, 2);
        loginPane.add(loginButton, 0, 3);

        Scene loginScene = new Scene(loginPane, 300, 200);

         // Customer interface
         GridPane customerPane = new GridPane();
         ComboBox<Barber> barberComboBox = new ComboBox<>();
         DatePicker appointmentDatePicker = new DatePicker();
         Button bookButton = new Button("Book appointment");
         Text bookingStatusText = new Text();
         customerPane.add(new Label("Select a barber:"), 0, 0);
         customerPane.add(barberComboBox, 1, 0);
         customerPane.add(new Label("Select a date:"), 0, 1);
         customerPane.add(appointmentDatePicker, 1, 1);
         customerPane.add(bookButton, 0, 2);
         customerPane.add(bookingStatusText, 0, 3);



         // Barber interface
        ListView<Appointment> appointmentListView = new ListView<>();
        VBox barberPane = new VBox(appointmentListView);

        // Main Scene
        VBox mainPane = new VBox();
        Scene mainScene = new Scene(mainPane, 500, 400);

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Customer customer = customerDao.findByUsername(username);
            Barber barber = barberDao.findByUsername(username);

            if (customer != null && customer.getPassword().equals(password)) {
                setupCustomerInterface(customer);
            } 
             else if (barber != null && barber.getPassword().equals(password)) {
                // Set up barber interface
                ObservableList<Appointment> appointments = FXCollections.observableArrayList(appointmentDao.findAllByBarber(barber));
                appointmentListView.setItems(appointments);
                mainPane.getChildren().setAll(barberPane);
                primaryStage.setScene(mainScene);
            }
          
             else {
                loginLabel.setText("Invalid username or password. Please try again.");
            }
        });

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void setupCustomerInterface(Customer customer) {
        VBox mainPane;
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customer_interface.fxml"));
            mainPane = loader.load();
        // Display customer's name
        Label welcomeLabel = new Label("Welcome, " + customer.getName());
        mainPane.getChildren().add(welcomeLabel);
    
        // Display appointments
        List<Appointment> appointments = appointmentDao.findAll();
        ListView<String> appointmentsList = new ListView<>();
        for (Appointment appointment : appointments) {
            if (appointment.getCustomer().getId() == customer.getId()) {
                appointmentsList.getItems().add(appointment.toString());
            }
        }
        mainPane.getChildren().add(appointmentsList);
    
        // Allow customer to book an appointment
        Label bookAppointmentLabel = new Label("Book an appointment:");
        mainPane.getChildren().add(bookAppointmentLabel);
    
        DatePicker datePicker = new DatePicker();
        mainPane.getChildren().add(datePicker);
    
        Button bookButton = new Button("Book Appointment");
        bookButton.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            if (date != null) {
                // For this example, we'll assume that the first barber is the one who will take the appointment.
                Barber barber = barberDao.findAll().get(0);
                LocalDateTime dateTime = date.atStartOfDay();
                Appointment appointment = new Appointment(customer, barber, dateTime);
                appointmentDao.save(appointment);
            }
        mainPane.getChildren().add(bookButton);
    
        Scene mainScene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(mainScene);
        }}catch (IOException e)
        {
        e.printStackTrace();
        }
    }

    
    private void setupBarberInterface(Barber barber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/barber_interface.fxml"));
            VBox mainPane = loader.load();
    
            Label welcomeLabel = (Label) loader.getNamespace().get("welcomeLabel");
            welcomeLabel.setText("Welcome, " + barber.getName());
    
            ListView<String> appointmentsList = (ListView<String>) loader.getNamespace().get("appointmentsList");

            if(appointmentsList!=null)
            {

            
    
            // Display appointments
            List<Appointment> appointments = appointmentDao.findAllByBarber(barber);
            for (Appointment appointment : appointments) {
                appointmentsList.getItems().add(appointment.toString());
            }}
    
            Scene mainScene = new Scene(mainPane, 800, 600);
            primaryStage.setScene(mainScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initializeDatabase() {
       
    
        // Create some customers
        Customer customer1 = new Customer("customer1", "password1", "John Doe");
        Customer customer2 = new Customer("customer2", "password2", "Jane Doe");
    
        // Save the customers to the database
        customerDao.save(customer1);
        customerDao.save(customer2);
    
        // Create some barbers
        Barber barber1 = new Barber("barber1", "password1", "Barber Bob");
        Barber barber2 = new Barber("barber2", "password2", "Barber Alice");
    
        // Save the barbers to the database
        barberDao.save(barber1);
        barberDao.save(barber2);
    }
    public static void main(String[] args) {
        launch(args);
    }
}