package LunchVote;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;
import LunchVote.model.Vote;
import LunchVote.service.RestrauntService;
import LunchVote.service.UserService;
import LunchVote.web.Dish.DishRestController;
import LunchVote.web.Restraunt.RestrauntRestController;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.naming.TimeLimitExceededException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

/**
 * Created by evgeniy on 10.05.2017.
 */
public class Main {

    public static void main(String[] args) {

        try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.load("spring/spring-db.xml", "spring/spring-app.xml");
            ctx.refresh();

            UserService service = ctx.getBean(UserService.class);

            Vote vote = new Vote();
            vote.setDate(LocalDate.now());
            vote.setRestraunt(100013);
            vote.setUsers(100000);
            try {
                service.sendVote(vote);
            } catch (TimeLimitExceededException e) {
                e.printStackTrace();
            }

            RestrauntService restrauntService = ctx.getBean(RestrauntService.class);

            Map<Integer, Integer> votesByDate = restrauntService.getAllWithVotesByDate(LocalDate.of(2015, Month.MAY, 31));
            System.out.println();

            /*DishRestController dishController = ctx.getBean(DishRestController.class);
            //JpaDishRepositoryImpl rep = ctx.getBean(JpaDishRepositoryImpl.class);

            RestrauntRestController restController = ctx.getBean(RestrauntRestController.class);

            RestrauntService service = ctx.getBean(RestrauntService.class);

            List<Dish> dishList = dishController.getByDate(LocalDate.of(2015, Month.MAY, 31));

            List<Dish> dishListRestrauntId = dishController.getByDateRestrauntId(LocalDate.of(2015, Month.MAY, 31), 100009);

            List<Restraunt> restrauntsToday = restController.getAllWithTodayMenu(LocalDate.of(2015, Month.MAY, 31));

            System.out.println("<---------------Get dishList--------------->");

            for (Dish dish : dishList) {
                System.out.println(dish.toString());
            }
            System.out.println("<---------------Done--------------->");
            System.out.println();

            System.out.println("<---------------Get dishListRestrauntId--------------->");
            for (Dish dish : dishListRestrauntId) {
                System.out.println(dish.toString());
            }
            System.out.println("<---------------Done--------------->");
            System.out.println();

            System.out.println("<---------------Get today restraunts--------------->");
            for (Restraunt restraunt : restrauntsToday) {
                System.out.println(restraunt.toString());
            }
            System.out.println("<---------------Done--------------->");
            System.out.println();

            List<Restraunt> restraunts = service.getAll();

            for (Restraunt restraunt : restraunts) {
                System.out.println(restraunt.getVotes().toString());
            }

            System.out.println("FFF");*/
            //System.out.println(dish.getName());
        }
    }
}
