package LunchVote.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Restraunt.ALL_BY_TODAY, query = "SELECT r FROM Restraunt r WHERE r.updateDate=:date ORDER BY r.id DESC"),
        @NamedQuery(name = Restraunt.DELETE_BY_ID, query = "DELETE FROM Restraunt r WHERE r.id=:id"),
        @NamedQuery(name = Restraunt.ALL, query = "SELECT r FROM Restraunt r ORDER BY r.id DESC"),
})

@Entity
@Table (name = "RESTRAUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Restraunt extends BaseEntity {

    public static final String ALL_BY_TODAY = "Restraunt.getByToday";

    public static final String ALL = "Restraunt.getAll";

    public static final String DELETE_BY_ID = "Restraunt.deleteById";

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

    public Restraunt(String name, LocalDate updateDate, List<Dish> dishList) {
        super();
        this.name = name;
        this.updateDate = updateDate;
        this.dishList = dishList;
    }

    public Restraunt(Integer id, String name, LocalDate updateDate, List<Dish> dishList) {
        super(id);
        this.name = name;
        this.updateDate = updateDate;
        this.dishList = dishList;
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
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", updateDate=" + updateDate +
                ", dishList=" + dishList +
                '}';
    }

}
