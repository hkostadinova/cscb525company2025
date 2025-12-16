package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDao {
    public static void createPerson(Person person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(person);
            transaction.commit();
        }
    }

    public static Person getPerson(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.find(Person.class, id);
        }
    }

    public static List<Person> getPeople(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        }
    }

    public static void updatePerson(long id, Person person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Person person1 = session.find(Person.class, id);
            person1.setName(person.getName());
            session.persist(person1);
            transaction.commit();
        }
    }

    public static void deletePerson(long id) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Person person = session.find(Person.class, id);
            session.remove(person);
            transaction.commit();
        }
    }
}
