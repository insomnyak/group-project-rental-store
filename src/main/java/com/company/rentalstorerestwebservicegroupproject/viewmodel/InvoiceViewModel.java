package com.company.rentalstorerestwebservicegroupproject.viewmodel;

import java.time.LocalDate;
import java.util.Objects;

public class InvoiceViewModel {
    private Integer invoiceId;
    private Customer customer;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private Double lateFee;
    private List<InvoiceItems> invoiceItemsList;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<InvoiceItems> getInvoiceItemsList() {
        return invoiceItemsList;
    }

    public void setInvoiceItemsList(List<InvoiceItems> invoiceItemsList) {
        this.invoiceItemsList = invoiceItemsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceViewModel)) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(getInvoiceId(), that.getInvoiceId()) &&
                getCustomer().equals(that.getCustomer()) &&
                getOrderDate().equals(that.getOrderDate()) &&
                getPickupDate().equals(that.getPickupDate()) &&
                getReturnDate().equals(that.getReturnDate()) &&
                getLateFee().equals(that.getLateFee()) &&
                Objects.equals(getInvoiceItemsList(), that.getInvoiceItemsList());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getInvoiceId(), getCustomer(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee(),
                        getInvoiceItemsList());
    }
}
