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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Controller
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

    @Transactional
    public Item addItem(Item item) {

        return itemDao.addItem(item);
    }

    @Transactional
    public Item findItem(Integer itemId) {

        return itemDao.getItem(itemId);
    }

    @Transactional
    public List<Item> findAllItem() {

        return itemDao.getAllItems();
    }

    @Transactional
    public void updateItem(Item item) {

        itemDao.updateItem(item);

    }

    @Transactional
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
        if (customerId == null){
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
            ivm.getInvoiceItemList().forEach(iItemVM -> {
                iItemVM.setInvoiceId(finalInvId);
                iItemVM.setInvoiceItemId(addInvoiceItemViewModel(iItemVM).getInvoiceItemId());
            });

            return ivm;
    }

    @Transactional
    public InvoiceViewModel findInvoiceViewModel(Integer ivmId) {

        Invoice invoice = invoiceDao.getInvoice(ivmId);

        return buildInvoiceViewModel(invoice) ;
    }

    @Transactional
    public List<InvoiceViewModel> findInvoiceViewModelByCustomerId(Integer customerId) {
        List<Invoice> invoices = invoiceDao.getAllInvoicesByCustomerId(customerId);
        if (invoices == null) return null;
        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();
        invoices.stream().forEach(invoice -> invoiceViewModels.add(buildInvoiceViewModel(invoice)));
        return invoiceViewModels;
    }

    @Transactional
    public void deleteInvoiceViewModelByCustomerId(Integer customerId) {
        List<Invoice> invoices = invoiceDao.getAllInvoicesByCustomerId(customerId);
        invoices.stream().forEach(invoice -> {
            List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemByInvoiceId(invoice.getInvoiceId());
            invoiceItems.stream().forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        });
    }

    @Transactional
    public List<InvoiceViewModel> findAllInvoiceViewModels() {

        List<Invoice> invoiceList=invoiceDao.getAllInvoices();

        List<InvoiceViewModel> ivmList=new ArrayList<>();

        for (Invoice invoice : invoiceList){

            InvoiceViewModel ivm=buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        return ivmList;
    }

    @Transactional
    public void deleteInvoiceViewModel(Integer ivmId) {
        // 1) delete Invoice Items where Invoice_Id = ivmId
        // 2) delete Invoice where invoice_id = ivmId
        //Remove the InvoiceItemViewModel
        List<InvoiceItem> iivmList = invoiceItemDao.getInvoiceItemByInvoiceId(ivmId);

        iivmList.forEach(invoiceItemViewModel ->
                invoiceItemDao.deleteInvoiceItem(invoiceItemViewModel.getInvoiceItemId()));


        //Remove the iivm

        invoiceDao.deleteInvoice(ivmId);

    }

    @Transactional
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

        invoices.forEach(in -> {
                    in.setCustomerId(cvm.getCustomerId());
                    in.setInvoiceId(invoiceDao.addInvoice(in).getInvoiceId());
                });

        return cvm;
    }

    @Transactional
    public CustomerViewModel findCustomerViewModel(Integer cvmId) {
        Customer customer = customerDao.getCustomer(cvmId);
        if (customer == null) return null;
        return buildCustomerViewModel(customer);
    }

    @Transactional
    public List<CustomerViewModel> findAllCustomerViewModels() {

        List<Customer> cList = customerDao.getAllCustomers();

        List<CustomerViewModel> custList = new ArrayList<>();

        for (Customer customer : cList) {
            CustomerViewModel cvm = buildCustomerViewModel(customer);
            custList.add(cvm);
        }

        return custList;
    }

    @Transactional
    private CustomerViewModel buildCustomerViewModel(Customer customer) {
        CustomerViewModel cvm = new CustomerViewModel();

        cvm.setCustomerId(customer.getCustomerId());
        cvm.setCompany(customer.getCompany());
        cvm.setEmail(customer.getEmail());
        cvm.setPhone(customer.getPhone());
        cvm.setFirstName(customer.getFirstName());
        cvm.setLastName(customer.getLastName());

        List<Invoice> invoices = invoiceDao.getAllInvoicesByCustomerId(customer.getCustomerId());
        cvm.setInvoiceList(invoices);

        return cvm;
    }

    @Transactional
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
        invoices.forEach(in ->
                {
                    in.setCustomerId(cvm.getCustomerId());

                    if (in.getInvoiceId() ==  null) {
                        Invoice temp = invoiceDao.addInvoice(in);
                        in.setInvoiceId(temp.getInvoiceId());
                    } else {
                        boolean exists = false;
                        for (Invoice invoice : existingInvoices) {
                            if (in.getInvoiceId() == invoice.getInvoiceId()) {
                                exists = true;
                            }
                        }
                        if (!exists) {
                            Invoice temp = invoiceDao.addInvoice(in);
                            in.setInvoiceId(temp.getInvoiceId());
                        }
                    }
                });
    }

    @Transactional
    public void deleteCustomerViewModel(Integer customerId) {
        List<Invoice> inList = invoiceDao.getAllInvoicesByCustomerId(customerId);

        if (inList != null) {
            inList.forEach(in -> invoiceDao.deleteInvoice(in.getInvoiceId()));
        }

        customerDao.deleteCustomer(customerId);
    }

    @Transactional
    public InvoiceItemViewModel addInvoiceItemViewModel(InvoiceItemViewModel iivm) {

        Integer itemId = iivm.getItem().getItemId();

        //If I don't have an item
        if (itemId == null) {
            Item temp = itemDao.addItem(iivm.getItem());
            iivm.getItem().setItemId(temp.getItemId());
        }
        else {
            Item i = itemDao.getItem(itemId);
            if (i == null) {
                Item temp = itemDao.addItem(iivm.getItem());
                iivm.getItem().setItemId(temp.getItemId());
            }
        }
            InvoiceItem ii = new InvoiceItem();
            ii.setInvoiceId(iivm.getInvoiceId());
            ii.setItemId(iivm.getItem().getItemId());
            ii.setQuantity(iivm.getQuantity());
            ii.setUnitRate(iivm.getUnitRate());
            ii.setDiscount(iivm.getDiscount());

            iivm.setInvoiceItemId(invoiceItemDao.addInvoiceItem(ii).getInvoiceItemId());

        return iivm;
    }

    public InvoiceItemViewModel findInvoiceItemViewModel(Integer iivmId) {
        InvoiceItem ii = invoiceItemDao.getInvoiceItem(iivmId);
        if (ii == null) return null;
        return buildInvoiceItemViewModel(ii);
    }

    public List<InvoiceItemViewModel> findAllInvoiceItemViewModels() {
        List<InvoiceItem> iiList = invoiceItemDao.getAllInvoiceItems();
        if (iiList == null || iiList.isEmpty()) return null;

        List<InvoiceItemViewModel> iivmList = new ArrayList<>();
        for (InvoiceItem invoiceItem : iiList) {
            InvoiceItemViewModel iivm = buildInvoiceItemViewModel(invoiceItem);
            iivmList.add(iivm);
        }
        return iivmList;
    }

    public List<InvoiceItemViewModel> findAllInvoiceItemViewModelByInvoiceId(Integer invoiceId) {
        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemByInvoiceId(invoiceId);
        if (invoiceItems == null || invoiceItems.isEmpty()) return null;

        List<InvoiceItemViewModel> iivmList = new ArrayList<>();
        invoiceItems.forEach(invoiceItem -> iivmList.add(buildInvoiceItemViewModel(invoiceItem)));

        return iivmList;
    }

    public void deleteInvoiceItemViewModel(Integer iivId) {

        invoiceItemDao.deleteInvoiceItem(iivId);

    }

    // Helper Methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        //make sure we have an invoice object; otherwise, return null...
        if (invoice == null) return null;

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
