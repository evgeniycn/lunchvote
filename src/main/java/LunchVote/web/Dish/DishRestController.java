package LunchVote.web.Dish;

import LunchVote.model.Dish;
import LunchVote.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static LunchVote.web.Dish.DishRestController.DISH_REST_URL;

/**
 * Created by evgeniy on 10.05.2017.
 */
@RestController
@RequestMapping(value = DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String DISH_REST_URL = "/rest/dishes";

    private DishService service;

    @Autowired
    public DishRestController(DishService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("id") int id) {
        Dish dish = service.get(id);
        return dish;
    }

    @GetMapping(value = "/date/{date}")
    public List<Dish> getByDate(@PathVariable("date") LocalDate date) {
        return service.getByDate(date);
    }

    @GetMapping(value = "/date/{date}/restraunt/{id}")
    public List<Dish> getByDateRestrauntId(@PathVariable("date") LocalDate date, @PathVariable("id") int restrauntId) {
        return service.getByDateRestrauntID(date, restrauntId);
    }

    @PostMapping
    public Dish save(@RequestBody Dish dish) {
        return service.save(dish);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
