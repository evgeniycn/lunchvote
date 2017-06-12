package LunchVote.service;

import LunchVote.AbstractServiceTest;
import LunchVote.model.Restraunt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static LunchVote.RestrauntTestData.*;
import static org.junit.Assert.*;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class RestrauntServiceServiceTest extends AbstractServiceTest {


    @Autowired
    private RestrauntService service;

    @Test
    public void get() throws Exception {
        assertEquals(RESTRAUNT1.toString(), service.get(100011).toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100011);
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2).toString(),service.getAll().toString());
    }

    @Test
    public void save() throws Exception {
        Restraunt created = getCreated();
        Restraunt restraunt = service.save(new Restraunt(null, "New restraunt", LocalDate.of(2017, Month.JUNE, 1), Collections.emptyList()));
        created.setId(100017);
        assertEquals(created.toString(), restraunt.toString());
    }

    @Test
    public void update() throws Exception {
        Restraunt updated = getUpdated();
        Restraunt restraunt = service.get(100011);

        restraunt.setName(updated.getName());
        restraunt.setDishList(updated.getDishList());
        restraunt.setUpdatedDate(updated.getUpdatedDate());

        assertEquals(updated.toString(), restraunt.toString());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(RESTRAUNTS.toString(), service.getAll().toString());
    }

    @Test
    public void getAllWithTodayMenu() throws Exception {
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT2).toString(), service.getAllWithTodayMenu(LocalDate.of(2015, Month.MAY, 31)).toString());
    }

}