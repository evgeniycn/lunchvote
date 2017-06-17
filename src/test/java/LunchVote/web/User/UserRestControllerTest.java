package LunchVote.web.User;

import LunchVote.AbstractRestTest;
import LunchVote.RestrauntTestData;
import LunchVote.TestUtil;
import LunchVote.model.Restraunt;
import LunchVote.model.Role;
import LunchVote.model.User;
import LunchVote.service.RestrauntService;
import LunchVote.service.UserService;
import LunchVote.util.exception.NotFoundException;
import LunchVote.web.Restraunt.RestrauntRestController;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;

import static LunchVote.TestUtil.userHttpBasic;
import static LunchVote.UserTestData.*;


import static LunchVote.web.Json.JacksonObjectMapper.getMapper;
import static LunchVote.web.User.UserRestController.USER_REST_URL;
import static org.hamcrest.core.Is.isA;
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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private UserService service;

    @Autowired
    private RestrauntService restrauntService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER1.getId())
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(USER1)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk());
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, USER3, USER2).toString(), service.getAll().toString());
    }

    @Test
    public void testCreate() throws Exception {
        User created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((created)))
                .with(userHttpBasic(USER1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        User returned = getMapper().readValue(TestUtil.getContent(action), User.class);
        created.setId(returned.getId());

        assertEquals(created.toString(), returned.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(USER1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        User returned = getMapper().readValue(TestUtil.getContent(action), User.class);

        assertEquals(updated.toString(), updated.toString());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(ADMIN2, ADMIN1, USER3, USER2, USER1))));
    }

    @Test
    public void testSendVote() throws Exception {

        /*Restraunt created = RestrauntTestData.getCreated();
        created.setUpdatedDate(LocalDate.now());

        restrauntService.save(created);*/

        mockMvc.perform(get(REST_URL + "/vote/100011")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(String.valueOf(true)));
    }

    @Test
    public void testSendVoteForRestrauntWithoutTodayMenu() throws Exception {
        expectedException.expectCause(isA(NotFoundException.class));
        mockMvc.perform(get(REST_URL + "/vote/100013")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(USER1)));
    }

}