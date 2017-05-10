package LunchVote.web.Dish;

import LunchVote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by evgeniy on 10.05.2017.
 */
public class UserRestController {

    private UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }


}
