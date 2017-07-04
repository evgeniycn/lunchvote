package lunchvote.repository;

import lunchvote.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface DishRepository {

    Dish get(int id);

    Dish save(Dish dish);

    boolean delete(int id);

    List<Dish> getByDate(LocalDate date);

    List<Dish> getByDateRestrauntID(LocalDate date, int restrauntId);

    List<Dish> getAll();
}
