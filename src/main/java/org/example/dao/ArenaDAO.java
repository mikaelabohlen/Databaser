package org.example.dao;

import org.example.SessionFactorySingleton;
import org.example.entities.Arena;
import org.example.entities.Concert;
import org.example.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ArenaDAO {
    private SessionFactory sessionFactory;

    public ArenaDAO () {
        sessionFactory = SessionFactorySingleton.getSessionFactory();
    }
    public void createArena (Arena arena) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(arena);
        session.getTransaction().commit();
        session.close();
    }
    public Arena getArenaById (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Arena arena = session.get(Arena.class, id);
        session.getTransaction().commit();
        session.close();

        return arena;
    }
    public Arena getArenaByName (String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Arena WHERE name = :name");
        query.setParameter("name", name);
        Arena arena = (Arena) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return arena;
    }

    public List<Arena> getAllArenas() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Arena> arenas = session.createQuery("FROM Arena").getResultList();
        session.getTransaction().commit();
        session.close();
        return arenas;
    }

    public void updateArena (Arena arena) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(arena);
        session.getTransaction().commit();
        session.close();
    }
    public void deleteArena (int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Arena arena = session.get(Arena.class, id);
        session.delete(arena);
        session.getTransaction().commit();
        session.close();
    }
}
