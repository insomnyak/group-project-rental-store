package com.company.rentalstorerestwebservicegroupproject.servicelayer;

import com.company.rentalstorerestwebservicegroupproject.dao.*;
import com.company.rentalstorerestwebservicegroupproject.model.Customer;
import com.company.rentalstorerestwebservicegroupproject.model.Invoice;
import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
import com.company.rentalstorerestwebservicegroupproject.model.Item;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;


public class ServiceLayerTest {

    ServiceLayer sl;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

    private Customer customer1a = new Customer();
    private Customer customer1b = new Customer();
    private Item item1a = new Item();
    private Item item1b = new Item();
    private Item item2a = new Item();
    private Item item2b = new Item();
    private Item item2c = new Item();
    private InvoiceItem invoiceItem1A = new InvoiceItem();
    private InvoiceItem invoiceItem1B = new InvoiceItem();
    private InvoiceItem invoiceItem2A = new InvoiceItem();
    private InvoiceItem invoiceItem2B = new InvoiceItem();
    private InvoiceItem invoiceItem2C = new InvoiceItem();


    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();

        sl = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);

        constructSampleData();
    }

    private void constructSampleData() {
        customer1a.setFirstName("xyz");
        customer1a.setLastName("xyz");
        customer1a.setEmail("xyz@xyz.com");
        customer1a.setCompany("X Industries");
        customer1a.setPhone("91723412312");

        customer1b.setCustomerId(11);
        customer1b.setFirstName("xyz");
        customer1b.setLastName("xyz");
        customer1b.setEmail("xyz@xyz.com");
        customer1b.setCompany("X Industries");
        customer1b.setPhone("91723412312");

        item1a.setName("xyx");
        item1a.setDescription("something");
        item1a.setDaily_rate(new BigDecimal(23.45));

        item1b.setItem_id(99);
        item1b.setName("xyx");
        item1b.setDescription("something");
        item1b.setDaily_rate(new BigDecimal(23.45));

        item2a.setName("xyxx");
        item2a.setDescription("somethingx");
        item2a.setDaily_rate(new BigDecimal(23.45));

        item2b.setItem_id(100);
        item2b.setName("xyxx");
        item2b.setDescription("somethingx");
        item2b.setDaily_rate(new BigDecimal(23.45));

        item2c.setItem_id(100);
        item2c.setName("something else");
        item2c.setDescription("something");

        invoiceItem1A.setInvoiceId(1);
        invoiceItem1A.setItemId(99);
        invoiceItem1A.setQuantity(45);
        invoiceItem1A.setUnitRate(234.23);
        invoiceItem1A.setDiscount(23.33);

        invoiceItem1B.setInvoiceItemId(1);
        invoiceItem1B.setInvoiceId(1);
        invoiceItem1B.setItemId(99);
        invoiceItem1B.setQuantity(45);
        invoiceItem1B.setUnitRate(234.23);
        invoiceItem1B.setDiscount(23.33);

        invoiceItem2A.setInvoiceId(1);
        invoiceItem2A.setItemId(99);
        invoiceItem2A.setQuantity(45);
        invoiceItem2A.setUnitRate(234.23);
        invoiceItem2A.setDiscount(23.33);

        invoiceItem2B.setInvoiceItemId(2);
        invoiceItem2B.setInvoiceId(1);
        invoiceItem2B.setItemId(99);
        invoiceItem2B.setQuantity(45);
        invoiceItem2B.setUnitRate(234.23);
        invoiceItem2B.setDiscount(23.33);

        invoiceItem2C.setInvoiceItemId(2);
        invoiceItem2C.setInvoiceId(1);
        invoiceItem2C.setItemId(101);
        invoiceItem2C.setQuantity(450);
        invoiceItem2C.setUnitRate(234.23);
        invoiceItem2C.setDiscount(23.33);
    }

    private void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);

        List<Item> items = new ArrayList<>();
        items.add(item1b);

        doReturn(item1b).when(itemDao).addItem(item1a);
        doReturn(item1b).when(itemDao).getItem(99);

        doReturn(items).when(itemDao).getAllItems();

        doReturn(item2b).when(itemDao).addItem(item2a);
        doNothing().when(itemDao).updateItem(item2c);
        doReturn(item2c).when(itemDao).getItem(100);

        doNothing().when(itemDao).deleteItem(101);
        doReturn(null).when(itemDao).getItem(101);
    }

    private void setUpInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem1B);

        doReturn(invoiceItem1B).when(invoiceItemDao).addInvoiceItem(invoiceItem1A);
        doReturn(invoiceItem1B).when(invoiceItemDao).getInvoiceItem(1);

        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItems();

        doReturn(invoiceItem2B).when(invoiceItemDao).addInvoiceItem(invoiceItem2A);
        doNothing().when(invoiceItemDao).updateInvoiceItem(invoiceItem2C);
        doReturn(invoiceItem2C).when(invoiceItemDao).getInvoiceItem(2);

        doNothing().when(invoiceItemDao).deleteInvoiceItem(15);
        doReturn(null).when(invoiceItemDao).getInvoiceItem(15);
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice=new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2019,5,6));
        invoice.setPickupDate(LocalDate.of(2020,7,8));
        invoice.setReturnDate(LocalDate.of(2018,4,5));
        invoice.setLateFee(34.65);

        Invoice invoice2=new Invoice();
        invoice.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2019,5,6));
        invoice.setPickupDate(LocalDate.of(2020,7,8));
        invoice.setReturnDate(LocalDate.of(2018,4,5));
        invoice.setLateFee(34.65);

        List<Invoice> ivList = new ArrayList<>();
        ivList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(ivList).when(invoiceDao).getAllInvoices();
        doNothing().when(invoiceDao).deleteInvoice(2);
        doReturn(null).when(invoiceDao).getInvoice(2);

    }

    private void setUpCustomerDaoMock() {
        customerDao=mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Elinisa");
        customer.setLastName("Canameti");
        customer.setEmail("ec@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("7186693953");

        Customer customer2=new Customer();
        customer.setFirstName("Elinisa");
        customer.setLastName("Canameti");
        customer.setEmail("ec@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("7186693953");

        Customer customer3=new Customer();
        customer.setCustomerId(3);
        customer.setFirstName("Eli");
        customer.setLastName("Cana");
        customer.setEmail("ec@yahoo.com");
        customer.setCompany("T");
        customer.setPhone("7186693543");


        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer3);

        doReturn(customer).when(customerDao).addCustomer(customer2);
        doReturn(customer).when(customerDao).getCustomer(1);
        doReturn(customerList).when(customerDao).getAllCustomers();
        doNothing().when(customerDao).updateCustomer(customer3);
        doReturn(customer3).when(customerDao).getCustomer(3);
        doNothing().when(customerDao).deleteCustomer(5);
        doReturn(null).when(customerDao).getCustomer(5);
    }

    @Test
    public void addFindItem() {
        Item item1 = new Item();
        item1.setName("xyx");
        item1.setDescription("something");
        item1.setDaily_rate(new BigDecimal(23.45));

        item1 = sl.addItem(item1);

        Item item2 = sl.findItem(item1.getItem_id());
        assertEquals(item1, item2);
    }

    @Test
    public void findAllItem() {
        Item item1 = new Item();
        item1.setName("xyx");
        item1.setDescription("something");
        item1.setDaily_rate(new BigDecimal(23.45));
        item1 = sl.addItem(item1);

        List<Item> items = sl.findAllItem();
        assertEquals(1, items.size());
    }

    @Test
    public void updateItem() {
        Item item1 = new Item();
        item1.setName("xyxx");
        item1.setDescription("somethingx");
        item1.setDaily_rate(new BigDecimal(23.45));

        item1 = sl.addItem(item1);

        Item item2 = sl.findItem(item1.getItem_id());
        assertEquals(item1, item2);
    }

    @Test
    public void deleteItem() {
        sl.deleteItem(101);
        Item item1 = sl.findItem(101);
        assertNull(item1);
    }

    @Test
    public void addFindInvoiceViewModel() {
        InvoiceViewModel ivm = new InvoiceViewModel();

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Elinisa");
        customer.setLastName("Canameti");
        customer.setEmail("ec@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("7186693953");

        ivm.setCustomer(customer);

        ivm.setOrderDate(LocalDate.of(2019,5,6));
        ivm.setPickupDate(LocalDate.of(2020,7,8));
        ivm.setReturnDate(LocalDate.of(2018,4,5));
        ivm.setLateFee(34.65);

        InvoiceItemViewModel iivm = new InvoiceItemViewModel();
        InvoiceItem invoiceItem1 = new InvoiceItem();
        iivm.setInvoiceId(1);
        iivm.setQuantity(45);
        iivm.setUnitRate(234.23);
        iivm.setDiscount(23.33);

        Item item = new Item();
        item.setName("xyx");
        item.setDescription("something");
        item.setDaily_rate(new BigDecimal(23.45));

        iivm.setItem(item);

        List<InvoiceItemViewModel> iivms = new ArrayList<>();
        iivms.add(iivm);

        sl.addInvoiceViewModel(ivm);
        InvoiceViewModel ivm2 = sl.findInvoiceViewModel(ivm.getInvoiceId());
        assertEquals(ivm, ivm2);
    }

    @Test
    public void findAllInvoiceViewModels() {
    }

    @Test
    public void deleteInvoiceViewModel() {
    }

    @Test
    public void addCustomerViewModel() {
    }

    @Test
    public void findCustomerViewModel() {
    }

    @Test
    public void findAllCustomerViewModels() {
    }

    @Test
    public void updateCustomerViewModel() {
    }

    @Test
    public void deleteCustomerViewModel() {
    }
}