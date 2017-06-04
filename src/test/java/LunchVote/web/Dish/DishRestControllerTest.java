package LunchVote.web.Dish;

import LunchVote.AbstractTest;
import LunchVote.model.Dish;
import LunchVote.service.DishService;
import LunchVote.web.Json.JacksonObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static LunchVote.DishTestData.*;
import static LunchVote.DishTestData.DISH1;
import static LunchVote.DishTestData.DISH2;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by evgeniy on 02.06.2017.
 */

@ContextConfiguration({
        "classpath:spring/spring-mvc.xml"
})
@WebAppConfiguration
@Transactional
public class DishRestControllerTest extends AbstractTest {

    private static final String REST_URL = DishRestController.REST_URL + "/";


    @Autowired
    private WebApplicationContext webApplicationContext;

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    private MockMvc mockMvc;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                //.apply(springSecurity())
                .build();
    }

    @Autowired
    private DishService service;

    ObjectMapper mapper = JacksonObjectMapper.getMapper();

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(DISH1)));
    }

    @Test
    public void save() throws Exception {
        Dish created = getCreated();
        Dish dish = service.save(new Dish(null, "Created dish", 1.00, LocalDate.of(2015, Month.JUNE, 1), 100010));
        created.setId(100017);
        assertEquals(created.toString(), dish.toString());
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        Dish dish = service.get(100005);

        dish.setName(updated.getName());
        dish.setDate(updated.getDate());
        dish.setPrice(updated.getPrice());
        dish.setRestrauntId(updated.getRestrauntId());

        assertEquals(updated.toString(), dish.toString());
    }



    @Test
    public void delete() throws Exception {
        service.delete(DISH1.getId());
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2).toString(), service.getAll().toString());
    }

    @Test
    public void getByDate() throws Exception {
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4).toString(), service.getByDate(LocalDate.of(2015, Month.MAY, 31)).toString());
    }

    @Test
    public void getByDateRestrauntID() throws Exception {
        assertEquals(Arrays.asList(DISH4, DISH5).toString(), service.getByDateRestrauntID(LocalDate.of(2015, Month.MAY, 31), 100012).toString());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2, DISH1).toString(), service.getAll().toString());
    }

}