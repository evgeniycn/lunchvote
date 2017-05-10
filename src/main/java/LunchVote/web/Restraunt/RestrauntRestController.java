package LunchVote.web.Restraunt;

import LunchVote.model.Restraunt;
import LunchVote.service.RestrauntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 10.05.2017.
 */
@Controller
public class RestrauntRestController {

    private RestrauntService service;

    @Autowired
    public RestrauntRestController(RestrauntService service) {
        this.service = service;
    }

    public List<Restraunt> getAllWithTodayMenu (LocalDate date) {
        return service.getAllWithTodayMenu(date);
    }

}
