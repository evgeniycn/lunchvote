package lunchvote.web.Dish;

import lunchvote.model.Dish;
import lunchvote.model.Restraunt;
import lunchvote.service.DishService;
import lunchvote.to.DishTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static lunchvote.web.Dish.DishRestController.DISH_REST_URL;

/**
 * Created by evgeniy on 10.05.2017.
 */
@RestController
@RequestMapping(value = DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String DISH_REST_URL = "/rest/admin/dishes";

    private DishService service;

    @Autowired
    public DishRestController(DishService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping(params = "date")
    public List<Dish> getByDate(@RequestParam("date") LocalDate date) {
        return service.getByDate(date);
    }

    @PostMapping
    public DishTo save(@RequestBody DishTo dishTo) {
        return service.save(dishTo);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
