package LunchVote.repository.datajpa;

import LunchVote.model.Dish;
import LunchVote.model.Restraunt;
import LunchVote.repository.RestrauntRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@Repository
public class RestrauntRepositoryImpl implements RestrauntRepositoy {

    private final CrudRestrauntRepository crudRestrauntRepository;

    @Autowired
    public RestrauntRepositoryImpl(CrudRestrauntRepository crudRestrauntRepository) {
        this.crudRestrauntRepository = crudRestrauntRepository;
    }

    public List<Dish> getAllDishesByDate(LocalDate date) {
        return crudRestrauntRepository.getAllDishesByDate(date);
    }

    public Restraunt get(int id) {
        return crudRestrauntRepository.getOne(id);
    }

    public boolean delete(int id) {
        return crudRestrauntRepository.delete(id) != 0;
    }

    public Restraunt save(Restraunt restraunt) {
        return crudRestrauntRepository.save(restraunt);
    }

    public List<Restraunt> getAll() {
        return crudRestrauntRepository.findAll();
    }
}
