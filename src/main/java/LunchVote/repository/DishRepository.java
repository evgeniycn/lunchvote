package LunchVote.repository;

import LunchVote.model.Dish;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface DishRepository {

    Dish get(int id);

    Dish save(Dish dish);

    boolean delete (int id);

}
