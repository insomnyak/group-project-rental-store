package com.company.rentalstorerestwebservicegroupproject.dao;


import com.company.rentalstorerestwebservicegroupproject.model.Customer;
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
//    @Autowired
//    InvoiceDao invoiceDao;
//    @Autowired
//    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    @Before

    public void setUp() throws Exception {
        // Clean up the test db
        List<Customer> cList = customerDao.getAllCustomers();
        for (Customer c : cList) {
            customerDao.deleteCustomer(c.getCustomerId());
        }

//        List<Invoice> iList = invoiceDao.getAllInvoices();
//
//        for (Invoice i : iList) {
//            invoiceDao.deleteInvoice(i.getInvoiceId());
//        }
//
//        List<InvoiceItem> tList = invoiceItemDao.getAllInvoiceItems();
//
//        for (InvoiceItem t : tList) {
//            invoiceItemDao.deleteInvoiceItem(t.getInvoiceItemId());
//        }

        List<Item> bList = itemDao.getAllItems();

        for (Item b : bList) {
            itemDao.deleteItem(b.getItem_id());
        }

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
