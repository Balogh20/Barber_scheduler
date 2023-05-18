package hu.unideb.inf;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private Customer customer;
    private Barber barber;
    private LocalDateTime appointmentTime;

    public Appointment(Customer customer, Barber barber, LocalDateTime appointmentTime) {
        this.customer = customer;
        this.barber = barber;
        this.appointmentTime = appointmentTime;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "Appointment at " + appointmentTime + " with " + barber.getName();
    }
}