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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {


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
    public void addGetDeleteItem() {

        Item item = new Item();
        item.setName("chocolate");
        item.setDescription("delicious");
        item.setDaily_rate(BigDecimal.valueOf(23.45));


        item = itemDao.addItem(item);

        Item item1 = itemDao.getItem(item.getItem_id());

        assertEquals(item1, item);

        itemDao.deleteItem(item.getItem_id());

        item1 = itemDao.getItem(item.getItem_id());

        assertNull(item1);
    }

    @Test
    public void updateItem() {


        Item item = new Item();
        item.setName("chocolate");
        item.setDescription("delicious");
        item.setDaily_rate(BigDecimal.valueOf(23.45));
        item = itemDao.addItem(item);

        item.setName("chips");
        item.setDescription("salty");
        item.setDaily_rate(BigDecimal.valueOf(12.34));

        itemDao.updateItem(item);

        Item item1 = itemDao.getItem(item.getItem_id());

        assertEquals(item1, item);
    }

    @Test
    public void getAllItems() {


        Item item = new Item();
        item.setName("chocolate");
        item.setDescription("delicious");
        item.setDaily_rate(BigDecimal.valueOf(23.45));

        item=itemDao.addItem(item);

        item = new Item();
        item.setName("chocolate");
        item.setDescription("delicious");
        item.setDaily_rate(BigDecimal.valueOf(23.45));

        item = itemDao.addItem(item);


        List<Item> tList = itemDao.getAllItems();
        assertEquals(tList.size(), 2);
    }
}
