package LunchVote.model;

import org.springframework.data.domain.Persistable;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public class BaseEntity implements Persistable<Integer> {
    public static final int START_SEQ = 100000;

    private Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return getId() == null;
    }

}
