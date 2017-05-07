package LunchVote.model;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public class Restraunt extends BaseEntity {

    private String name;

    private List<Dish> dishList;

    public String getName() {
        return name;
    }

    public List<Dish> getDishList() {
        return dishList;
    }
}
