package LunchVote.web.User;

import LunchVote.model.User;
import LunchVote.service.RestrauntService;
import LunchVote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static LunchVote.web.User.AdminRestController.ADMIN_USER_REST_URL;

/**
 * Created by evgeniy on 09.06.2017.
 */
@RestController
@RequestMapping(value = ADMIN_USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    static final String ADMIN_USER_REST_URL = "/rest/admin/users";

    private UserService userService;

    private RestrauntService restrauntService;

    @Autowired
    public AdminRestController(UserService userService, RestrauntService restrauntService) {
        this.userService = userService;
        this.restrauntService = restrauntService;
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        return userService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }



    /*@GetMapping(value = "/vote/{id}")
    public Vote sendVote(@PathVariable("id") int id) throws TimeLimitExceededException {
        return userService.sendVote(id);
    }

    @GetMapping(value = "/date/{date}")
    public List<Restraunt> getAllWithTodayMenu(@PathVariable("date") LocalDate date) {
        return restrauntService.getAllWithTodayMenu(date);
    }

    @GetMapping(value = "/votes/{date}")
    public Map<Integer, Integer> getAllWithVotesByDate(@PathVariable("date") LocalDate date) {
        return restrauntService.getAllWithVotesByDate(date);
    }*/

}