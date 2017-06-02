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

    private final RestrauntRepositoy restrauntRepository;

    @Autowired
    public RestrauntServiceImpl(RestrauntRepositoy restrauntRepository) {
        this.restrauntRepository = restrauntRepository;
    }


    /*public List<Dish> getAllDishesByDate(LocalDate date) {
        return restrauntRepository.getAllDishesByDate(date);
    }*/

    public Restraunt get(int id) throws NotFoundException {
        return restrauntRepository.get(id);
    }

    public void delete(int id) throws NotFoundException {
        restrauntRepository.delete(id);
    }

    public Restraunt save(Restraunt restraunt) {
        return restrauntRepository.save(restraunt);
    }

    public List<Restraunt> getAll() {
        return restrauntRepository.getAll();
    }

    @Override
    public List<Restraunt> getAllWithTodayMenu(LocalDate date) {
        return restrauntRepository.getAllWithTodayMenu(date);
    }
}
