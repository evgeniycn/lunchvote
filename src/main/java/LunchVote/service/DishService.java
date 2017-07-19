package lunchvote.service;

import lunchvote.model.Dish;
import lunchvote.model.Restraunt;
import lunchvote.to.DishTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface DishService {

    Dish get(int id);

    DishTo save(DishTo dishTo);

    void delete(int id);

    List<Dish> getByDate(LocalDate date);

    List<Dish> getAll();

}
