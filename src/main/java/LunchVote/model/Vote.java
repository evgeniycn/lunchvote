package LunchVote.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    public static final String DELETE_BY_RESTRAUNT_ID = "Vote.deleteByRestrauntId";

    public static final String ALL_BY_DATE_WITH_VOTES = "Vote.getAllByDateWithVotes";

    @Column(name = "USER_ID", nullable = false)
    @NotNull
    private Integer userId;

    @Column(name = "DATE", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "RESTRAUNT_ID")
    @NotNull
    private Integer restrauntId;

    public Vote() {
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getRestrauntId() {
        return restrauntId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRestrauntId(Integer restrauntId) {
        this.restrauntId = restrauntId;
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
