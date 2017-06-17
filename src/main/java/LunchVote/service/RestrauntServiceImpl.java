package LunchVote.service;

import LunchVote.model.Restraunt;
import LunchVote.model.Vote;
import LunchVote.repository.RestrauntRepository;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Service
public class RestrauntServiceImpl implements RestrauntService {

    private final RestrauntRepository restrauntRepository;

    @Autowired
    public RestrauntServiceImpl(RestrauntRepository restrauntRepository) {
        this.restrauntRepository = restrauntRepository;
    }


    /*public List<Dish> getAllDishesByDate(LocalDate date) {
        return restrauntRepository.getAllDishesByDate(date);
    }*/

    @Override
    public Restraunt get(int id) throws NotFoundException {
        return restrauntRepository.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        restrauntRepository.delete(id);
    }

    @Override
    public Restraunt save(Restraunt restraunt) {
        return restrauntRepository.save(restraunt);
    }

    @Override
    public List<Restraunt> getAll() {
        return restrauntRepository.getAll();
    }

    @Override
    public List<Restraunt> getAllWithTodayMenu(LocalDate date) {
        return restrauntRepository.getAllWithTodayMenu(date);
    }

    /*@Override
    public Restraunt getVotesByDateAndRestrauntId(LocalDate date, int restrauntId) {
        return restrauntRepository.getVotesByDateAndRestrauntId(date, restrauntId);
    }*/

    @Override
    public Map<Integer, Integer> getAllWithVotesByDate(LocalDate date) {

        //restrauntId, numberOfVotes
        Map<Integer, Integer> votesResultByDate = new HashMap<>();
        List<Vote> votesByDate = restrauntRepository.getAllWithVotesByDate(date);
        for (Vote vote : votesByDate) {
            votesResultByDate.merge(vote.getRestrauntId(), 1, (oldValue, one) -> oldValue + one);
        }
        return votesResultByDate;
    }
}
