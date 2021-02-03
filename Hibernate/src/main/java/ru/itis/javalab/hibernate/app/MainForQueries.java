package ru.itis.javalab.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.javalab.hibernate.models.Client;
import ru.itis.javalab.hibernate.models.Order;
import ru.itis.javalab.hibernate.models.Type;

import java.util.HashSet;
import java.util.Set;

public class MainForQueries {
    public static void main(String[] args) {
        Configuration configuration= new Configuration().configure("Hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Query<Client> clientQuery = session.createQuery("from Client where id = 1",Client.class);
        Client client = clientQuery.getSingleResult();

        Type type = Type.builder()
                .name("Gadgets")
                .build();

        session.save(type);

        Query<Type> typeQuery = session.createQuery("from Type where id ="+type.getId().toString(), Type.class);
        Type typeSingleResult = typeQuery.getSingleResult();

        Order order=Order.builder()
                .name("Phone")
                .type(typeSingleResult)
                .build();
        Set<Order> orderSet=new HashSet<>();
        orderSet.add(order);
        client.setOrders(orderSet);
        session.save(order);
        session.save(client);
        session.getTransaction().commit();
        session.close();

        session=sessionFactory.openSession();
        session.beginTransaction();
        Query<Order> orderQuery =session.createQuery("from Order where id=3",Order.class);
        Order orderQuerySingleResult = orderQuery.getSingleResult();
        System.out.println(orderQuerySingleResult);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}
