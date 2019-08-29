package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerViewModelController {

    @Autowired
    ServiceLayer sl;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<CustomerViewModel> getAllCustomerVMs() {
        return sl.findAllCustomerViewModels();
    };

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public List<CustomerViewModel> updateCustomers(@RequestBody List<CustomerViewModel> customerViewModels) {
        customerViewModels.stream().forEach(cvm -> sl.updateCustomerViewModel(cvm));
        return null;


    }


}
