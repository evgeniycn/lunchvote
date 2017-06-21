package LunchVote;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;
import LunchVote.model.Vote;
import LunchVote.service.RestrauntService;
import LunchVote.service.UserService;
import LunchVote.util.PasswordUtil;
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

            RestrauntService service = ctx.getBean(RestrauntService.class);

            List<Restraunt> restraunts = service.getAll();

            for (Restraunt rest : restraunts) {
                List<Dish> dishList = rest.getDishList();
                for (Dish dish : dishList) {
                    System.out.println(rest.getName());
                    System.out.println(dish.getName());
                    System.out.println("--------------");
                }
            }

            /*UserService service = ctx.getBean(UserService.class);

            Vote vote = new Vote();
            vote.setDate(LocalDate.now());
            vote.setRestrauntId(100013);
            vote.setUserId(100000);
            try {
                service.sendVote(100013);
            } catch (TimeLimitExceededException e) {
                e.printStackTrace();
            }

            RestrauntService restrauntService = ctx.getBean(RestrauntService.class);

            Map<Integer, Integer> votesByDate = restrauntService.getAllWithVotesByDate(LocalDate.of(2015, Month.MAY, 31));
            System.out.println();*/


           /* System.out.println(PasswordUtil.encode("password1"));
            System.out.println(PasswordUtil.encode("password2"));
            System.out.println(PasswordUtil.encode("password3"));
            System.out.println(PasswordUtil.encode("admin1"));
            System.out.println(PasswordUtil.encode("admin2"));*/



           /* ('User1', 'user1@user.com', 'password1', NULL),
            ('User2', 'user2@user.com', 'password2', now),
            ('User3', 'user3@user.com', 'password3', now),
            ('Admin1', 'admin1@gmail.com', 'admin1', NULL),
            ('Admin2', 'admin2@gmail.com', 'admin2', NULL);*/
        }
    }
}
