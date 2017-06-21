package LunchVote;

import LunchVote.model.Restraunt;
import LunchVote.util.ModelMatcher;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static LunchVote.DishTestData.*;
import static LunchVote.model.BaseEntity.START_SEQ;
import static java.time.LocalDate.of;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class RestrauntTestData {

    public static final ModelMatcher<Restraunt> MATCHER = ModelMatcher.of(Restraunt.class);
    //public static final ModelMatcher<MealWithExceed> MATCHER_WITH_EXCEED = ModelMatcher.of(MealWithExceed.class);

    public static final int RESTRAUNT1_ID = START_SEQ + 11;
    //public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Restraunt RESTRAUNT1 = new Restraunt(RESTRAUNT1_ID, "Restraunt1", LocalDate.now());
    public static final Restraunt RESTRAUNT2 = new Restraunt(RESTRAUNT1_ID + 1, "Restraunt2", LocalDate.now());
    public static final Restraunt RESTRAUNT3 = new Restraunt(RESTRAUNT1_ID + 2, "Restraunt3", of(2015, Month.MAY, 30));
    public static final Restraunt RESTRAUNT4 = new Restraunt(RESTRAUNT1_ID + 3, "Restraunt4", of(2015, Month.MAY, 31));
    public static final Restraunt RESTRAUNT5 = new Restraunt(RESTRAUNT1_ID + 4, "Restraunt5", of(2015, Month.MAY, 31));
    public static final Restraunt RESTRAUNT6 = new Restraunt(RESTRAUNT1_ID + 5, "Restraunt6", of(2015, Month.MAY, 31));


    public static final List<Restraunt> RESTRAUNTS = Arrays.asList(RESTRAUNT6, RESTRAUNT5, RESTRAUNT4, RESTRAUNT3, RESTRAUNT2, RESTRAUNT1);

    public static Restraunt getCreated() {
        return new Restraunt(null, "New restraunt", of(2017, Month.JUNE, 1));
    }

    public static Restraunt getUpdated() {
        return new Restraunt(RESTRAUNT1_ID, "Updated restraunt", of(2017, Month.AUGUST, 22));
    }
}
/*
('Restraunt1', '2015-05-30', 0),
  ('Restraunt2', '2015-05-31', 5),
  ('Restraunt3', '2015-05-30', 2),
  ('Restraunt4', '2015-05-31', 1),
  ('Restraunt5', '2015-05-31', 7),
  ('Restraunt6', '2015-05-31', 0);

100003	Salat	2015-05-30	50	100008
100004	Macaroni	2015-05-30	80	100008
100005	Soup	2015-05-31	50	100009
100006	Sandwich	2015-05-31	55.5	100009
100007	Jarkoe	2015-05-31	100	100010


100008	2015-05-30	Restraunt1
100009	2015-05-31	Restraunt2
100010	2015-05-30	Restraunt3
100011	2015-05-31	Restraunt4
100012	2015-05-31	Restraunt5
100013	2015-05-31	Restraunt6

 */
