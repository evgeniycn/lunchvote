package LunchVote.repository.jpa;

import LunchVote.model.User;
import LunchVote.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by evgeniy on 10.05.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
