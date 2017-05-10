package LunchVote.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Restraunt.ALL_BY_TODAY, query = "SELECT r FROM Restraunt r WHERE r.updateDate=:date ORDER BY r.id DESC"),
})

@Entity
@Table (name = "RESTRAUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Restraunt extends BaseEntity {

    public static final String ALL_BY_TODAY = "Restraunt.getByToday";

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    //To do: Change to Lazy
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restraunt")
    @OrderBy("id DESC")
    private List<Dish> dishList;

    public Restraunt() {

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
    public String toString() {
        return "Restraunt{" +
                "name='" + name + '\'' +
                ", updateDate=" + updateDate +
                ", dishList=" + dishList +
                '}';
    }
}
