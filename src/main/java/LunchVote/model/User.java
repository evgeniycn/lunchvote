package LunchVote.model;

import org.hibernate.annotations.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE_BY_ID, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL, query = "SELECT u FROM User u ORDER BY u.id DESC"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
})
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User extends BaseEntity {
    public static final String DELETE_BY_ID  = "User.deleteById";
    public static final String ALL  = "User.getAll";
    public static final String BY_EMAIL = "User.getByEmail";

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LAST_VOTE_DATE", nullable = true)
    private LocalDate lastVoteDate;

    ////@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<Role> roles;


    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Collection<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastVoteDate = null;
        setRoles(roles);
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

    public Set<Role> getRoles() {
        return roles;
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

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
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
