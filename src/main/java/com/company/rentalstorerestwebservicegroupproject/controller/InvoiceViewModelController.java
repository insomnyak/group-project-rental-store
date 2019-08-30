package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.util.List;

@RestController
@Validated
public class InvoiceViewModelController {

    @Autowired
    ServiceLayer sl;

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoicesVMs() {

        return sl.findAllInvoiceViewModels();
    };

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> addInvoicesVMs(@RequestBody @Valid List<InvoiceViewModel> invoiceViewModels) {

        invoiceViewModels.forEach(invoiceViewModel -> sl.addInvoiceViewModel(invoiceViewModel));
        return invoiceViewModels;
    };

    @RequestMapping(value = "/invoice/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoicesByCustomerVMs(@PathVariable @Digits(integer = 11, fraction = 0) Integer id ) {

        return sl.findInvoiceViewModelByCustomerId(id); //add the method
    };

    @RequestMapping(value = "/invoice/customer/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteInvoicesByCustom (@PathVariable @Digits(integer = 11, fraction = 0) Integer id) {

        sl.deleteInvoiceViewModelByCustomerId(id);
        return String.format("Successfully deleted Invoices for customer id %s.", id);
    }

    @RequestMapping(value = "invoice/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteInvoiceById(@PathVariable @Digits(integer = 11, fraction = 0) Integer id){

        sl.deleteInvoiceViewModel(id);
        return String.format("Successfully deleted Invoice id %s.", id);
    }
}
