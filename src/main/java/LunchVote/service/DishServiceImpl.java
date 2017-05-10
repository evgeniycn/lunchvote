package LunchVote.service;

import LunchVote.model.Dish;
import LunchVote.repository.DishRepository;
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

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    public Dish get(int id) throws NotFoundException {
        return dishRepository.get(id);
    }

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

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
}
