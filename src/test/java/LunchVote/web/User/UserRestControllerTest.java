package lunchvote.web.User;

import lunchvote.AbstractRestTest;
import lunchvote.TestUtil;
import lunchvote.model.*;
import lunchvote.service.RestrauntService;
import lunchvote.service.UserService;
import lunchvote.util.exception.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import javax.naming.TimeLimitExceededException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static lunchvote.RestrauntTestData.RESTRAUNT2;
import static lunchvote.RestrauntTestData.RESTRAUNT3;
import static lunchvote.TestUtil.userHttpBasic;
import static lunchvote.UserTestData.*;
import static lunchvote.RestrauntTestData.RESTRAUNT1_ID;


import static lunchvote.web.Json.JacksonObjectMapper.getMapper;
import static lunchvote.web.User.AdminRestController.ADMIN_USER_REST_URL;
import static lunchvote.web.User.UserRestController.USER_REST_URL;
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
        ResultActions resultActions = mockMvc.perform(get(REST_ADMIN_URL + USER1.getId())
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        assertEquals(USER1, mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), User.class));
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
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, user3, user2), service.getAll());
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

        assertEquals(created, returned);
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

        assertEquals(updated, returned);
    }

    @Test
    public void testGetAll() throws Exception {
        User user2 = USER2;
        user2.setLastVoteDate(LocalDate.now());

        ResultActions resultActions = mockMvc.perform(get(REST_ADMIN_URL)
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        assertEquals(Arrays.asList(ADMIN2, ADMIN1, USER3, user2, USER1), mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<List<User>>() {
        }));
    }

    @Test
    public void testSendVote() throws Exception {
        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT1_ID);
        vote.setUserId(USER1_ID);

        Field field = Vote.class.getDeclaredField("VOTE_BEFORE");
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        boolean isModifierAccessible = modifiersField.isAccessible();
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        field.set(null, LocalTime.now().plusHours(1));
        field.setAccessible(isAccessible);
        modifiersField.setAccessible(isModifierAccessible);

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote, returned);
    }

    @Test
    public void testSendVoteWithoutMenu() throws Exception {

        expectedException.expectCause(isA(NotFoundException.class));

        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT3.getId());
        vote.setUserId(USER3.getId());

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT3.getId())
                .with(userHttpBasic(USER3)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote, returned);
        assertEquals("{100011=1, 100012=1}", restrauntService.getAllWithVotesByDate(LocalDate.now()).toString());
    }

    @Test
    public void testUpdateVote() throws Exception {

        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT2.getId());
        vote.setUserId(USER2.getId());

        Field field = Vote.class.getDeclaredField("VOTE_BEFORE");
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        boolean isModifierAccessible = modifiersField.isAccessible();
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        field.set(null, LocalTime.now().plusHours(1));
        field.setAccessible(isAccessible);
        modifiersField.setAccessible(isModifierAccessible);

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT2.getId())
                .with(userHttpBasic(USER2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote, returned);
        assertEquals("{100011=1, 100012=1}", restrauntService.getAllWithVotesByDate(LocalDate.now()).toString());
    }

    @Test
    public void testUpdateVoteAfterAllowedTime() throws Exception {

        expectedException.expectCause(isA(TimeLimitExceededException.class));

        Vote vote = new Vote();
        vote.setDate(LocalDate.now());
        vote.setRestrauntId(RESTRAUNT2.getId());
        vote.setUserId(USER2.getId());

        Field field = Vote.class.getDeclaredField("VOTE_BEFORE");
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        boolean isModifierAccessible = modifiersField.isAccessible();
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        field.set(null, LocalTime.now().minusHours(1));
        field.setAccessible(isAccessible);
        modifiersField.setAccessible(isModifierAccessible);

        ResultActions action = mockMvc.perform(get(REST_URL + "/vote/" + RESTRAUNT2.getId())
                .with(userHttpBasic(USER2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote returned = getMapper().readValue(TestUtil.getContent(action), Vote.class);
        vote.setId(returned.getId());
        assertEquals(vote, returned);
        assertEquals("{100011=1, 100012=1}", restrauntService.getAllWithVotesByDate(LocalDate.now()).toString());
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