package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.IdCard;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IdCardDao {
    public static void createIdCard(IdCard idCard) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(idCard);
            transaction.commit();
        }
    }

    public static IdCard getIdCard(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.find(IdCard.class, id);
        }
    }

    public static List<IdCard> getIdCards(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT i FROM IdCard i", IdCard.class).getResultList();
        }
    }

    public static void updateIdCard(long id, IdCard idCard) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            IdCard idCard1 = session.find(IdCard.class, id);
            idCard1.setNumber(idCard1.getNumber());
            session.persist(idCard);
            transaction.commit();
        }
    }

    public static void deleteIdCard(long id) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            IdCard idCard = session.find(IdCard.class, id);
            session.remove(idCard);
            transaction.commit();
        }
    }
}
