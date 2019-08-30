package com.company.rentalstorerestwebservicegroupproject.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    @Digits(integer = 11, fraction = 0)
    private Integer invoiceId;

    @NotNull(message = "Missing customerId")
    @Digits(integer = 11, fraction = 0)
    private Integer customerId;

    @NotNull(message = "Missing orderDate")
    @PastOrPresent
    private LocalDate orderDate;

    @NotNull(message = "Missing pickup date")
    private LocalDate pickupDate;

    @NotNull(message = "Missing return date")
    private LocalDate returnDate;

    @NotNull(message = "Missing late Fee")
    @Digits(integer = 8, fraction = 2)
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

    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(getInvoiceId(), invoice.getInvoiceId()) &&
                Objects.equals(getCustomerId(), invoice.getCustomerId()) &&
                getOrderDate().equals(invoice.getOrderDate()) &&
                getPickupDate().equals(invoice.getPickupDate()) &&
                getReturnDate().equals(invoice.getReturnDate()) &&
                getLateFee().equals(invoice.getLateFee());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getInvoiceId(), getCustomerId(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee());
    }
}
