package LunchVote.web.Dish;

import LunchVote.AbstractRestTest;
import LunchVote.TestUtil;
import LunchVote.model.Dish;
import LunchVote.service.DishService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static LunchVote.RestrauntTestData.RESTRAUNT3;
import static LunchVote.TestUtil.userHttpBasic;
import static LunchVote.UserTestData.ADMIN1;
import static LunchVote.web.Json.JacksonObjectMapper.getMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static LunchVote.DishTestData.*;
import static LunchVote.DishTestData.DISH1;
import static LunchVote.DishTestData.DISH2;
import static LunchVote.UserTestData.USER1;
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
    public void getByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/date/2015-05-31")
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(DISH6, DISH5, DISH4))));
    }

    @Test
    public void getByDateRestrauntID() throws Exception {
        mockMvc.perform(get(REST_URL + "/date/2015-05-30/restraunt/100011")
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(Arrays.asList(DISH3, DISH2))));
    }

    @Test
    public void testCreate() throws Exception {
        Dish created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((created)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Dish returned = getMapper().readValue(TestUtil.getContent(action), Dish.class);
        created.setId(returned.getId());

        assertEquals(created.toString(), returned.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Dish returned = getMapper().readValue(TestUtil.getContent(action), Dish.class);

        assertEquals(updated.toString(), updated.toString());
    }

    @Test
    public void testUpdateVotedMenuWithVote() throws Exception {
        Dish updated = getUpdated();
        updated.setRestrauntId(RESTRAUNT3.getId());

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Dish returned = getMapper().readValue(TestUtil.getContent(action), Dish.class);

        assertEquals(updated.toString(), updated.toString());
    }

    @Test
    public void testUpdateVotedMenu() throws Exception {
        Dish updated = getUpdated();
        updated.setRestrauntId(RESTRAUNT3.getId());

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString((updated)))
                .with(userHttpBasic(ADMIN1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Dish returned = getMapper().readValue(TestUtil.getContent(action), Dish.class);

        assertEquals(updated.toString(), updated.toString());
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID)
                .with(userHttpBasic(ADMIN1)))
                .andExpect(status().isOk());
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2).toString(), service.getAll().toString());
    }
}