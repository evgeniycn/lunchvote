package LunchVote.model;

import java.time.LocalDate;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public class Dish extends BaseEntity {

    private String name;

    private double price;

    private LocalDate date;

    private int restrauntId;

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
}
