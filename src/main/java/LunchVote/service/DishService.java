package LunchVote.service;

import LunchVote.model.Dish;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface DishService {

    Dish get(int id);

    Dish save(Dish dish);

    void delete(int id);

    List<Dish> getByDate (LocalDate date);

    List<Dish> getByDateRestrauntID (LocalDate date, int restrauntId);

}
