package LunchVote.web.User;

import LunchVote.AbstractRestTest;
import LunchVote.DishTestData;
import LunchVote.RestrauntTestData;
import LunchVote.TestUtil;
import LunchVote.model.*;
import LunchVote.repository.DishRepository;
import LunchVote.service.DishService;
import LunchVote.service.RestrauntService;
import LunchVote.service.UserService;
import LunchVote.util.exception.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;

import static LunchVote.RestrauntTestData.RESTRAUNT1;
import static LunchVote.RestrauntTestData.RESTRAUNT2;
import static LunchVote.TestUtil.userHttpBasic;
import static LunchVote.UserTestData.*;
import static LunchVote.RestrauntTestData.RESTRAUNT1_ID;


import static LunchVote.web.Json.JacksonObjectMapper.getMapper;
import static LunchVote.web.User.AdminRestController.ADMIN_USER_REST_URL;
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

    private static final String REST_ADMIN_URL = ADMIN_USER_REST_URL + "/";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private UserService service;

    @Autowired
    private RestrauntService restrauntService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_ADMIN_URL + USER1.getId())
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(USER1)));
    }

    @Test
    public void testDelete() throws Exception {
        User user3 = USER3;
        user3.setLastVoteDate(LocalDate.now());
        User user2 = USER2;
        user2.setLastVoteDate(LocalDate.now());
        mockMvc.perform(delete(REST_ADMIN_URL + USER1_ID)
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk());
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, user3, user2).toString(), service.getAll().toString());
    }

    @Test
    public void testCreate() throws Exception {
        User created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_ADMIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((created)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        User returned = getMapper().readValue(TestUtil.getContent(action), User.class);
        created.setId(returned.getId());

        assertEquals(created.toString(), returned.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();

        ResultActions action = mockMvc.perform(post(REST_ADMIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        User returned = getMapper().readValue(TestUtil.getContent(action), User.class);

        assertEquals(updated.toString(), updated.toString());
    }

    @Test
    public void testGetAll() throws Exception {
        User user2 = USER2;
        user2.setLastVoteDate(LocalDate.now());

        mockMvc.perform(get(REST_ADMIN_URL)
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(ADMIN2, ADMIN1, USER3, user2, USER1))));
    }

    @Test
    public void testSendVote() throws Exception {
        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT1_ID);
        vote.setUserId(USER1_ID);

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote.toString(), returned.toString());
    }

    @Test
    public void testSendVoteWithoutMenu() throws Exception {

        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT2.getId());
        vote.setUserId(USER3.getId());

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT2.getId())
                .with(userHttpBasic(USER3)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote.toString(), returned.toString());
        restrauntService.getAllWithVotesByDate(LocalDate.now());
    }

    @Test
    public void testUpdateVote() throws Exception {

        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT2.getId());
        vote.setUserId(USER2.getId());

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT2.getId())
                .with(userHttpBasic(USER2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote.toString(), returned.toString());
        assertEquals("{100011=1, 100012=1}",restrauntService.getAllWithVotesByDate(LocalDate.now()).toString());
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