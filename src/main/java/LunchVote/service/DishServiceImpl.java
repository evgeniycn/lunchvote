package LunchVote.service;

import LunchVote.model.Dish;
import LunchVote.model.Vote;
import LunchVote.repository.DishRepository;
import LunchVote.repository.RestrauntRepository;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static LunchVote.util.ValidationUtil.checkEmptyArray;
import static LunchVote.util.ValidationUtil.checkNotFound;
import static LunchVote.util.ValidationUtil.checkNotFoundWithId;

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
    public Dish save(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        LocalDate dishDate = dish.getDate();

        List<Vote> allWithVotesByDate = restrauntRepository.getAllWithVotesByDate(dishDate);
        for (Vote vote : allWithVotesByDate) {
            if (vote.getRestrauntId().equals(dish.getRestrauntId())) {
                throw new UnsupportedOperationException("Someone already voted for this restraunt today, menu cannot be changed");
            }
        }

        LocalDate updatedDate = restrauntRepository.get(dish.getRestrauntId()).getUpdatedDate();
        if (updatedDate == null || updatedDate.isBefore(dishDate)) {
            dish = dishRepository.save(dish);
        }
        return dish;
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
    public List<Dish> getByDateRestrauntID(LocalDate date, int restrauntId) {
        Assert.notNull(date, "date must not be null");
        Assert.notNull(restrauntId, "restrauntId must not be null");
        return checkEmptyArray(dishRepository.getByDateRestrauntID(date, restrauntId));
    }

    @Override
    public List<Dish> getAll() {
        return checkEmptyArray(dishRepository.getAll());
    }
}
