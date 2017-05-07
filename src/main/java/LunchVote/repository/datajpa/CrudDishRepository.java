package LunchVote.repository.datajpa;

import LunchVote.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    Dish getOne(int id);

    Dish save(Dish dish);

    int delete(int id);
}
