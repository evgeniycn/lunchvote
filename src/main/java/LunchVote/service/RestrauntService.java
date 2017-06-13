package LunchVote.service;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;
import LunchVote.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface RestrauntService {

    //List<Dish> getAllDishesByDate(LocalDate date);

    Restraunt get(int id);

    void delete(int id);

    Restraunt save(Restraunt restraunt);

    List<Restraunt> getAll();

    List<Restraunt> getAllWithTodayMenu(LocalDate date);

    Restraunt getVotesByDateAndRestrauntId (LocalDate date, int restrauntId);

    Map<Integer, Integer> getAllWithVotesByDate(LocalDate date);
}
