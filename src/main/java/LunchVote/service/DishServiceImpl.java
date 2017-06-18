package LunchVote.service;

import LunchVote.model.Dish;
import LunchVote.model.Vote;
import LunchVote.repository.DishRepository;
import LunchVote.repository.RestrauntRepository;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public Dish get(int id) throws NotFoundException {
        return dishRepository.get(id);
    }

    @Override
    public Dish save(Dish dish) {
        LocalDate dishDate = dish.getDate();

        List<Vote> allWithVotesByDate = restrauntRepository.getAllWithVotesByDate(dishDate);
        for (Vote vote : allWithVotesByDate) {
            if (vote.getRestrauntId() != null) {
                throw new UnsupportedOperationException("Someone already voted for this restraunt today, menu cannot be changed");
            }
        }

        if (restrauntRepository.get(dish.getRestrauntId()).getUpdatedDate() == null || restrauntRepository.get(dish.getRestrauntId()).getUpdatedDate().isBefore(dishDate)) {
            dish = dishRepository.save(dish);
        }
        return dish;

    }

    @Override
    public void delete(int id) throws NotFoundException {
        dishRepository.delete(id);
    }

    @Override
    public List<Dish> getByDate(LocalDate date) {
        return dishRepository.getByDate(date);
    }

    @Override
    public List<Dish> getByDateRestrauntID(LocalDate date, int restrauntId) {
        return dishRepository.getByDateRestrauntID(date, restrauntId);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.getAll();
    }
}
