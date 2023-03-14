package org.example.dao;

import org.example.SessionFactorySingleton;
import org.example.entities.Address;
import org.example.entities.Concert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AddressDAO {

    private SessionFactory sessionFactory;

    public AddressDAO () {
        sessionFactory = SessionFactorySingleton.getSessionFactory();
    }
    public void createAddress (Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }
    public Address getAddressById (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Address address = session.get(Address.class, id);
        session.getTransaction().commit();
        session.close();

        return address;
    }
    public List<Address> getConcertByArtist (String streetName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Address WHERE streetName = :streetName");
        query.setParameter("streetName", streetName);
        List<Address> addresses = query.list();
        session.getTransaction().commit();
        session.close();

        return addresses;
    }

    public List<Address> getAllAddresses() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Address> addresses = session.createQuery("FROM Address").getResultList();
        session.getTransaction().commit();
        session.close();
        return addresses;
    }

    public void updateAddress (Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(address);
        session.getTransaction().commit();
        session.close();
    }
    public void deleteAddress (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Address address = session.get(Address.class, id);
        session.delete(address);
        session.getTransaction().commit();
        session.close();
    }
}
