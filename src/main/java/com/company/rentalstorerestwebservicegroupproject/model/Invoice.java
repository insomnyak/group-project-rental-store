package com.company.rentalstorerestwebservicegroupproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(allowSetters = true, value = {"_orderDate", "_pickupDate", "_returnDate", })
public class Invoice {

    @Digits(integer = 11, fraction = 0)
    private Integer invoiceId;

    @NotNull(message = "Missing customerId")
    @Digits(integer = 11, fraction = 0)
    private Integer customerId;

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
