package LunchVote.web.Dish;

import LunchVote.model.Dish;
import LunchVote.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by evgeniy on 10.05.2017.
 */
@RestController (value = "/rest/dishes")
public class DishRestController {

    private DishService service;

    @Autowired
    public DishRestController(DishService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Dish get (int id) {
        return service.get(id);
    }

    public List<Dish> getByDate (LocalDate date){
        return service.getByDate(date);
    }

    public List<Dish> getByDateRestrauntId (LocalDate date, int restrauntId) {
        return service.getByDateRestrauntID(date, restrauntId);
    }

    public Dish save (Dish dish) {
        return service.save(dish);
    }

    public void delete (int id) {
        service.delete(id);
    }
}
