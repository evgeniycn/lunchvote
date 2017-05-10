package LunchVote.repository.datajpa;

import LunchVote.model.Dish;
import LunchVote.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Repository
public class DishRepositoryImpl implements DishRepository {

    private final CrudDishRepository crudDishRepository;

    @Autowired
    public DishRepositoryImpl(CrudDishRepository crudDishRepository) {
        this.crudDishRepository = crudDishRepository;
    }

    public Dish get(int id) {
        return crudDishRepository.getOne(id);
    }

    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public List<Dish> getByDate(LocalDate date) {
        return null;
    }
}
