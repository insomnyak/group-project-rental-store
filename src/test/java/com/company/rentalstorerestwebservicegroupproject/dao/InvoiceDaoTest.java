package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.model.Customer;
import com.company.rentalstorerestwebservicegroupproject.model.Invoice;
import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
import com.company.rentalstorerestwebservicegroupproject.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    private Customer customer1 = new Customer() {{
        setFirstName("xyz");
        setLastName("xyz");
        setEmail("xyz@xyz.com");
        setCompany("X Industries");
        setPhone("91723412312");
    }};

    private Invoice invoice1 = new Invoice() {{
        setOrderDate(LocalDate.parse("2019-01-01"));
        setPickupDate(LocalDate.parse("2019-01-01"));
        setReturnDate(LocalDate.parse("2019-01-01"));
        setLateFee(55.55);
    }};

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<InvoiceItem> inList = invoiceItemDao.getAllInvoiceItems();
        inList.stream().forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        List<Invoice> iList = invoiceDao.getAllInvoices();
        iList.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));

        List<Customer> cList = customerDao.getAllCustomers();
        cList.stream().forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));

        List<Item> itList = itemDao.getAllItems();
        itList.stream().forEach(item -> itemDao.deleteItem(item.getItem_id()));
    }

    @Test
    public void addGetDeleteInvoice() {
        customerDao.addCustomer(customer1);
        invoice1.setCustomerId(customer1.getCustomerId());
        invoiceDao.addInvoice(invoice1);

        Invoice i2 = invoiceDao.getInvoice(invoice1.getInvoiceId());
        assertEquals(invoice1, i2);

        invoiceDao.deleteInvoice(invoice1.getInvoiceId());
        i2 = invoiceDao.getInvoice(invoice1.getInvoiceId());
        assertNull(i2);
    }

    @Test(expected  = DataIntegrityViolationException.class)
    public void addWithRefIntegrityException() {
        invoice1.setCustomerId(99);
        invoiceDao.addInvoice(invoice1);
        LocalDate.parse("2019-09-09");
    }

    @Test
    public void getByCustomerId() {
        customerDao.addCustomer(customer1);
        invoice1.setCustomerId(customer1.getCustomerId());
        invoiceDao.addInvoice(invoice1);

        List<Invoice> invoices = invoiceDao.getAllInvoicesByCustomerId(customer1.getCustomerId());
        assertEquals(1, invoices.size());

        invoices = invoiceDao.getAllInvoicesByCustomerId(45);
        assertEquals(0, invoices.size());
    }

}
