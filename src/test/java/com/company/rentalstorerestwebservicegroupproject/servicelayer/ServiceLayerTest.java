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
    private Invoice invoice1a =new Invoice();
    private Invoice invoice1b = new Invoice();
    private Invoice invoice2a =new Invoice();
    private Invoice invoice2b = new Invoice();
    private Customer customer = new Customer();
    private Customer customer2 = new Customer();
    private Customer customer3 = new Customer();

    @Before
    public void setUp() throws Exception {
        constructSampleData();

        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();

        sl = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }


    @Test
    public void addFindItem() {
        Item item1 = new Item();
        item1.setName("xyx");
        item1.setDescription("something");
        item1.setDailyRate(new BigDecimal(23.45));

        item1 = sl.addItem(item1);

        Item item2 = sl.findItem(item1.getItemId());
        assertEquals(item1, item2);
    }

    @Test
    public void findAllItem() {
        Item item1 = new Item();
        item1.setName("xyx");
        item1.setDescription("something");
        item1.setDailyRate(new BigDecimal(23.45));
        item1 = sl.addItem(item1);

        List<Item> items = sl.findAllItem();
        assertEquals(1, items.size());
    }

    @Test
    public void updateItem() {
        Item item1 = new Item();
        item1.setName("xyxx");
        item1.setDescription("somethingx");
        item1.setDailyRate(new BigDecimal(23.45));

        item1 = sl.addItem(item1);

        item1.setName("something else");
        item1.setDescription("something");

        sl.updateItem(item1);

        Item item2 = sl.findItem(item1.getItemId());
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
        iivm.setQuantity(45);
        iivm.setUnitRate(234.23);
        iivm.setDiscount(23.33);

        Item item = new Item();
        item.setName("xyx");
        item.setDescription("something");
        item.setDailyRate(new BigDecimal(23.45));

        iivm.setItem(item);

        List<InvoiceItemViewModel> iivms = new ArrayList<>();
        iivms.add(iivm);

        ivm.setInvoiceItemList(iivms);

        sl.addInvoiceViewModel(ivm);
        InvoiceViewModel ivm2 = sl.findInvoiceViewModel(ivm.getInvoiceId());
        assertEquals(ivm, ivm2);
    }

    @Test
    public void deleteInvoiceViewModel() {
        sl.deleteInvoiceViewModel(2);
        InvoiceViewModel ivm = sl.findInvoiceViewModel(2);
        assertNull(ivm);

        sl.deleteInvoiceItemViewModel(15);
        InvoiceItemViewModel iivm = sl.findInvoiceItemViewModel(15);
        assertNull(iivm);

        List<InvoiceItemViewModel> iivmList = sl.findAllInvoiceItemViewModelByInvoiceId(15);
        assertNull(iivmList);
    }

    @Test
    public void findAllInvoiceViewModels() {
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
        iivm.setInvoiceId(1);
        iivm.setQuantity(45);
        iivm.setUnitRate(234.23);
        iivm.setDiscount(23.33);

        Item item = new Item();
        item.setName("xyx");
        item.setDescription("something");
        item.setDailyRate(new BigDecimal(23.45));

        iivm.setItem(item);

        List<InvoiceItemViewModel> iivms = new ArrayList<>();
        iivms.add(iivm);

        ivm.setInvoiceItemList(iivms);

        sl.addInvoiceViewModel(ivm);
        List<InvoiceViewModel> invoiceViewModels = sl.findAllInvoiceViewModels();
        assertEquals(1, invoiceViewModels.size());
    }

    @Test
    public void addFindCustomerViewModel() {
        CustomerViewModel customerVM = new CustomerViewModel();

        customerVM.setFirstName("Elinisa");
        customerVM.setLastName("Canameti");
        customerVM.setEmail("ec@gmail.com");
        customerVM.setCompany("Google");
        customerVM.setPhone("7186693953");

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice1a);

        customerVM.setInvoiceList(invoices);

        sl.addCustomerViewModel(customerVM);
        CustomerViewModel customerVM2 = sl.findCustomerViewModel(customerVM.getCustomerId());
        assertEquals(customerVM, customerVM2);
    }

    @Test
    public void deleteCustomerViewModel() {
        sl.deleteCustomerViewModel(5);
        CustomerViewModel cvm = sl.findCustomerViewModel(5);
        assertNull(cvm);

        List<InvoiceViewModel> ivm = sl.findInvoiceViewModelByCustomerId(5);
        assertNull(ivm);
    }

    @Test
    public void findAllCustomerViewModels() {
        CustomerViewModel customerVM = new CustomerViewModel();

        customerVM.setFirstName("Elinisa");
        customerVM.setLastName("Canameti");
        customerVM.setEmail("ec@gmail.com");
        customerVM.setCompany("Google");
        customerVM.setPhone("7186693953");

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice1a);

        customerVM.setInvoiceList(invoices);
        sl.addCustomerViewModel(customerVM);

        List<CustomerViewModel> customerViewModels = sl.findAllCustomerViewModels();
        assertEquals(2, customerViewModels.size());
    }

    @Test
    public void updateCustomerViewModel() {
        CustomerViewModel customerVM = new CustomerViewModel();

        customerVM.setCustomerId(3);
        customerVM.setFirstName("Eli");
        customerVM.setLastName("Cana");
        customerVM.setEmail("ec@yahoo.com");
        customerVM.setCompany("T");
        customerVM.setPhone("7186693543");

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice2a);

        customerVM.setInvoiceList(invoices);

        sl.updateCustomerViewModel(customerVM);
        CustomerViewModel customerVM2 = sl.findCustomerViewModel(customerVM.getCustomerId());
        assertEquals(customerVM, customerVM2);
    }

    private void constructSampleData() {

        item1a.setName("xyx");
        item1a.setDescription("something");
        item1a.setDailyRate(new BigDecimal(23.45));

        item1b.setItemId(99);
        item1b.setName("xyx");
        item1b.setDescription("something");
        item1b.setDailyRate(new BigDecimal(23.45));

        item2a.setName("xyxx");
        item2a.setDescription("somethingx");
        item2a.setDailyRate(new BigDecimal(23.45));

        item2b.setItemId(100);
        item2b.setName("xyxx");
        item2b.setDescription("somethingx");
        item2b.setDailyRate(new BigDecimal(23.45));

        item2c.setItemId(100);
        item2c.setName("something else");
        item2c.setDescription("something");
        item2c.setDailyRate(new BigDecimal(23.45));

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
        invoiceItem2A.setQuantity(46);
        invoiceItem2A.setUnitRate(234.23);
        invoiceItem2A.setDiscount(23.33);

        invoiceItem2B.setInvoiceItemId(2);
        invoiceItem2B.setInvoiceId(1);
        invoiceItem2B.setItemId(99);
        invoiceItem2B.setQuantity(46);
        invoiceItem2B.setUnitRate(234.23);
        invoiceItem2B.setDiscount(23.33);

        invoiceItem2C.setInvoiceItemId(2);
        invoiceItem2C.setInvoiceId(1);
        invoiceItem2C.setItemId(101);
        invoiceItem2C.setQuantity(450);
        invoiceItem2C.setUnitRate(234.23);
        invoiceItem2C.setDiscount(23.33);

        invoice1a.setCustomerId(1);
        invoice1a.setOrderDate(LocalDate.of(2019,5,6));
        invoice1a.setPickupDate(LocalDate.of(2020,7,8));
        invoice1a.setReturnDate(LocalDate.of(2018,4,5));
        invoice1a.setLateFee(34.65);

        invoice1b.setInvoiceId(1);
        invoice1b.setCustomerId(1);
        invoice1b.setOrderDate(LocalDate.of(2019,5,6));
        invoice1b.setPickupDate(LocalDate.of(2020,7,8));
        invoice1b.setReturnDate(LocalDate.of(2018,4,5));
        invoice1b.setLateFee(34.65);

        invoice2a.setCustomerId(3);
        invoice2a.setOrderDate(LocalDate.of(2019,5,6));
        invoice2a.setPickupDate(LocalDate.of(2020,7,8));
        invoice2a.setReturnDate(LocalDate.of(2018,4,5));
        invoice2a.setLateFee(34.65);

        invoice2b.setInvoiceId(3);
        invoice2b.setCustomerId(3);
        invoice2b.setOrderDate(LocalDate.of(2019,5,6));
        invoice2b.setPickupDate(LocalDate.of(2020,7,8));
        invoice2b.setReturnDate(LocalDate.of(2018,4,5));
        invoice2b.setLateFee(34.65);

        customer.setCustomerId(1);
        customer.setFirstName("Elinisa");
        customer.setLastName("Canameti");
        customer.setEmail("ec@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("7186693953");

        customer2.setFirstName("Elinisa");
        customer2.setLastName("Canameti");
        customer2.setEmail("ec@gmail.com");
        customer2.setCompany("Google");
        customer2.setPhone("7186693953");

        customer3.setCustomerId(3);
        customer3.setFirstName("Eli");
        customer3.setLastName("Cana");
        customer3.setEmail("ec@yahoo.com");
        customer3.setCompany("T");
        customer3.setPhone("7186693543");
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

        doReturn(invoiceItems).when(invoiceItemDao).getInvoiceItemByInvoiceId(1);
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        List<Invoice> list1 = new ArrayList<>();
        list1.add(invoice1b);

        List<Invoice> list2 = new ArrayList<>();
        list2.add(invoice2b);

        doReturn(invoice1b).when(invoiceDao).addInvoice(invoice1a);
        doReturn(invoice1b).when(invoiceDao).getInvoice(1);
        doReturn(invoice2b).when(invoiceDao).addInvoice(invoice2a);
        doReturn(invoice2b).when(invoiceDao).getInvoice(3);
        doReturn(list1).when(invoiceDao).getAllInvoices();
        doNothing().when(invoiceDao).deleteInvoice(2);
        doReturn(null).when(invoiceDao).getInvoice(2);
        doReturn(list2).when(invoiceDao).getAllInvoicesByCustomerId(3);
        doReturn(null).when(invoiceDao).getAllInvoicesByCustomerId(5);
        doReturn(list1).when(invoiceDao).getAllInvoicesByCustomerId(1);

    }

    private void setUpCustomerDaoMock() {
        customerDao=mock(CustomerDaoJdbcTemplateImpl.class);

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

}