package LunchVote.repository.jpa;

import LunchVote.model.User;
import LunchVote.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by evgeniy on 10.05.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE_BY_ID)
                .setParameter("id", id)
                .executeUpdate()!= 0;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        }
        else return em.merge(user);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL, User.class)
                .getResultList();
    }
}
