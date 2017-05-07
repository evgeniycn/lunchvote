package LunchVote;

import LunchVote.model.BaseEntity;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public class AuthorizedUser {

    public static int id = BaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
