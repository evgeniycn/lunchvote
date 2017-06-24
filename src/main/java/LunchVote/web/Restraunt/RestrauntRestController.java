package LunchVote.web.Restraunt;

import LunchVote.model.Restraunt;
import LunchVote.service.RestrauntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static LunchVote.web.Restraunt.RestrauntRestController.RESTRAUNT_REST_URL;

/**
 * Created by Evgeniy on 10.05.2017.
 */
@RestController
@RequestMapping(value = RESTRAUNT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestrauntRestController {

    static final String RESTRAUNT_REST_URL = "/rest/admin/restraunts";

    private RestrauntService service;

    @Autowired
    public RestrauntRestController(RestrauntService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Restraunt get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
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

    @GetMapping(value = "/date/{date}")
    public List<Restraunt> getAllWithMenuByDate(@PathVariable("date") LocalDate date) {
        return service.getAllWithTodayMenu(date);
    }

    @GetMapping(value = "/votes/{date}")
    public Map<Integer, Integer> getAllVotesByDate(@PathVariable("date") LocalDate date) {
        return service.getAllWithVotesByDate(date);
    }

}
