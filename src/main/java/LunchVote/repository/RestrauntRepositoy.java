package LunchVote.repository;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface RestrauntRepositoy {

    List<Dish> getAllDishesByDate(LocalDate date);

    Restraunt get (int id);

    boolean delete (int id);

    Restraunt save (Restraunt restraunt);

    List<Restraunt> getAll();
}
