package LunchVote.service;

import LunchVote.model.User;
import LunchVote.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface UserService {

    User get(int id);

    void delete(int id) throws NotFoundException;

    User save (User user);

    List<User> getAll();
}
