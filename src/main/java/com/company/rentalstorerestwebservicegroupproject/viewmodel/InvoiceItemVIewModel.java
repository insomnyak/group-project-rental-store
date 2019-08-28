package com.company.rentalstorerestwebservicegroupproject.viewmodel;

import com.company.rentalstorerestwebservicegroupproject.model.Item;

import java.util.Objects;

public class InvoiceItemVIewModel {
    private Integer invoiceItemId;
    private Integer invoiceId;
    private Item item;
    private Integer quantity;
    private Double unitRate;
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
        if (!(o instanceof InvoiceItemVIewModel)) return false;
        InvoiceItemVIewModel that = (InvoiceItemVIewModel) o;
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
