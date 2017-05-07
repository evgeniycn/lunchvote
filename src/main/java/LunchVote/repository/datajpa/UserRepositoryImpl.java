package LunchVote.repository.datajpa;

import LunchVote.model.User;
import LunchVote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public UserRepositoryImpl(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    public User get(int id) {
        return crudUserRepository.getOne(id);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll();
    }
}
