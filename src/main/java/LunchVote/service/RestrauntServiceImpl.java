package LunchVote.service;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;
import LunchVote.repository.RestrauntRepositoy;
import LunchVote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Service
public class RestrauntServiceImpl implements RestrauntService {

    private final RestrauntRepositoy restrauntRepositoy;

    @Autowired
    public RestrauntServiceImpl(RestrauntRepositoy restrauntRepositoy) {
        this.restrauntRepositoy = restrauntRepositoy;
    }


    public List<Dish> getAllDishesByDate(LocalDate date) {
        return restrauntRepositoy.getAllDishesByDate(date);
    }

    public Restraunt get(int id) throws NotFoundException {
        return restrauntRepositoy.get(id);
    }

    public void delete(int id) throws NotFoundException {
        restrauntRepositoy.delete(id);
    }

    public Restraunt save(Restraunt restraunt) {
        return restrauntRepositoy.save(restraunt);
    }

    public List<Restraunt> getAll() {
        return restrauntRepositoy.getAll();
    }
}
