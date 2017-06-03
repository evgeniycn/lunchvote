package LunchVote.service;

import LunchVote.AbstractTest;
import LunchVote.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static LunchVote.UserTestData.*;
import static org.junit.Assert.*;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService service;

    @Test
    public void get() throws Exception {
        assertEquals(USER1.toString(), service.get(100000).toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100001);
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, USER3, USER1).toString(), service.getAll().toString());
    }

    @Test
    public void save() throws Exception {
        User user = getCreated();
        user.setId(100017);
        User created = service.save(new User(null, "New user", "new_user@gmail.com", "new_user_password"));
        assertEquals(user.toString(), created.toString());
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        User user = service.get(100002);

        user.setName(updated.getName());
        user.setEmail(updated.getEmail());
        user.setPassword(updated.getPassword());

        assertEquals(updated.toString(), user.toString());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(USERS.toString(), service.getAll().toString());
    }

}