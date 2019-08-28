package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.Model.Customer;
import com.company.rentalstorerestwebservicegroupproject.model.Invoice;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    @Before

    public void setUp() throws Exception {
        // Clean up the test db
        List<Customer> cList = customerDao.getAllCustomers();
        for (Customer c : cList) {
            customerDao.deleteCustomer(c.getCustomerId());
        }

        List<Invoice> iList = invoiceDao.getAllInvoices();

        for (Invoice i : iList) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }



    }


}
