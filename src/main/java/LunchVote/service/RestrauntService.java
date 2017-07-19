package lunchvote.service;

import lunchvote.model.Restraunt;
import lunchvote.model.Vote;

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

    List<Restraunt> getAllWithMenuByDate(LocalDate date);

    List<Vote> getVotesByDateRestrauntId(LocalDate date, int restrauntId);

    Restraunt getByDateRestrauntId(LocalDate date, int restrauntId);
}
