package LunchVote.repository.jpa;

import LunchVote.model.Dish;
import LunchVote.repository.DishRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by evgeniy on 10.05.2017.
 */
@Repository
//@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    public Dish get(int id) {
        Dish dish = em.find(Dish.class, id);
        return dish;
    }

    public Dish save(Dish dish) {
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        }
        else return em.merge(dish);
    }

    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE_BY_ID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Dish> getByDate(LocalDate date) {
        return em.createNamedQuery(Dish.ALL_BY_DATE, Dish.class)
                .setParameter("date", date)
                .getResultList();
    }
}
