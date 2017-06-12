package LunchVote.web.User;

import LunchVote.AbstractRestTest;
import LunchVote.model.User;
import LunchVote.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static LunchVote.UserTestData.*;


import static LunchVote.web.User.UserRestController.USER_REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by evgeniy on 09.06.2017.
 */

public class UserRestControllerTest extends AbstractRestTest {
    private static final String REST_URL = USER_REST_URL + "/";


    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(USER1)));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER2.getId());
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, USER3, USER1).toString(), service.getAll().toString());
    }

    @Test
    public void testSave() throws Exception {
        User created = getCreated();
        User user = service.save(new User(null, "New user", "new_user@gmail.com", "new_user_password"));
        created.setId(100017);
        assertEquals(created.toString(), user.toString());
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, USER3, USER2, USER1).toString(), service.getAll().toString());
    }

}