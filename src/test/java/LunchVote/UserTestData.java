package LunchVote;

import LunchVote.model.Role;
import LunchVote.model.User;
import LunchVote.util.ModelMatcher;
import LunchVote.util.PasswordUtil;

import java.util.Arrays;

import java.util.List;
import java.util.Objects;

import static LunchVote.model.BaseEntity.START_SEQ;


/**
 * Created by evgeniy on 02.06.2017.
 */
public class UserTestData {

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class, (expected, actual) -> expected == actual ||
            (comparePassword(expected.getPassword(), actual.getPassword()))
                && Objects.equals(expected.getId(), actual.getId())
                && Objects.equals(expected.getEmail(), actual.getEmail())
                && Objects.equals(expected.getName(), actual.getName())
                && Objects.equals(expected.getRoles(), actual.getRoles()));

    public static final int USER1_ID = START_SEQ;

    public static final User USER1 = new User(USER1_ID, "User1", "user1@user.com", "password1", Role.ROLE_USER);
    public static final User USER2 = new User(USER1_ID + 1, "User2", "user2@user.com", "password2", Role.ROLE_USER);
    public static final User USER3 = new User(USER1_ID + 2, "User3", "user3@user.com", "password3", Role.ROLE_USER);

    public static final User ADMIN1 = new User(USER1_ID + 3, "Admin1", "admin1@gmail.com", "admin1", Role.ROLE_ADMIN);
    public static final User ADMIN2 = new User(USER1_ID + 4, "Admin2", "admin2@gmail.com", "admin2", Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_USER));



    public static final List<User> USERS = Arrays.asList(ADMIN2, ADMIN1, USER3, USER2, USER1);

    public static User getCreated() {
        return new User(null, "New user", "new_user@gmail.com", "new_user_password", Role.ROLE_USER);
    }

    public static User getUpdated() {
        return new User(USER1_ID + 2, "New user", "new_user@gmail.com", "new_user_password", Role.ROLE_USER);
    }

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            //LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }
}