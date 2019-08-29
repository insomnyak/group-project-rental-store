package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(Integer invoiceId);

    List<Invoice> getAllInvoices();

    void deleteInvoice(Integer invoiceId);

    List<Invoice> getAllInvoicesByCustomerId(Integer customerId);

}
