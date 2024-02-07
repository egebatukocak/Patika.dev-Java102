package model.insurances;

import java.util.Date;

public abstract class Insurance {
    private String name;
    private double price;
    private Date startDate, endDate;

    public abstract double calculate();

    public String print() {
        return "Insurance{" + "name : " + name + ", price : " + price +
                ", start date : " + startDate + ", end date : " + endDate + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
