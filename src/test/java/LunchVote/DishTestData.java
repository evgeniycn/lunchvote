package lunchvote;


import lunchvote.model.Dish;
import lunchvote.to.DishTo;

import java.time.LocalDate;
import java.time.Month;

import static lunchvote.model.BaseEntity.START_SEQ;
import static java.time.LocalDate.of;
import static lunchvote.RestrauntTestData.RESTRAUNT1;
import static lunchvote.RestrauntTestData.RESTRAUNT2;
import static lunchvote.RestrauntTestData.RESTRAUNT3;
import static lunchvote.RestrauntTestData.RESTRAUNT4;

/**
 * Created by Evgeniy on 10.05.2017.
 */
public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 5;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Borsh", 100.00, LocalDate.now(), RESTRAUNT1);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Salat", 50.00, of(2015, Month.MAY, 30), RESTRAUNT1);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Macaroni", 80.00, of(2015, Month.MAY, 30), RESTRAUNT1);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Soup", 50.00, of(2015, Month.MAY, 31), RESTRAUNT2);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Sandwich", 55.50, of(2015, Month.MAY, 31), RESTRAUNT2);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Jarkoe", 100.00, of(2015, Month.MAY, 31), RESTRAUNT3);

    public static Dish getCreated() {
        return new Dish(null, "Created dish", 1.00, of(2015, Month.JUNE, 1), RESTRAUNT1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated dish", 20.00, DISH1.getDate(), RESTRAUNT4);
    }

    public static DishTo toGetCreated() {
        return new DishTo(null, "Created dish", 1.00, of(2015, Month.JUNE, 1), 100011);
    }

    public static DishTo toGetUpdated() {
        return new DishTo(DISH1_ID, "Updated dish", 20.00, DISH1.getDate(), 100014);
    }

}
