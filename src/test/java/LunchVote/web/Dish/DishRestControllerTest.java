package lunchvote.web.Dish;

import lunchvote.AbstractRestTest;
import lunchvote.TestUtil;
import lunchvote.model.Dish;
import lunchvote.service.DishService;
import lunchvote.to.DishTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static lunchvote.RestrauntTestData.RESTRAUNT3;
import static lunchvote.TestUtil.userHttpBasic;
import static lunchvote.UserTestData.ADMIN1;
import static lunchvote.web.Json.JacksonObjectMapper.getMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static lunchvote.DishTestData.*;
import static lunchvote.DishTestData.DISH1;
import static lunchvote.DishTestData.DISH2;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by evgeniy on 02.06.2017.
 */

public class DishRestControllerTest extends AbstractRestTest {
    private static final String REST_URL = DishRestController.DISH_REST_URL + "/";


    @Autowired
    private DishService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID)
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(DISH1)));
    }

    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "?date=2015-05-31")
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(DISH6, DISH5, DISH4))));
    }

    @Test
    public void testGetByDateRestrauntId() throws Exception {
        mockMvc.perform(get(REST_URL + "?date=2015-05-31&restrauntId=100012")
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(DISH5, DISH4))));
    }

    @Test
    public void testCreate() throws Exception {
        DishTo created = toGetCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((created)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        DishTo returned = getMapper().readValue(TestUtil.getContent(action), DishTo.class);
        created.setId(returned.getId());

        assertEquals(created, returned);
    }

    @Test
    public void testUpdate() throws Exception {
        DishTo updated = toGetUpdated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        DishTo returned = getMapper().readValue(TestUtil.getContent(action), DishTo.class);

        assertEquals(updated, returned);
    }

    @Test
    public void testUpdateVotedMenu() throws Exception {
        DishTo updated = toGetUpdated();
        //updated.getRestraunt().setId(RESTRAUNT3.getId());

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        DishTo returned = getMapper().readValue(TestUtil.getContent(action), DishTo.class);

        assertEquals(updated, returned);
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID)
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk());
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2), service.getAll());
    }
}