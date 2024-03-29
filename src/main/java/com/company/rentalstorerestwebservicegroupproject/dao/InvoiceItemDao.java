package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItem(Integer invoiceItemId);

    List<InvoiceItem> getAllInvoiceItems();

    void updateInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(Integer invoiceItemId);

    List <InvoiceItem> getInvoiceItemByInvoiceId(Integer invoiceId);

    List<InvoiceItem> getInvoiceItemByItemId(Integer itemId);
}
