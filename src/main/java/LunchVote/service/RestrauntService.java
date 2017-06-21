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

    Restraunt get(int id);

    void delete(int id);

    Restraunt save(Restraunt restraunt);

    List<Restraunt> getAll();

    List<Restraunt> getAllWithTodayMenu(LocalDate date);

    Map<Integer, Integer> getAllWithVotesByDate(LocalDate date);
}
