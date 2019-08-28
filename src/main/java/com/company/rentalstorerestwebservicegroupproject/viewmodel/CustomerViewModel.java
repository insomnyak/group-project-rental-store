package com.company.rentalstorerestwebservicegroupproject.viewmodel;

import java.util.Objects;

public class CustomerViewModel {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
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
