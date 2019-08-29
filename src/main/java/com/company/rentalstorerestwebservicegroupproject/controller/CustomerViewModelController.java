package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerViewModelController {

    @Autowired
    ServiceLayer sl;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerViewModel> getAllCustomerVMs() {
        return sl.findAllCustomerViewModels();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public String updateCustomers(@RequestBody List<CustomerViewModel> customerViewModels) {
        customerViewModels.stream().forEach(cvm -> sl.updateCustomerViewModel(cvm));
        return "something happened in the backend that may have updated the DB.";

    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerViewModel> addCustomerList(@RequestBody @Valid List<CustomerViewModel> customerViewModels) {
        customerViewModels.stream().forEach(cvm -> sl.addCustomerViewModel(cvm));
        return customerViewModels;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerViewModel> getCustomerById(@PathVariable Integer id) {
        CustomerViewModel cvm = sl.findCustomerViewModel(id);
        List<CustomerViewModel> cvmList = new ArrayList<>();
        cvmList.add(cvm);
        return cvmList;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCustomerById(@PathVariable Integer id) {
        sl.deleteCustomerViewModel(id);
        return "Successfully deleted";
    }
    


}
