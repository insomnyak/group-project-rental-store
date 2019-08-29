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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

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
    public void addGetDeleteInvoiceItem() {

            Customer customer = new Customer();
            customer.setCompany("ABC");
            customer.setFirstName("Raj");
            customer.setLastName("Jindal");
            customer.setPhone("1234567890");
            customer.setEmail("rj@gmail.com");
            customer = customerDao.addCustomer(customer);

            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getCustomerId());
            invoice.setOrderDate(LocalDate.parse("2017-11-17"));
            invoice.setPickupDate(LocalDate.parse("2018-08-04"));
            invoice.setReturnDate(LocalDate.parse("2018-08-04"));
            invoice.setLateFee(125.66);
            invoice = invoiceDao.addInvoice(invoice);

            Item item = new Item();
            item.setItem_id(item.getItem_id());
            item.setName("Apple");
            item.setDescription("Fruit");
            item.setDaily_rate(BigDecimal.valueOf(2.99));
            item = itemDao.addItem(item);

            InvoiceItem invoiceitem = new InvoiceItem();
            invoiceitem.setInvoiceId(invoice.getInvoiceId());
            invoiceitem.setItemId(item.getItem_id());
            invoiceitem.setQuantity(2);
            invoiceitem.setUnitRate(3.99);
            invoiceitem.setDiscount(2.99);
            invoiceitem = invoiceItemDao.addInvoiceItem(invoiceitem);

            InvoiceItem invoiceitem1 = invoiceItemDao.getInvoiceItem(invoiceitem.getInvoiceItemId());

            assertEquals(invoiceitem, invoiceitem1);

            invoiceItemDao.deleteInvoiceItem(invoiceitem.getInvoiceItemId());

            invoiceitem1 = invoiceItemDao.getInvoiceItem(invoiceitem.getInvoiceItemId());

            assertNull(invoiceitem1);

        }

        @Test(expected  = DataIntegrityViolationException.class)
        public void addWithRefIntegrityException() {

            InvoiceItem invoiceitem = new InvoiceItem();
            invoiceitem.setInvoiceId(invoiceitem.getInvoiceId());
            invoiceitem.setItemId(invoiceitem.getItemId());
            invoiceitem.setQuantity(2);
            invoiceitem.setUnitRate(3.99);
            invoiceitem.setDiscount(2.99);
            invoiceitem = invoiceItemDao.addInvoiceItem(invoiceitem);

        }

        @Test
        public void getAllInvoiceItems() {

            Customer customer = new Customer();
            customer.setCompany("ABC");
            customer.setFirstName("Raj");
            customer.setLastName("Jindal");
            customer.setPhone("1234567890");
            customer.setEmail("rj@gmail.com");
            customer = customerDao.addCustomer(customer);

            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getCustomerId());
            invoice.setOrderDate(LocalDate.parse("2017-11-17"));
            invoice.setPickupDate(LocalDate.parse("2018-08-04"));
            invoice.setReturnDate(LocalDate.parse("2018-08-04"));
            invoice.setLateFee(125.66);
            invoice = invoiceDao.addInvoice(invoice);

            Item item = new Item();
            item.setName("Apple");
            item.setDescription("Fruit");
            item.setDaily_rate(BigDecimal.valueOf(2.99));
            item = itemDao.addItem(item);

            InvoiceItem invoiceitem = new InvoiceItem();
            invoiceitem.setInvoiceId(invoice.getInvoiceId());
            invoiceitem.setItemId(item.getItem_id());
            invoiceitem.setQuantity(2);
            invoiceitem.setUnitRate(3.99);
            invoiceitem.setDiscount(2.99);
            invoiceitem = invoiceItemDao.addInvoiceItem(invoiceitem);

            invoiceitem = new InvoiceItem();
            invoiceitem.setInvoiceId(invoice.getInvoiceId());
            invoiceitem.setItemId(item.getItem_id());
            invoiceitem.setQuantity(5);
            invoiceitem.setUnitRate(8.99);
            invoiceitem.setDiscount(5.99);
            invoiceitem = invoiceItemDao.addInvoiceItem(invoiceitem);

            List<InvoiceItem> inList = invoiceItemDao.getAllInvoiceItems();

            assertEquals(inList.size(), 2);

        }

        @Test
        public void updateInvoiceItem() {

            Customer customer = new Customer();
            customer.setCompany("ABC");
            customer.setFirstName("Raj");
            customer.setLastName("Jindal");
            customer.setPhone("1234567890");
            customer.setEmail("rj@gmail.com");
            customer = customerDao.addCustomer(customer);

            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getCustomerId());
            invoice.setOrderDate(LocalDate.parse("2017-11-17"));
            invoice.setPickupDate(LocalDate.parse("2018-08-04"));
            invoice.setReturnDate(LocalDate.parse("2018-08-04"));
            invoice.setLateFee(125.66);
            invoice = invoiceDao.addInvoice(invoice);

            Item item = new Item();
            item.setName("Apple");
            item.setDescription("Fruit");
            item.setDaily_rate(BigDecimal.valueOf(2.99));
            item = itemDao.addItem(item);

            InvoiceItem invoiceitem = new InvoiceItem();
            invoiceitem.setInvoiceId(invoice.getInvoiceId());
            invoiceitem.setItemId(item.getItem_id());
            invoiceitem.setQuantity(2);
            invoiceitem.setUnitRate(3.99);
            invoiceitem.setDiscount(2.99);
            invoiceitem = invoiceItemDao.addInvoiceItem(invoiceitem);

            invoiceitem.setQuantity(5);
            invoiceitem.setUnitRate(8.99);
            invoiceitem.setDiscount(5.99);
            invoiceItemDao.updateInvoiceItem(invoiceitem);

            InvoiceItem invoiceitem1 = invoiceItemDao.getInvoiceItem(invoiceitem.getInvoiceItemId());


            assertEquals(invoiceitem1, invoiceitem);
        }



    }
