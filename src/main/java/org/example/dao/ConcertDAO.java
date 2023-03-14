package org.example.dao;

import org.example.SessionFactorySingleton;
import org.example.entities.Concert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ConcertDAO {

    private SessionFactory sessionFactory;

    public ConcertDAO() {
        sessionFactory = SessionFactorySingleton.getSessionFactory();
    }
    public void createConcert (Concert concert) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(concert);
        session.getTransaction().commit();
        session.close();
    }
    public Concert getConcertById (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Concert concert = session.get(Concert.class, id);
        session.getTransaction().commit();
        session.close();

        return concert;
    }
    public List<Concert> getConcertByArtist (String artist) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Concert WHERE artist = :artist");
        query.setParameter("artist", artist);
        List<Concert> concerts = query.list();
        session.getTransaction().commit();
        session.close();

        return concerts;
    }

    public List<Concert> getAllConcerts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Concert> concerts = session.createQuery("FROM Concert").getResultList();
        session.getTransaction().commit();
        session.close();
        return concerts;
    }

    public void updateConcert (Concert concert) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(concert);
        session.getTransaction().commit();
        session.close();
    }
    public void deleteConcert (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Concert concert = session.get(Concert.class, id);
        session.delete(concert);
        session.getTransaction().commit();
        session.close();
    }
}
