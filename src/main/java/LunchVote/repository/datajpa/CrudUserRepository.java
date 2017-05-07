package LunchVote.repository.datajpa;

import LunchVote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface CrudUserRepository extends JpaRepository<User, Integer> {


    User getOne(int id);

    List<User> findAll();

    User save(User user);

    int delete(int id);
}
