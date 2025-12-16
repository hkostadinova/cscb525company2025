package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.EmployeeDto;
import org.example.dto.EmployeeIdCardDto;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao {
    public static void createEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        }
    }

    public static Employee getEmployee(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.find(Employee.class, id);
        }
    }

    public static List<Employee> getEmployees(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
        }
    }

    public static List<EmployeeDto> getEmployeesDto(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT new org.example.dto.EmployeeDto(e.id, e.name, e.position)" +
                    " FROM Employee e", EmployeeDto.class).getResultList();
        }
    }

    public static List<EmployeeIdCardDto> getEmployeesIdCardDto(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT new org.example.dto.EmployeeIdCardDto(e.id, e.name, i.id, i.number)" +
                    " FROM IdCard i" +
                    " join i.employee e", EmployeeIdCardDto.class).getResultList();
        }
    }

    public static void updateEmployee(long id, Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee1 = session.find(Employee.class, id);
            employee1.setName(employee.getName());
            employee1.setPosition(employee.getPosition());
            session.persist(employee1);
            transaction.commit();
        }
    }

    public static void deleteEmployee(long id) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            session.remove(employee);
            transaction.commit();
        }
    }
    
}
