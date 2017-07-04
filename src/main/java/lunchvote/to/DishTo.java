package lunchvote.to;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Evgeniy on 04.07.2017.
 */
public class DishTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank
    @Length(min = 3, max = 25)
    private String name;

    @NotNull
    @Range(min = 10, max = 1000)
    private double price;

    @NotNull
    private LocalDate date;

    @NotNull
    private int restrauntId;



    public DishTo() {
    }

    public DishTo(String name, double price, LocalDate date, int restrauntId) {
        this(null, name, price, date, restrauntId);
    }

    public DishTo(Integer id, String name, double price, LocalDate date, int restrauntId) {
        super(id);
        this.name = name;
        this.price = price;
        this.date = date;
        this.restrauntId = restrauntId;
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

    public int getRestrauntId() {
        return restrauntId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishTo dishTo = (DishTo) o;

        if (Double.compare(dishTo.price, price) != 0) return false;
        if (restrauntId != dishTo.restrauntId) return false;
        if (name != null ? !name.equals(dishTo.name) : dishTo.name != null) return false;
        return !(date != null ? !date.equals(dishTo.date) : dishTo.date != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + restrauntId;
        return result;
    }
}
