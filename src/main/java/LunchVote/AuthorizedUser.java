package LunchVote;

import LunchVote.model.BaseEntity;
import LunchVote.model.User;
import LunchVote.to.UserTo;
import LunchVote.util.UserUtil;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public static int id = BaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
