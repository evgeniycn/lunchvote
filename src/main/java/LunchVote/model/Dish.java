package lunchvote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Dish.ALL_BY_DATE, query = "SELECT d FROM Dish d WHERE d.date=:date ORDER BY d.id DESC"),
        @NamedQuery(name = Dish.DELETE_BY_ID, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d ORDER BY d.id DESC"),
})
@Entity
@Table(name = "DISHES", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Dish extends BaseEntity {

    public static final String ALL = "Dish.getAll";

    public static final String ALL_BY_DATE = "Dish.getByDate";

    public static final String DELETE_BY_ID = "Dish.deleteById";

    @Column(name = "NAME", nullable = false)
    @NotBlank
    @Length(min = 3, max = 25)
    private String name;

    @Column(name = "PRICE", nullable = false)
    @NotNull
    @Range(min = 10, max = 1000)
    private double price;

    @Column(name = "DATE", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTRAUNT_ID", insertable = false, updatable = false)
    @BatchSize(size = 200)
    @JsonIgnore
    private Restraunt restraunt;

    public Dish() {

    }

    public Dish(String name, double price, LocalDate date, Restraunt restraunt) {
        this(null, name, price, date, restraunt);
    }

    public Dish(Integer id, String name, double price, LocalDate date, Restraunt restraunt) {
        super(id);
        this.name = name;
        this.price = price;
        this.date = date;
        this.restraunt = restraunt;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restraunt getRestraunt() {
        return restraunt;
    }

    public void setRestraunt(Restraunt restraunt) {
        this.restraunt = restraunt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (Double.compare(dish.price, price) != 0) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        return date != null ? date.equals(dish.date) : dish.date != null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (restraunt != null ? restraunt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
