package ru.itis.javalab.hibernate.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.javalab.hibernate.models.Mentor;
import ru.itis.javalab.hibernate.models.Subject;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("Hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Query<Mentor> userQuery = session.createQuery("from Mentor where id=1",Mentor.class);
        Mentor mentor=userQuery.getSingleResult();

        Subject subject=Subject.builder()
                .subjectName("Math")
                .mentor(mentor)
                .build();
        session.save(subject);
//        Student student = Student.builder()
//                .name("AZAT")
//                .build();
//        session.persist(student);
        session.getTransaction().commit();
        session.close();


        session = sessionFactory.openSession();
        Query<Subject>subjectQuery =session.createQuery("from Subject where id=1",Subject.class);
        subject = subjectQuery.getSingleResult();
        System.out.println(subject);
        session.close();
        sessionFactory.close();
    }
}
