package LunchVote.web.Restraunt;

import LunchVote.AbstractRestTest;
import LunchVote.model.Restraunt;
import LunchVote.service.RestrauntService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static LunchVote.RestrauntTestData.*;
import static LunchVote.RestrauntTestData.RESTRAUNT2;
import static LunchVote.RestrauntTestData.RESTRAUNT3;
import static java.time.LocalDate.of;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(get(REST_URL + RESTRAUNT1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(RESTRAUNT1)));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTRAUNT1.getId());
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2).toString(), service.getAll().toString());
    }

    @Test
    public void testSave() throws Exception {
        Restraunt created = getCreated();
        Restraunt restraunt = service.save(new Restraunt("New restraunt", of(2017, Month.JUNE, 1), Collections.emptyList()));
        created.setId(100017);
        assertEquals(created.toString(), restraunt.toString());
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2, RESTRAUNT1).toString(), service.getAll().toString());
    }

    @Test
    public void testGetAllWithTodayMenu() throws Exception {
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT2).toString(), service.getAllWithTodayMenu(of(2015, Month.MAY, 31)).toString());
    }

}