package LunchVote.repository.jpa;

import LunchVote.model.Restraunt;
import LunchVote.repository.RestrauntRepositoy;
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
public class JpaRestrauntRepositoryImpl implements RestrauntRepositoy {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Restraunt get(int id) {
        return em.find(Restraunt.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restraunt.DELETE_BY_ID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public Restraunt save(Restraunt restraunt) {
        if (restraunt.isNew()) {
            em.persist(restraunt);
            return restraunt;
        }
        else return em.merge(restraunt);
    }

    @Override
    public List<Restraunt> getAll() {
        return em.createNamedQuery(Restraunt.ALL, Restraunt.class)
                .getResultList();
    }

    @Override
    public List<Restraunt> getAllWithTodayMenu(LocalDate date) {
        return em.createNamedQuery(Restraunt.ALL_BY_TODAY, Restraunt.class)
                .setParameter("date", date)
                .getResultList();
    }
}
