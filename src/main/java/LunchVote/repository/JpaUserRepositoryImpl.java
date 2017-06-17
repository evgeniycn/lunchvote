package LunchVote.repository;

import LunchVote.AuthorizedUser;
import LunchVote.model.User;
import LunchVote.model.Vote;
import LunchVote.repository.UserRepository;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
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

    @Override
    @Transactional
    public Vote sendVote(Vote vote) {
        if (get(AuthorizedUser.id()).getLastVoteDate() == null || !get(AuthorizedUser.id()).getLastVoteDate().equals(LocalDate.now())) {
            em.persist(vote);
            return vote;
        }
        else {
            em.createNamedQuery(Vote.DELETE_BY_RESTRAUNT_ID)
                    .setParameter("userId", AuthorizedUser.id())
                    .setParameter("date", LocalDate.now())
                    .executeUpdate();
            return em.merge(vote);
        }
    }
}
