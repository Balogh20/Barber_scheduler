package hu.unideb.inf.model;

import hu.unideb.inf.model.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersonDao<T extends Person> {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    private Class<T> type;

    public PersonDao(Class<T> type) {
        this.type = type;
    }

    public T findById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        T person = em.find(type, id);
        em.close();
        return person;
    }

    public List<T> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<T> people = em.createQuery("FROM " + type.getSimpleName(), type).getResultList();
        em.close();
        return people;
    }

    public void save(T person) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public T findByUsername(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        T person = em.createQuery("FROM " + type.getSimpleName() + " WHERE username = :username", type)
                .setParameter("username", username)
                .getResultList()
                .stream().findFirst().orElse(null);
        em.close();
        return person;
    }
}