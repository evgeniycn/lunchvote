package LunchVote.util;


import LunchVote.model.User;
import LunchVote.to.UserTo;

public class UserUtil {
    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
