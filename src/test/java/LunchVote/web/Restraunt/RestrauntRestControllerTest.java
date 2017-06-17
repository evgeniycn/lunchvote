package LunchVote.web.Restraunt;

import LunchVote.AbstractRestTest;
import LunchVote.TestUtil;
import LunchVote.model.Restraunt;
import LunchVote.service.RestrauntService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static LunchVote.RestrauntTestData.*;
import static LunchVote.RestrauntTestData.RESTRAUNT2;
import static LunchVote.RestrauntTestData.RESTRAUNT3;
import static LunchVote.TestUtil.userHttpBasic;
import static LunchVote.UserTestData.USER1;
import static LunchVote.web.Json.JacksonObjectMapper.getMapper;
import static java.time.LocalDate.of;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by evgeniy on 12.06.2017.
 */

public class RestrauntRestControllerTest extends AbstractRestTest {
    private static final String REST_URL = RestrauntRestController.RESTRAUNT_REST_URL + "/";

    @Autowired
    private RestrauntService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTRAUNT1.getId())
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(RESTRAUNT1)));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2, RESTRAUNT1))));
    }

    @Test
    public void testGetAllWithTodayMenu() throws Exception {
        mockMvc.perform(get(REST_URL + "/date/2015-05-31")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT2))));
    }

    @Test
    public void testGetAllWithVotesByDate() throws Exception {

        ObjectNode response = mapper.createObjectNode();
        response.put("100011", 3);
        response.put("100012", 2);


        mockMvc.perform(get(REST_URL + "/votes/2015-05-31")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(response)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTRAUNT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk());
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2).toString(), service.getAll().toString());
    }

    @Test
    public void testCreate() throws Exception {
        Restraunt created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((created)))
                .with(userHttpBasic(USER1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Restraunt returned = getMapper().readValue(TestUtil.getContent(action), Restraunt.class);
        created.setId(returned.getId());

        assertEquals(created.toString(), returned.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        Restraunt updated = getUpdated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(USER1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Restraunt returned = getMapper().readValue(TestUtil.getContent(action), Restraunt.class);

        assertEquals(updated.toString(), updated.toString());
    }
}