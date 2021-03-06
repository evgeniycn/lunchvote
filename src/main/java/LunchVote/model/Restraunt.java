package lunchvote.model;


import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Restraunt.ALL_BY_TODAY, query = "SELECT r FROM Restraunt r WHERE r.updateDate=:date ORDER BY r.id DESC"),
        @NamedQuery(name = Restraunt.DELETE_BY_ID, query = "DELETE FROM Restraunt r WHERE r.id=:id"),
        @NamedQuery(name = Restraunt.ALL, query = "SELECT r FROM Restraunt r ORDER BY r.id DESC"),
        @NamedQuery(name = Restraunt.VOTES_BY_DATE_RESTRAUNT_ID, query = "SELECT r FROM Restraunt r WHERE r.updateDate=:date AND r.id=:id ORDER BY r.id DESC"),
        @NamedQuery(name = Restraunt.ALL_BY_DATE_WITH_VOTES, query = "SELECT r FROM Restraunt r WHERE r.updateDate=:date AND r.id=:id ORDER BY r.id DESC"),
        @NamedQuery(name = Restraunt.ALL_BY_DATE_RESTRAUNT_ID, query = "SELECT r FROM Restraunt r WHERE r.updateDate=:date AND r.id=:restrauntId ORDER BY r.id DESC"),
})

@Entity
@Table(name = "RESTRAUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Restraunt extends BaseEntity {

    public static final String ALL_BY_TODAY = "Restraunt.getByToday";

    public static final String ALL = "Restraunt.getAll";

    public static final String DELETE_BY_ID = "Restraunt.deleteById";

    public static final String VOTES_BY_DATE_RESTRAUNT_ID = "Restraunt.getVotesByDateRestrauntId";

    public static final String ALL_BY_DATE_WITH_VOTES = "Restraunt.getAllByDateWithVotes";

    public static final String ALL_BY_DATE_RESTRAUNT_ID = "Restraunt.getByDateRestrauntId";

    @Column(name = "NAME", nullable = false)
    @NotBlank
    @Length(min = 3, max = 25)
    private String name;

    @Column(name = "update_date", nullable = false)
    @NotNull
    private LocalDate updateDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restraunt")
    @OrderBy("id DESC")
    @BatchSize(size = 200)
    private List<Dish> dishList;


    public Restraunt() {

    }


    public Restraunt(String name, LocalDate updateDate) {
        this(null, name, updateDate);
    }

    public Restraunt(Integer id, String name, LocalDate updateDate) {
        super(id);
        this.name = name;
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public LocalDate getUpdatedDate() {
        return updateDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updateDate = updatedDate;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restraunt restraunt = (Restraunt) o;

        if (name != null ? !name.equals(restraunt.name) : restraunt.name != null) return false;
        return updateDate != null ? updateDate.equals(restraunt.updateDate) : restraunt.updateDate != null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (dishList != null ? dishList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Restraunt{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", updateDate=" + updateDate +
                '}';
    }

}
