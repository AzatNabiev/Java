package ru.itis.javalab.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.models.Message;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryJpaImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Message entity) {
            entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(Message entity) {
            Query query=entityManager.createQuery("update Message message set message.text=:text where message.id=:id")
                    .setParameter("text", entity.getText())
                    .setParameter("id",entity.getId());
            query.executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Message entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public List<Message> findAll() {
        return entityManager.createQuery("from Message",Message.class).getResultList();
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message message;
        try {
            message=(Message)entityManager.createQuery("from Message message where message.id=:id")
                    .setParameter("id",id).getSingleResult();
        } catch (NoResultException ex){
            message=null;
        }
        return Optional.ofNullable(message);
    }

    @Override
    public List<Message> findAll(int page, int size) {
        return entityManager.createQuery("from Message message order by message.id",
                Message.class).setFirstResult(page).setMaxResults(page*size).getResultList();
    }
}
