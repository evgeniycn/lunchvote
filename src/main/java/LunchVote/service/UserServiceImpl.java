package LunchVote.service;

import LunchVote.model.User;
import LunchVote.repository.UserRepository;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(int id) throws NotFoundException {
        return userRepository.get(id);
    }

    public void delete(int id) throws NotFoundException {
        userRepository.delete(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
