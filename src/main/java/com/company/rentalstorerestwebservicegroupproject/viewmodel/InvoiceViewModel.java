package com.company.rentalstorerestwebservicegroupproject.viewmodel;

import com.company.rentalstorerestwebservicegroupproject.model.Customer;
import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(allowSetters = true, value = {"_orderDate", "_pickupDate", "_returnDate", })
public class InvoiceViewModel {

    @Digits(integer = 11, fraction = 0)
    private Integer invoiceId;

    @Valid
    private Customer customer;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;

    @NotNull(message = "Missing orderDate")
    @Pattern(regexp = "^[0-9]{4}[-](0[1-9]|1[0-2])[-](0[1-9]|[1-2][0-9]|3[0-1])$",
            message = "Invalid pickup date."
    )
    private String _orderDate;

    @NotNull(message = "Missing pickup date")
    @Pattern(regexp = "^[0-9]{4}[-](0[1-9]|1[0-2])[-](0[1-9]|[1-2][0-9]|3[0-1])$",
            message = "Invalid pickup date."
    )
    private String _pickupDate;

    @NotNull(message = "Missing return date")
    @Pattern(regexp = "^[0-9]{4}[-](0[1-9]|1[0-2])[-](0[1-9]|[1-2][0-9]|3[0-1])$",
            message = "Invalid pickup date."
    )
    private String _returnDate;

    @NotNull(message = "Missing late Fee")
    @Digits(integer = 8, fraction = 2)
    private Double lateFee;

    @Valid
    private List<InvoiceItemViewModel> invoiceItemList;

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

    public List<InvoiceItemViewModel> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(
            List<InvoiceItemViewModel> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public String get_orderDate() {
        return _orderDate;
    }

    public void set_orderDate(String _orderDate) {
        this.orderDate = LocalDate.parse(_orderDate);
        this._orderDate = _orderDate;
    }

    public String get_pickupDate() {
        return _pickupDate;
    }

    public void set_pickupDate(String _pickupDate) {
        this.pickupDate = LocalDate.parse(_pickupDate);
        this._pickupDate = _pickupDate;
    }

    public String get_returnDate() {
        return _returnDate;
    }

    public void set_returnDate(String _returnDate) {
        this.returnDate = LocalDate.parse(_returnDate);
        this._returnDate = _returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceViewModel)) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getInvoiceId().equals(that.getInvoiceId()) &&
                getCustomer().equals(that.getCustomer()) &&
                getOrderDate().equals(that.getOrderDate()) &&
                getPickupDate().equals(that.getPickupDate()) &&
                getReturnDate().equals(that.getReturnDate()) &&
                getLateFee().equals(that.getLateFee()) &&
                getInvoiceItemList().equals(that.getInvoiceItemList());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getInvoiceId(), getCustomer(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee(),
                        getInvoiceItemList());
    }
}
