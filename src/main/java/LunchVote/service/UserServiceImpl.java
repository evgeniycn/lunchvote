package LunchVote.service;

import LunchVote.AuthorizedUser;
import LunchVote.model.User;
import LunchVote.model.Vote;
import LunchVote.repository.UserRepository;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.TimeLimitExceededException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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

    @Override
    public User get(int id) throws NotFoundException {
        return userRepository.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        userRepository.delete(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void sendVote(Vote vote) throws TimeLimitExceededException {
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            userRepository.sendVote(vote);
            User user = get(AuthorizedUser.id());
            user.setLastVoteDate(LocalDate.now());
            userRepository.save(user);
        } else throw new TimeLimitExceededException("Vote time is expired for today");
    }
}
