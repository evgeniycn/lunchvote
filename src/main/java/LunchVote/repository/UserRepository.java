package LunchVote.repository;

import LunchVote.model.User;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface UserRepository {

    User get(int id);

    boolean delete(int id);

    User save(User user);

    List<User> getAll();
}
