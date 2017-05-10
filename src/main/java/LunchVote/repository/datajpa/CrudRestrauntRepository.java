package LunchVote.repository.datajpa;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface CrudRestrauntRepository {

    List<Dish> getAllDishesByDate(LocalDate date);

    Restraunt getOne(int id);

    int delete(int id);

    Restraunt save(Restraunt restraunt);

    List<Restraunt> findAll();
}
