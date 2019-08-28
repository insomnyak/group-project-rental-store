package com.company.rentalstorerestwebservicegroupproject.model;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    private Integer invoiceId;
    private Integer customerId;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private Double lateFee;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId.equals(invoice.invoiceId) &&
                customerId.equals(invoice.customerId) &&
                orderDate.equals(invoice.orderDate) &&
                pickupDate.equals(invoice.pickupDate) &&
                returnDate.equals(invoice.returnDate) &&
                lateFee.equals(invoice.lateFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, orderDate, pickupDate, returnDate, lateFee);
    }
}
