package com.company.rentalstorerestwebservicegroupproject.Model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    @Digits(integer = 11,fraction = 1)
    private Integer item_id;
    @Size(max=50)
    private String name;
    @Size(max=255)
    private String description;
    @Digits(integer = 8,fraction = 2)
    private BigDecimal daily_rate;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(BigDecimal daily_rate) {
        this.daily_rate = daily_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_id == item.item_id &&
                name.equals(item.name) &&
                description.equals(item.description) &&
                daily_rate.equals(item.daily_rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, name, description, daily_rate);
    }
}
