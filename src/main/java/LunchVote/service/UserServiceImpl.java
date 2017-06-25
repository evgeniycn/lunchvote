package LunchVote.service;

import LunchVote.AuthorizedUser;
import LunchVote.model.Restraunt;
import LunchVote.model.User;
import LunchVote.model.Vote;
import LunchVote.repository.RestrauntRepository;
import LunchVote.repository.UserRepository;
import LunchVote.util.PasswordUtil;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.naming.TimeLimitExceededException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static LunchVote.util.ValidationUtil.checkEmptyArray;
import static LunchVote.util.ValidationUtil.checkNotFoundWithId;
import static LunchVote.model.Vote.VOTE_BEFORE;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RestrauntRepository restrauntRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestrauntRepository restrauntRepository) {
        this.userRepository = userRepository;
        this.restrauntRepository = restrauntRepository;
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return userRepository.getByEmail(email);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not me null");
        if (!PasswordUtil.isEncoded(user.getPassword())) user.setPassword(PasswordUtil.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return checkEmptyArray(userRepository.getAll());
    }


    @Override
    public Vote sendVote(int restrauntId) throws TimeLimitExceededException {
        Vote vote = new Vote();
        vote.setUserId(AuthorizedUser.id());
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(restrauntId);

        List<Restraunt> allWithTodayMenu = checkEmptyArray(restrauntRepository.getAllWithTodayMenu(LocalDate.now()));

        boolean hasTodayMenu = false;
        for (Restraunt restraunt : allWithTodayMenu) {
            if (restraunt.getId().equals(restrauntId)) {
                hasTodayMenu = true;
                break;
            }
        }

        if (!hasTodayMenu)
            throw new NotFoundException("No menu from this restraunt available for today");

        if (LocalTime.now().isBefore(VOTE_BEFORE)) {
            userRepository.sendVote(vote);
            User user = get(AuthorizedUser.id());
            user.setLastVoteDate(LocalDate.now());
            userRepository.save(user);
        } else
            throw new TimeLimitExceededException("Vote time is expired for today");
        return checkNotFoundWithId(vote, restrauntId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
