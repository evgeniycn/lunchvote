package LunchVote;

import LunchVote.model.Restraunt;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static LunchVote.model.BaseEntity.START_SEQ;
import static java.time.LocalDate.of;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class RestrauntTestData {

    public static final int RESTRAUNT1_ID = START_SEQ + 11;

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