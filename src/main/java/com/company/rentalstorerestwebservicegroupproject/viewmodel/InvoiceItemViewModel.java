package com.company.rentalstorerestwebservicegroupproject.viewmodel;

import com.company.rentalstorerestwebservicegroupproject.model.Item;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InvoiceItemViewModel {

    @Digits(integer = 11, fraction = 0)
    private Integer invoiceItemId;

    @Digits(integer = 11, fraction = 0)
    private Integer invoiceId;

    @Valid
    private Item item;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
        if (!(o instanceof InvoiceItemViewModel)) return false;
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return getInvoiceItemId().equals(that.getInvoiceItemId()) &&
                getInvoiceId().equals(that.getInvoiceId()) &&
                getItem().equals(that.getItem()) &&
                getQuantity().equals(that.getQuantity()) &&
                getUnitRate().equals(that.getUnitRate()) &&
                getDiscount().equals(that.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceItemId(), getInvoiceId(), getItem(), getQuantity(), getUnitRate(), getDiscount());
    }
}
