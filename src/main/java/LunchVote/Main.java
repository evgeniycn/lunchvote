package LunchVote;

import LunchVote.model.Dish;
import LunchVote.web.Dish.DishRestController;
import org.springframework.context.support.GenericXmlApplicationContext;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Created by evgeniy on 10.05.2017.
 */
public class Main {

    public static void main(String[] args) {

        try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.load("spring/spring-db.xml", "spring/spring-app.xml");
            ctx.refresh();

            DishRestController controller = ctx.getBean(DishRestController.class);
            //JpaDishRepositoryImpl rep = ctx.getBean(JpaDishRepositoryImpl.class);

            List<Dish> dishList = controller.getByDate(LocalDate.of(2015, Month.MAY, 30));

            for (Dish dish : dishList) {
                System.out.println(dish.toString());
            }
            System.out.println("FFF");
            //System.out.println(dish.getName());
        }
    }
}
