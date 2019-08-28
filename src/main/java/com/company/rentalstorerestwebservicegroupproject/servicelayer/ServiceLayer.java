package com.company.rentalstorerestwebservicegroupproject.servicelayer;

import com.company.rentalstorerestwebservicegroupproject.dao.CustomerDao;
import com.company.rentalstorerestwebservicegroupproject.dao.InvoiceDao;
import com.company.rentalstorerestwebservicegroupproject.dao.InvoiceItemDao;
import com.company.rentalstorerestwebservicegroupproject.dao.ItemDao;
import com.company.rentalstorerestwebservicegroupproject.model.Customer;
import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
import com.company.rentalstorerestwebservicegroupproject.model.Item;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao,
                        InvoiceDao invoiceDao,
                        InvoiceItemDao invoiceItemDao,
                        ItemDao itemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    public Customer addCustomer(Customer customer) {return null;}
    public Customer findCustomer(Integer customerId) {return null;}
    public List<Customer> findAllCustomers() {return null;}
    public void updateCustomer(Customer customer) {}
    public void deleteCustomer(Integer customerId) {}

    public Item addItem(Item item) {
        return null;
    }

    public Item findItem(Integer itemId) {
        return null;
    }

    public List<Item> findAllItem() {
        return null;
    }

    public void updateItem(Item item) {
    }

    public void deleteItem(Integer itemId) {
    }

    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {return null;}
    public InvoiceItem findInvoiceItem(Integer invoiceItemId) {return null;}
    public List<InvoiceItem> findAllInvoiceItems() {return null;}
    public void updateInvoiceItem(InvoiceItem invoiceItem) {}
    public void deleteInvoiceItem(Integer invoiceItemId) {}

    public InvoiceViewModel addInvoiceViewModel(InvoiceViewModel ivm) {
        return null;
    }

    public InvoiceViewModel findInvoiceViewModel(Integer ivmId) {
        return null;
    }

    public List<InvoiceViewModel> findAllInvoiceViewModels() {
        return null;
    }

    public void deleteInvoiceViewModel(Integer ivmId) {

    }

    public CustomerViewModel addCustomerViewModel(CustomerViewModel cvm) {
        return null;
    }

    public CustomerViewModel findCustomerViewModel(Integer cvmId) {
        return null;
    }

    public List<CustomerViewModel> findAllCustomerViewModels() {
        return null;
    }

    public void updateCustomerViewModel(CustomerViewModel cvm) {

    }

    public void deleteCustomerViewModel(Integer cmvId) {

    }
}
