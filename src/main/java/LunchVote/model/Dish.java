package LunchVote.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Evgeniy on 07.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Dish.ALL_BY_DATE, query = "SELECT d FROM Dish d WHERE d.date=:date ORDER BY d.id DESC"),
        @NamedQuery(name = Dish.ALL_BY_DATE_RESTRAUNT_ID, query = "SELECT d FROM Dish d WHERE d.date=:date AND d.restrauntId=:restrauntId  ORDER BY d.restrauntId DESC"),
        @NamedQuery(name = Dish.DELETE_BY_ID, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d ORDER BY d.id DESC"),
})
@Entity
@Table(name = "DISHES", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Dish extends BaseEntity {

    public static final String ALL = "Dish.getAll";

    public static final String ALL_BY_DATE = "Dish.getByDate";

    public static final String ALL_BY_DATE_RESTRAUNT_ID = "Dish.getByDateRestrauntId";

    public static final String DELETE_BY_ID = "Dish.deleteById";

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "RESTRAUNT_ID", nullable = false)
    private int restrauntId;

    @ManyToOne
    @JoinColumn(name = "RESTRAUNT_ID", insertable = false, updatable = false)
    private Restraunt restraunt;

    public Dish() {

    }

    /*public Dish(String name, double price, LocalDate date, int restrauntId) {
        super();
        this.name = name;
        this.price = price;
        this.date = date;
        this.restrauntId = restrauntId;
    }*/

    public Dish(Integer id, String name, double price, LocalDate date, int restrauntId) {
        super(id);
        this.name = name;
        this.price = price;
        this.date = date;
        this.restrauntId = restrauntId;
    }

    public int getRestrauntId() {
        return restrauntId;
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

    public void setRestrauntId(int restrauntId) {
        this.restrauntId = restrauntId;
    }

    public Restraunt getRestraunt() {
        return restraunt;
    }

    public void setRestraunt(Restraunt restraunt) {
        this.restraunt = restraunt;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", restrauntId=" + restrauntId +
                '}';
    }
}
