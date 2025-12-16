package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDao {
    public static void createCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
    }

    public static Customer getCustomer(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.find(Customer.class, id);
        }
    }

    public static List<Customer> getCustomers(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        }
    }

    public static void updateCustomer(long id, Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer1 = session.find(Customer.class, id);
            customer1.setName(customer.getName());
            session.persist(customer1);
            transaction.commit();
        }
    }

    public static void deleteCustomer(long id) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, id);
            session.remove(customer);
            transaction.commit();
        }
    }
}
