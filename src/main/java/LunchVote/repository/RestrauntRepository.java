package lunchvote.repository;

import lunchvote.model.Restraunt;
import lunchvote.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface RestrauntRepository {

    Restraunt get(int id);

    boolean delete(int id);

    Restraunt save(Restraunt restraunt);

    List<Restraunt> getAll();

    List<Restraunt> getAllWithMenuByDate(LocalDate date);

    Restraunt getVotesByDateAndRestrauntId(LocalDate date, int restrauntId);

    List<Vote> getVotesByDateRestrauntId(LocalDate date, int restrauntId);

    Restraunt getByDateRestrauntId(LocalDate date, int restrauntId);
}
