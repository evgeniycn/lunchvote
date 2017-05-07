package LunchVote.service;

import LunchVote.model.Dish;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface DishService {

    Dish get(int id);

    Dish save(Dish dish);

    void delete(int id);
}
