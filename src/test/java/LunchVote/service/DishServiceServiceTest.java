package LunchVote.service;

import LunchVote.AbstractServiceTest;
import LunchVote.model.Dish;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static LunchVote.DishTestData.*;
import static org.junit.Assert.*;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class DishServiceServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void testGet() throws Exception {
        assertEquals(DISH1,service.get(100005));
    }

    @Test
    public void testSave() throws Exception {
        Dish created = getCreated();
        Dish dish = service.save(new Dish(null, "Created dish", 1.00, LocalDate.of(2015, Month.JUNE, 1), 100011));
        created.setId(100026);
        assertEquals(created, dish);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        Dish dish = service.get(100005);

        dish.setName(updated.getName());
        dish.setDate(updated.getDate());
        dish.setPrice(updated.getPrice());
        dish.setRestrauntId(updated.getRestrauntId());

        assertEquals(updated, dish);
    }



    @Test
    public void testDelete() throws Exception {
        service.delete(DISH1.getId());
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2), service.getAll());
    }

    @Test
    public void testGetByDate() throws Exception {
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4), service.getByDate(LocalDate.of(2015, Month.MAY, 31)));
    }

    @Test
    public void testGetByDateRestrauntID() throws Exception {
        assertEquals(Arrays.asList(DISH5, DISH4), service.getByDateRestrauntID(LocalDate.of(2015, Month.MAY, 31), 100012));
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2, DISH1), service.getAll());
    }

}