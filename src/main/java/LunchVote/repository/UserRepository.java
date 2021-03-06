package lunchvote.repository;

import lunchvote.model.User;
import lunchvote.model.Vote;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface UserRepository {

    User get(int id);

    User getByEmail(String email);

    boolean delete(int id);

    User save(User user);

    List<User> getAll();

    Vote sendVote(Vote vote);
}
