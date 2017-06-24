package LunchVote.service;

import LunchVote.AbstractServiceTest;
import LunchVote.model.Restraunt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static LunchVote.RestrauntTestData.*;
import static org.junit.Assert.*;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class RestrauntServiceServiceTest extends AbstractServiceTest {


    @Autowired
    private RestrauntService service;

    @Test
    public void testGet() throws Exception {
        assertEquals(RESTRAUNT1, service.get(100011));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100011);
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2),service.getAll());
    }

    @Test
    public void testSave() throws Exception {
        Restraunt created = getCreated();
        Restraunt restraunt = service.save(new Restraunt(null, "New restraunt", LocalDate.of(2017, Month.JUNE, 1)));
        created.setId(100026);
        assertEquals(created, restraunt);
    }

    @Test
    public void testUpdate() throws Exception {
        Restraunt updated = getUpdated();
        Restraunt restraunt = service.get(100011);

        restraunt.setName(updated.getName());
        restraunt.setDishList(updated.getDishList());
        restraunt.setUpdatedDate(updated.getUpdatedDate());

        assertEquals(updated, restraunt);
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(RESTRAUNTS, service.getAll());
    }

    @Test
    public void testGetAllWithTodayMenu() throws Exception {
        assertEquals(Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4), service.getAllWithTodayMenu(LocalDate.of(2015, Month.MAY, 31)));
    }
}