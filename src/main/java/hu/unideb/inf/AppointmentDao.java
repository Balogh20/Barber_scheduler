package hu.unideb.inf;


import hu.unideb.inf.model.Barber;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AppointmentDao {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    public Appointment findById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Appointment appointment = em.find(Appointment.class, id);
        em.close();
        return appointment;
    }

    public List<Appointment> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Appointment> appointments = em.createQuery("FROM Appointment", Appointment.class).getResultList();
        em.close();
        return appointments;
    }

    public void save(Appointment appointment) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
        em.close();
    }

    public List<Appointment> findAllByBarber(Barber barber) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Appointment> appointments = em.createQuery("FROM Appointment WHERE barber = :barber", Appointment.class)
                .setParameter("barber", barber)
                .getResultList();
        em.close();
        return appointments;
    }
}