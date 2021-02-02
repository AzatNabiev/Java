package ru.itis.javalab.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.javalab.hibernate.models.Client;
import ru.itis.javalab.hibernate.models.Order;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("Hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Client client =Client.builder()
                .name("Name")
                .build();
        Order order = Order.builder()
                .name("PC")
                .build();
        session.persist(client);
        session.persist(order);
        session.getTransaction().commit();
        session.close();
        System.out.println("Name was saved in db");

        client.setName("Test");
        System.out.println("Test was not saved in db");
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(client);
        session.getTransaction().commit();
        session.close();
        System.out.println("Test was saved in db");
        sessionFactory.close();
    }
}
