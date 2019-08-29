package com.company.rentalstorerestwebservicegroupproject.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InvoiceItem {

    @Digits(integer = 11, fraction = 0)
    private Integer invoiceItemId;

    @NotNull(message = "Please provide an Invoice ID")
    @Digits(integer = 11, fraction = 0)
    private Integer invoiceId;

    @NotNull(message = "Please provide an Item ID")
    @Digits(integer = 11, fraction = 0)
    private Integer itemId;

    @NotNull(message = "Please provide a quantity")
    private Integer quantity;

    @NotNull(message = "Please provide a Unit Rate")
    @Digits(integer = 8, fraction = 2)
    private Double unitRate;

    @NotNull(message = "Please provide a the discount amount")
    @Digits(integer = 8, fraction = 2)
    private Double discount;

    public Integer getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Integer invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(Double unitRate) {
        this.unitRate = unitRate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoiceItemId.equals(that.invoiceItemId) &&
                invoiceId.equals(that.invoiceId) &&
                itemId.equals(that.itemId) &&
                quantity.equals(that.quantity) &&
                unitRate.equals(that.unitRate) &&
                discount.equals(that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, itemId, quantity, unitRate, discount);
    }
}
