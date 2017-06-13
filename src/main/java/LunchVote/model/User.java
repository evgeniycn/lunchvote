package LunchVote.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE_BY_ID, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL, query = "SELECT u FROM User u ORDER BY u.id DESC"),
        //@NamedQuery(name = User.VOTE, query = "INSERT INTO VOTES (restraunt_id, user_id, date) VALUES (?, ?, ?)", restrauntId, userId, date),
        //"INSERT INTO user_roles (user_id, role) VALUES (?, ?)", roles, roles.size()
})
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User extends BaseEntity {
    public static final String DELETE_BY_ID  = "User.deleteById";

    public static final String ALL  = "User.getAll";

    //public static final String VOTE = "User.vote";

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LAST_VOTE_DATE", nullable = true)
    private LocalDate lastVoteDate;

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "restraunt")
    @ElementCollection(targetClass=Vote.class)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<Vote> votes;*/

    public User() {
    }

    public User(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastVoteDate = null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getLastVoteDate() {
        return lastVoteDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastVoteDate(LocalDate lastVoteDate) {
        this.lastVoteDate = lastVoteDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastVoteDate='" + lastVoteDate + '\'' +
                '}';
    }
}
