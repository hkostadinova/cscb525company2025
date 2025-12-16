package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.CompanyDto;
import org.example.entity.Company;
import org.example.entity.Company_;
import org.example.entity.Employee;
import org.example.validator.InvalidNames;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CompanyDao {
    public static void createCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        }
    }

    public static void createCompanyEmployee(Company company, Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company.setEmployees(Set.of(employee));
            session.persist(company);
            transaction.commit();
        }
    }

    public static Company getCompany(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.find(Company.class, id);
        }
    }

    public static List<Company> getCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT c FROM Company c", Company.class).getResultList();
        }
    }

    public static void updateCompany(long id, Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company1 = session.find(Company.class, id);
            company1.setName(company.getName());
            session.persist(company1);
            transaction.commit();
        }
    }

    public static void deleteCompany(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.find(Company.class, id);
            session.remove(company);
            transaction.commit();
        }
    }

    public static List<Company> companiesFindByInitialCapitalBetween(double bottom, double top) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);

            cr.select(root).where(cb.between(root.get("initialCapital"), bottom, top));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static List<CompanyDto> companiesDtoFindByInitialCapitalBetween(double bottom, double top) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CompanyDto> cr = cb.createQuery(CompanyDto.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(
                    cb.construct(
                            CompanyDto.class,
                            root.get(Company_.name),
                            root.get(Company_.foundationDate),
                            root.get(Company_.initialCapital)
                    )
            ).where(cb.between(root.get("initialCapital"), bottom, top));

            Query<CompanyDto> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static List<Company> findByNameStartingWithAndInitialCapitalGreaterThan(String name, double initialCapital) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);

            Predicate greaterThanInitialCapital = cb.greaterThan(root.get("initialCapital"), initialCapital);
            Predicate nameStartingWith = cb.like(root.get("name"), name + "%");

            cr.select(root).where(cb.and(nameStartingWith, greaterThanInitialCapital));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static BigDecimal sumInitialCapital() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> cr = cb.createQuery(BigDecimal.class);
            Root<Company> root = cr.from(Company.class);

            cr.select(cb.sum(root.get("initialCapital")));

            Query<BigDecimal> query = session.createQuery(cr);
            return query.getSingleResult();
        }
    }

    public static List<Company> companiesWithNameEqualTo(String name) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("name"), name));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static List<Company> companiesWithNameNotEqualTo(String name) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.notEqual(root.get("name"), name));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static List<Company> companiesWithNameLike(String name) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.like(root.get("name"), "%" + name + "%"));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static List<Company> companiesWithNameNotLike(String name) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.notLike(root.get("name"), "%" + name + "%"));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    public static List<Company> companiesWithFoundationDateGreaterThan(LocalDate localDate) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.greaterThan(root.get("foundationDate"), localDate));

            Query<Company> query = session.createQuery(cr);
            return query.getResultList();
        }
    }
}
