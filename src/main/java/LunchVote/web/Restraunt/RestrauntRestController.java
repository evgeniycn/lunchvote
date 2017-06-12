package LunchVote.web.Restraunt;

import LunchVote.model.Restraunt;
import LunchVote.service.RestrauntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static LunchVote.web.Restraunt.RestrauntRestController.RESTRAUNT_REST_URL;

/**
 * Created by Evgeniy on 10.05.2017.
 */
@RestController
@RequestMapping(value = RESTRAUNT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestrauntRestController {

    static final String RESTRAUNT_REST_URL = "/rest/restraunts";

    private RestrauntService service;

    @Autowired
    public RestrauntRestController(RestrauntService service) {
        this.service = service;
    }

    @GetMapping (value = "/{id}")
    public Restraunt get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping
    public void delete (@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping
    public Restraunt save(@RequestBody Restraunt restraunt) {
        return service.save(restraunt);
    }

    @GetMapping
    public List<Restraunt> getAll() {
        return service.getAll();
    }

    @GetMapping (value = "/date/{date}")
    public List<Restraunt> getAllWithTodayMenu(@PathVariable("date") LocalDate date) {
        return service.getAllWithTodayMenu(date);
    }

}
