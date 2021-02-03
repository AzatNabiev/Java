package ru.itis.javalab.hibernate.app;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.javalab.hibernate.models.Client;


import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MainForCriteriaQuery {
    public static void main(String[] args) {
        //config
        Configuration configuration= new Configuration().configure("Hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Client> clientQuery = builder.createQuery(Client.class);
        Root<Client> clientRoot = clientQuery.from(Client.class);
        System.out.println("Работа с таблицей clients: ");

        //SQL: SELECT * FROM clients;
        clientQuery.select(clientRoot);
        Query<Client> query = session.createQuery(clientQuery);
        List<Client> clients = query.getResultList();
        System.out.println("Клиенты "+clients);

        //SQL: SELECT * FROM clients WHERE id=1;
        clientQuery.select(clientRoot).where(builder.equal(clientRoot.get("id"),1L));
        query = session.createQuery(clientQuery);
        Client client = query.getSingleResult();
        System.out.println("Клиент с id=1 "+ client);
        session.getTransaction().commit();
        session.close();


        session =sessionFactory.openSession();
        session.beginTransaction();

        //SQL: SELECT name FROM CLIENTS;
        CriteriaQuery<Client> cq= builder.createQuery(Client.class);
        Root<Client> root1=cq.from(Client.class);
        cq.select(root1.get("name"));
        List<Client> list=session.createQuery(cq).getResultList();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}
