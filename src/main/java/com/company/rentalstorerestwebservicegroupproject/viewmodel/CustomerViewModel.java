package com.company.rentalstorerestwebservicegroupproject.viewmodel;

import com.company.rentalstorerestwebservicegroupproject.model.Invoice;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

public class CustomerViewModel {

    @Digits(integer = 11,fraction = 0)
    private Integer customerId;

    @NotNull(message = "Please enter a first name")
    @NotEmpty(message = "Please enter a first name")
    @Size(max = 50, message = "Your first name is too long")
    private String firstName;

    @NotNull(message = "Please enter a last name")
    @NotEmpty(message = "Please enter a last name")
    @Size(max=50, message = "Your first name is too long")
    private String lastName;

    @NotNull(message = "Please enter an email")
    @NotEmpty(message = "Please enter an email")
    @Size(max=75, message = "Your email is too long")
    @Pattern(regexp = "^[a-z0-9]+([._][a-z0-9]+)*[@][a-z0-9]+([.][a-z0-9]+)*$",
            flags = {Pattern.Flag.CASE_INSENSITIVE},
            message = "Invalid email address provided"
    )
    private String email;

    @NotNull(message = "Please enter a company name")
    @NotEmpty(message = "Please enter a company name")
    @Size(max=50, message = "Your company name is too long")
    private String company;

    @NotNull(message = "Please enter a phone number")
    @NotEmpty(message = "Please enter a phone number")
    @Size(max=50, message = "Your phone number is too long")
    @Pattern(regexp = "^[+]?[\\s]?[0-9]+?([\\s-.]?[0-9]+)*$",
            message = "Invalid phone number provided"
    )
    private String phone;

    private List<Invoice> invoiceList;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerViewModel)) return false;
        CustomerViewModel that = (CustomerViewModel) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getEmail().equals(that.getEmail()) &&
                getCompany().equals(that.getCompany()) &&
                getPhone().equals(that.getPhone()) &&
                Objects.equals(getInvoiceList(), that.getInvoiceList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName(), getLastName(), getEmail(), getCompany(), getPhone(),
                getInvoiceList());
    }
}
