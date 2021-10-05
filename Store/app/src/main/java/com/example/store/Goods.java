package com.example.store;

import java.io.Serializable;
import java.util.Calendar;

public class Goods implements Serializable {
    private String name;
    private String category;
    private String description;
    private double price;
    private boolean availability;
    private long number;
    private Calendar deliveryDate;

    public Goods(String name, String category, String description,
                 double price, boolean availability, long number, Calendar deliveryDate) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.number = number;
        this.deliveryDate = deliveryDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public long getNumber() {
        return number;
    }
    public void setNumber(long number) {
        this.number = number;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStringOfDate () {
        return availability ? "" : deliveryDate.get(Calendar.DAY_OF_MONTH) + "/"
                + deliveryDate.get(Calendar.MONTH) + "/" + deliveryDate.get(Calendar.YEAR);
    }

    public void Edit(Goods goods)
    {
        this.name = goods.getName();
        this.category = goods.getCategory();
        this.description = goods.getDescription();
        this.price = goods.getPrice();
        this.availability = goods.isAvailability();
        this.number = goods.getNumber();
        this.deliveryDate = goods.getDeliveryDate();
    }
}
