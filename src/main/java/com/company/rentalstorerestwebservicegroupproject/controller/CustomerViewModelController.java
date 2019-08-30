package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
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
    public String updateCustomers(@RequestBody @Valid List<CustomerViewModel> customerViewModels) {
        customerViewModels.stream().forEach(cvm -> sl.updateCustomerViewModel(cvm));
        return String.format("Successfully updated %s customers.", customerViewModels.size());

    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerViewModel> addCustomerList(@RequestBody @Valid List<CustomerViewModel> customerViewModels) {
        customerViewModels.stream().forEach(cvm -> sl.addCustomerViewModel(cvm));
        return customerViewModels;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerViewModel> getCustomerById(@PathVariable @Digits(integer = 11, fraction = 0) Integer id) {
        CustomerViewModel cvm = sl.findCustomerViewModel(id);
        List<CustomerViewModel> cvmList = new ArrayList<>();
        if (cvm == null) return cvmList;
        cvmList.add(cvm);
        return cvmList;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCustomerById(@PathVariable @Digits(integer = 11, fraction = 0) Integer id) {
        sl.deleteCustomerViewModel(id);
        return String.format("Successfully deleted Customer with id %s.", id);
    }
    


}
