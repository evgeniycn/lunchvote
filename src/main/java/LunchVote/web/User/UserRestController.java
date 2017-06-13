package LunchVote.web.User;

import LunchVote.model.User;
import LunchVote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static LunchVote.web.User.UserRestController.USER_REST_URL;

/**
 * Created by evgeniy on 09.06.2017.
 */
@RestController
@RequestMapping(value = USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String USER_REST_URL = "/rest/users";

    private UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

}