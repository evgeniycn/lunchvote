package lunchvote.service;

import lunchvote.AbstractServiceTest;
import lunchvote.model.Role;
import lunchvote.model.User;
import lunchvote.util.PasswordUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static lunchvote.UserTestData.*;
import static org.junit.Assert.*;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class UserServiceServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        assertEquals(USER1, service.get(100000));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100001);
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, USER3, USER1), service.getAll());
    }

    @Test
    public void testSave() throws Exception {
        User user = getCreated();
        user.setId(100026);
        User created = service.save(new User(null, "New user", "new_user@gmail.com", PasswordUtil.encode("new_user_password"), Role.ROLE_USER));
        assertEquals(user, created);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();
        User user = service.get(100002);

        user.setName(updated.getName());
        user.setEmail(updated.getEmail());

        assertEquals(updated, user);
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(USERS, service.getAll());
    }

}