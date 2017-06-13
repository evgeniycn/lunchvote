package LunchVote.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by evgeniy on 13.06.2017.
 */
@NamedQueries({
        @NamedQuery(name = Vote.DELETE_BY_RESTRAUNT_ID, query = "DELETE FROM Vote v WHERE v.userId=:userId AND v.date=:date"),
        @NamedQuery(name = Vote.ALL_BY_DATE_WITH_VOTES, query = "SELECT v FROM Vote v WHERE v.date=:date ORDER BY v.date DESC"),
})
@Entity
@Table(name = "VOTES", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Vote extends BaseEntity {

    public static final String DELETE_BY_RESTRAUNT_ID  = "Vote.deleteByRestrauntId";

    public static final String ALL_BY_DATE_WITH_VOTES = "Vote.getAllByDateWithVotes";
    /*@OneToMany
    @JoinColumn(name="USER_ID")*/
    /*@ElementCollection(targetClass = User.class)
    @Fetch(FetchMode.SUBSELECT)*/
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTRAUNT_ID", insertable = false, updatable = false)*/
    //@Fetch(FetchMode.SUBSELECT)
    @Column(name = "RESTRAUNT_ID")
    private Integer restrauntId;

    public Vote() {
    }

    public void setRestraunt(Integer restrauntId) {
        this.restrauntId = restrauntId;
    }

    public Integer getRestrauntId() {
        return restrauntId;
    }

    public Integer getUsers() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    /*public void setRestrauntId(int restrauntId) {
        this.restrauntId = restrauntId;
    }*/

    public void setUsers(Integer userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "users=" + userId +
                ", date=" + date +
                ", restraunt=" + restrauntId +
                '}';
    }
}
