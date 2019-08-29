package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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
    public InvoiceViewModel addInvoicesVMs(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {

        return sl.addInvoiceViewModel(invoiceViewModel);
    };

    @RequestMapping(value = "/invoice/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoicesByCustomerVMs(@PathVariable Integer id ) {

        return sl.findInvoiceViewModelByCustomerId(id); //add the method
    };

    @RequestMapping(value = "/invoice/customer/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvocesByCustom (@PathVariable Integer id){

        sl.deleteInvoiceViewModelByCustomerId(id); // add method
    }

    @RequestMapping(value = "invoice/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoiceById(@PathVariable Integer id){

        sl.deleteInvoiceViewModel(id);
    }
}
