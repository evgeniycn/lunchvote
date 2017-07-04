package lunchvote.web.User;

import lunchvote.model.User;
import lunchvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static lunchvote.web.User.AdminRestController.ADMIN_USER_REST_URL;

/**
 * Created by evgeniy on 09.06.2017.
 */
@RestController
@RequestMapping(value = ADMIN_USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    static final String ADMIN_USER_REST_URL = "/rest/admin/users";

    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
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


}