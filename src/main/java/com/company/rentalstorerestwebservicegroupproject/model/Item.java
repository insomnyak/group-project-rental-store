package com.company.rentalstorerestwebservicegroupproject.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    @Digits(integer = 11,fraction = 1)
    private Integer itemId;

    @NotNull(message = "Missing item name")
    @NotEmpty(message = "Missing item name")
    @Size(max=50, message = "Item name is too long. Max 50 characters.")
    private String name;

    @Size(max=255, message = "Description is too long. Max 255 characters.")
    private String description;

    @Digits(integer = 8,fraction = 2)
    private BigDecimal dailyRate;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId &&
                name.equals(item.name) &&
                description.equals(item.description) &&
                dailyRate.equals(item.dailyRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, description, dailyRate);
    }
}
