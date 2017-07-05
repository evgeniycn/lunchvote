package lunchvote.service;

import lunchvote.model.Dish;
import lunchvote.model.Vote;
import lunchvote.repository.DishRepository;
import lunchvote.repository.RestrauntRepository;
import lunchvote.to.DishTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static lunchvote.util.ValidationUtil.checkEmptyArray;
import static lunchvote.util.ValidationUtil.checkNotFound;
import static lunchvote.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final RestrauntRepository restrauntRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, RestrauntRepository restrauntRepository) {
        this.dishRepository = dishRepository;
        this.restrauntRepository = restrauntRepository;
    }

    @Override
    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    @Override
    public DishTo save(DishTo dishTo) {
        Assert.notNull(dishTo, "dish must not be null");
        LocalDate dishDate = dishTo.getDate();

        List<Vote> allWithVotesByDate = restrauntRepository.getAllWithVotesByDate(dishDate);
        for (Vote vote : allWithVotesByDate) {
            if (vote.getRestrauntId().equals(dishTo.getRestrauntId())) {
                throw new UnsupportedOperationException("Someone already voted for this restraunt today, menu cannot be changed");
            }
        }

        LocalDate updatedDate = restrauntRepository.get(dishTo.getRestrauntId()).getUpdatedDate();
        if (updatedDate == null || updatedDate.isBefore(dishDate)) {
            dishRepository.save(new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice(), dishTo.getDate(), restrauntRepository.get(dishTo.getRestrauntId())));
        }
        return dishTo;
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    @Override
    public List<Dish> getByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return checkEmptyArray(dishRepository.getByDate(date));
    }

    @Override
    public List<Dish> getByDateRestrauntId(LocalDate date, int restrauntId) {
        Assert.notNull(date, "date must not be null");
        Assert.notNull(restrauntId, "restrauntId must not be null");
        return checkEmptyArray(dishRepository.getByDateRestrauntId(date, restrauntId));
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.getAll();
    }
}
