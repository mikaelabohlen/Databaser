package org.example.dao;

import org.example.SessionFactorySingleton;
import org.example.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerDAO {

    private SessionFactory sessionFactory;

    public CustomerDAO () {
        sessionFactory = SessionFactorySingleton.getSessionFactory();
    }
    public void createCustomer (Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }
    public Customer getCustomerById (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        session.close();

        return customer;
    }

    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Customer> customers = session.createQuery("FROM Customer").getResultList();
        session.getTransaction().commit();
        session.close();
        return customers;
    }

    public void updateCustomer (Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }
    public void deleteCustomer (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }
}
