package LunchVote.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE_BY_ID, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL, query = "SELECT u FROM User u ORDER BY u.id DESC"),
})
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User extends BaseEntity {
    public static final String DELETE_BY_ID  = "User.deleteById";

    public static final String ALL  = "User.getAll";


    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restraunt")
    @ElementCollection(targetClass=Vote.class)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<Vote> votes;

    public User() {
    }

    public User(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
