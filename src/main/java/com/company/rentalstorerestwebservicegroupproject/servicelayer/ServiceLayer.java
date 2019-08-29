package com.company.rentalstorerestwebservicegroupproject.servicelayer;

import com.company.rentalstorerestwebservicegroupproject.dao.CustomerDao;
import com.company.rentalstorerestwebservicegroupproject.dao.InvoiceDao;
import com.company.rentalstorerestwebservicegroupproject.dao.InvoiceItemDao;
import com.company.rentalstorerestwebservicegroupproject.dao.ItemDao;
import com.company.rentalstorerestwebservicegroupproject.model.Customer;
import com.company.rentalstorerestwebservicegroupproject.model.Invoice;
import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
import com.company.rentalstorerestwebservicegroupproject.model.Item;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.InvoiceItemViewModel;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
//
//    public Customer addCustomer(Customer customer) {
//
//        return null;
//    }
//    public Customer findCustomer(Integer customerId) {return null;}
//    public List<Customer> findAllCustomers() {return null;}
//    public void updateCustomer(Customer customer) {}
//    public void deleteCustomer(Integer customerId) {}

    public Item addItem(Item item) {

        return itemDao.addItem(item);
    }

    public Item findItem(Integer itemId) {

        return itemDao.getItem(itemId);
    }

    public List<Item> findAllItem() {

        return itemDao.getAllItems();
    }

    public void updateItem(Item item) {

        itemDao.updateItem(item);

    }

    public void deleteItem(Integer itemId) {

        List<InvoiceItem> iivmList=invoiceItemDao.getInvoiceItemByItemId(itemId);

        iivmList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));


        itemDao.deleteItem(itemId);
    }

//    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
//
//        return null;
//    }
//    public InvoiceItem findInvoiceItem(Integer invoiceItemId) {return null;}
//    public List<InvoiceItem> findAllInvoiceItems() {return null;}
//    public void updateInvoiceItem(InvoiceItem invoiceItem) {}
//    public void deleteInvoiceItem(Integer invoiceItemId) {}


    @Transactional
    public InvoiceViewModel addInvoiceViewModel(InvoiceViewModel ivm) {

        Integer customerId = ivm.getCustomer().getCustomerId();

        //If I don't have a customerId
        if (customerId==null){

            customerDao.addCustomer(ivm.getCustomer());

        }
        else {
            //If I have a customerId but its not in the database

            Customer c = customerDao.getCustomer(ivm.getCustomer().getCustomerId());
            if (c == null)
                customerDao.addCustomer(ivm.getCustomer());
        }

            Invoice invoice = new Invoice();
            invoice.setCustomerId(ivm.getCustomer().getCustomerId());
            invoice.setOrderDate(ivm.getOrderDate());
            invoice.setPickupDate(ivm.getPickupDate());
            invoice.setReturnDate(ivm.getReturnDate());
            invoice.setLateFee(ivm.getLateFee());
            invoice = invoiceDao.addInvoice(invoice);
            ivm.setInvoiceId(invoice.getInvoiceId());

            Integer finalInvId = invoice.getInvoiceId();
            ivm.getInvoiceItemList().stream().forEach(invoiceItemViewModel -> {
                invoiceItemViewModel.setInvoiceId(finalInvId);
                addInvoiceItemViewModel(invoiceItemViewModel);
            });


            return ivm;
    }

    public InvoiceViewModel findInvoiceViewModel(Integer ivmId) {

        Invoice invoice=invoiceDao.getInvoice(ivmId);

        return buildInvoiceViewModel(invoice) ;
    }

    public List<InvoiceViewModel> findAllInvoiceViewModels() {

        List<Invoice> invoiceList=invoiceDao.getAllInvoices();

        List<InvoiceViewModel> ivmList=new ArrayList<>();

        for (Invoice invoice : invoiceList){

            InvoiceViewModel ivm=buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        return ivmList;
    }

    public void deleteInvoiceViewModel(Integer ivmId) {
        // 1) delete Invoice Items where Invoice_Id = ivmId
        // 2) delete Invoice where invoice_id = ivmId
        //Remove the InvoiceItemViewModel
        List<InvoiceItem> iivmList=invoiceItemDao.getInvoiceItemByInvoiceId(ivmId);

        iivmList.stream()
                .forEach(invoiceItemViewModel -> invoiceItemDao.deleteInvoiceItem(invoiceItemViewModel.getInvoiceItemId()));


        //Remove the iivm

        invoiceDao.deleteInvoice(ivmId);

    }

    public CustomerViewModel addCustomerViewModel(CustomerViewModel cvm) {

        Customer c = new Customer();
        c.setFirstName(cvm.getFirstName());
        c.setLastName(cvm.getLastName());
        c.setCompany(cvm.getCompany());
        c.setPhone(cvm.getPhone());
        c.setEmail(cvm.getEmail());

        c = customerDao.addCustomer(c);
        cvm.setCustomerId(c.getCustomerId());

        List<Invoice> invoices = cvm.getInvoiceList();

        invoices.stream()
                .forEach(in ->
                {
                    in.setCustomerId(cvm.getCustomerId());
                    invoiceDao.addInvoice(in);
                });

        return cvm;
    }

    public CustomerViewModel findCustomerViewModel(Integer cvmId) {

        Customer customer = customerDao.getCustomer(cvmId);

        return buildCustomerViewModel(customer);
    }

    public List<CustomerViewModel> findAllCustomerViewModels() {

        List<Customer> cList = customerDao.getAllCustomers();

        List<CustomerViewModel> custList = new ArrayList<>();

        for (Customer customer : cList) {
            CustomerViewModel cvm = buildCustomerViewModel(customer);
            custList.add(cvm);
        }

        return custList;
    }

    private CustomerViewModel buildCustomerViewModel(Customer customer) {
        return null;
    }

    public void updateCustomerViewModel(CustomerViewModel cvm)  {

        Customer c = new Customer();
        c.setCustomerId(cvm.getCustomerId());
        c.setFirstName(cvm.getFirstName());
        c.setLastName(cvm.getLastName());
        c.setCompany(cvm.getCompany());
        c.setPhone(cvm.getPhone());
        c.setEmail(cvm.getEmail());

        customerDao.updateCustomer(c);

        List<Invoice> existingInvoices = invoiceDao.getAllInvoicesByCustomerId(cvm.getCustomerId());

        List<Invoice> invoices = cvm.getInvoiceList();
        invoices.stream()
                .forEach(in ->
                {
                    in.setCustomerId(cvm.getCustomerId());

                    in = invoiceDao.addInvoice(in);

                    boolean exists = false;
                    for (Invoice invoice : existingInvoices) {
                        if (in.getInvoiceId() == invoice.getInvoiceId()) {
                            exists = true;
                        }
                    }
                    if (!exists) {
                        invoiceDao.addInvoice(in);
                    }
                });
    }


    public void deleteCustomerViewModel(Integer customerId) {
        List<Invoice> inList = invoiceDao.getAllInvoicesByCustomerId(customerId);

        inList.stream()
                .forEach(in -> invoiceDao.deleteInvoice(in.getInvoiceId()));

        customerDao.deleteCustomer(customerId);
    }

    public InvoiceItemViewModel addInvoiceItemViewModel(InvoiceItemViewModel iivm) {

        Integer itemId=iivm.getItem().getItem_id();

        //If I dont have an item
        if(itemId==null){

            itemDao.addItem(iivm.getItem());
        }
        else {

            Item i=itemDao.getItem(iivm.getItem().getItem_id());
            if (i==null)
                itemDao.addItem(iivm.getItem());
        }
            InvoiceItem ii=new InvoiceItem();
            ii.setInvoiceId(iivm.getInvoiceId());
            ii.setItemId(iivm.getItem().getItem_id());
            ii.setQuantity(iivm.getQuantity());
            ii.setUnitRate(iivm.getUnitRate());
            ii.setDiscount(iivm.getDiscount());
            ii=invoiceItemDao.addInvoiceItem(ii);
            iivm.setInvoiceItemId(ii.getInvoiceItemId());

        return iivm;
    }

    public InvoiceItemViewModel findInvoiceItemViewModel(Integer iivmId) {

        InvoiceItem ii=invoiceItemDao.getInvoiceItem(iivmId);

        return buildInvoiceItemViewModel(ii);
    }

    public List<InvoiceItemViewModel> findAllInvoiceItemViewModels() {

        List<InvoiceItem> iiList=invoiceItemDao.getAllInvoiceItems();

        List<InvoiceItemViewModel> iivmList=new ArrayList<>();

        for (InvoiceItem invoiceItem : iiList){

            InvoiceItemViewModel iivm=buildInvoiceItemViewModel(invoiceItem);
            iivmList.add(iivm);
        }
        return iivmList;
    }

    public void deleteInvoiceItemViewModel(Integer iivId) {

        invoiceItemDao.deleteInvoiceItem(iivId);

    }

    // Helper Methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        //make sure we have an invoice object; otherwise, return null...
        if (invoice==null) return null;

        // Get the associated customer
        Customer customer = customerDao.getCustomer(invoice.getCustomerId());

        // Get the InvoiceItemViewListModel associated with the Invoices
        List<InvoiceItemViewModel> iivmList = new ArrayList<>();
        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemByInvoiceId(invoice.getInvoiceId());
        invoiceItems.stream().forEach(invoiceItem -> {
            InvoiceItemViewModel iivm = buildInvoiceItemViewModel(invoiceItem);
            iivmList.add(iivm);
        });

        // Assemble the InvoiceViewModel
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(invoice.getInvoiceId());
        ivm.setCustomer(customer);
        ivm.setOrderDate(invoice.getOrderDate());
        ivm.setPickupDate(invoice.getPickupDate());
        ivm.setReturnDate(invoice.getReturnDate());
        ivm.setLateFee(invoice.getLateFee());
        ivm.setInvoiceItemList(iivmList);

        // Return the InvoiceViewModel
        return ivm;
    }

    private InvoiceItemViewModel buildInvoiceItemViewModel(InvoiceItem invoiceItem){

        if (invoiceItem==null) return null;

        Item item = itemDao.getItem(invoiceItem.getItemId());

        InvoiceItemViewModel iivm=new InvoiceItemViewModel();
        iivm.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        iivm.setInvoiceId(invoiceItem.getInvoiceId());
        iivm.setItem(item);
        iivm.setQuantity(invoiceItem.getQuantity());
        iivm.setUnitRate(invoiceItem.getUnitRate());
        iivm.setDiscount(invoiceItem.getDiscount());

        return iivm;

    }
}
