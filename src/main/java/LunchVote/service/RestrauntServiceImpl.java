package lunchvote.service;

import lunchvote.model.Restraunt;
import lunchvote.model.Vote;
import lunchvote.repository.RestrauntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lunchvote.util.ValidationUtil.checkEmptyArray;
import static lunchvote.util.ValidationUtil.checkNotFoundWithId;

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

    @Override
    public Restraunt get(int id) {
        return checkNotFoundWithId(restrauntRepository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(restrauntRepository.delete(id), id);
    }

    @Override
    public Restraunt save(Restraunt restraunt) {
        Assert.notNull(restraunt, "restraunt must not be null");
        return restrauntRepository.save(restraunt);
    }

    @Override
    public List<Restraunt> getAll() {
        return checkEmptyArray(restrauntRepository.getAll());
    }

    @Override
    public List<Restraunt> getAllWithMenuByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return checkEmptyArray(restrauntRepository.getAllWithMenuByDate(date));
    }

    @Override
    public Map<Integer, Integer> getAllWithVotesByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        //restrauntId, numberOfVotes
        Map<Integer, Integer> votesResultByDate = new HashMap<>();
        List<Vote> votesByDate = checkEmptyArray(restrauntRepository.getAllWithVotesByDate(date));
        for (Vote vote : votesByDate) {
            votesResultByDate.merge(vote.getRestrauntId(), 1, (oldValue, one) -> oldValue + one);
        }
        return votesResultByDate;
    }
}
