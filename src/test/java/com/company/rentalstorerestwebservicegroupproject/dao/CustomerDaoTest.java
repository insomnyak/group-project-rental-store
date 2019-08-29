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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        public void addGetDeleteCustomer() {

            Customer customer = new Customer();
            customer.setFirstName("Dritero");
            customer.setLastName("Agolli");
            customer.setEmail("Durresi");
            customer.setCompany("Tirana");
            customer.setPhone(("694560987"));


            customer = customerDao.addCustomer(customer);

            Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

            assertEquals(customer1, customer);

            customerDao.deleteCustomer(customer.getCustomerId());

            customer1 = customerDao.getCustomer(customer.getCustomerId());

            assertNull(customer1);
        }

        @Test
        public void updateCustomer() {

            Customer customer = new Customer();
            customer.setFirstName("Dritero");
            customer.setLastName("Agolli");
            customer.setEmail("Durresi");
            customer.setCompany("Tirana");
            customer.setPhone("694560987");


            customer = customerDao.addCustomer(customer);

            customer.setFirstName("Dritero");
            customer.setLastName("Ago");
            customer.setEmail("Durresi");
            customer.setCompany("Tirana");
            customer.setPhone("694560987");

            customerDao.updateCustomer(customer);
            Customer customer1 = customerDao.getCustomer(customer.getCustomerId());
            assertEquals(customer1, customer);
        }

        @Test
        public void getAllCustomers() {

            Customer customer = new Customer();
            customer.setFirstName("Dritero");
            customer.setLastName("Agolli");
            customer.setEmail("Durresi");
            customer.setCompany("Tirana");
            customer.setPhone(("694560987"));


            customer = customerDao.addCustomer(customer);
            customer = new Customer();
            customer.setFirstName("Dritero");
            customer.setLastName("Agolli");
            customer.setEmail("Durresi");
            customer.setCompany("Tirana");
            customer.setPhone("694560987");


            customer=customerDao.addCustomer(customer);

            List<Customer> dList = customerDao.getAllCustomers();
            assertEquals(dList.size(), 2);
        }
    }

