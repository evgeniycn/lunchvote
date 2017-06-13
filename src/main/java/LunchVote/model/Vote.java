package LunchVote.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by evgeniy on 13.06.2017.
 */
@Entity
@Table(name = "VOTES", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Vote extends BaseEntity {

    @OneToMany
    @JoinColumn(name="USER_ID")
    /*@ElementCollection(targetClass = User.class)
    @Fetch(FetchMode.SUBSELECT)*/
    private Set<User> users;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTRAUNT_ID", insertable = false, updatable = false)
    //@Fetch(FetchMode.SUBSELECT)
    private Restraunt restraunt;

    public Vote() {
    }

    public void setRestraunt(Restraunt restraunt) {
        this.restraunt = restraunt;
    }

    public Restraunt getRestraunt() {
        return restraunt;
    }

    public Set<User> getUsers() {
        return users;
    }

    public LocalDate getDate() {
        return date;
    }

    /*public void setRestrauntId(int restrauntId) {
        this.restrauntId = restrauntId;
    }*/

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "users=" + users +
                ", date=" + date +
                ", restraunt=" + restraunt +
                '}';
    }
}
