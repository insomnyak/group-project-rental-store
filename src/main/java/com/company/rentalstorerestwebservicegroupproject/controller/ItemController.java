package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.model.Item;
import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.CustomerViewModel;
import com.company.rentalstorerestwebservicegroupproject.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ServiceLayer sl;

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItemsV() {

        return sl.findAllItem();
    };

    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public Item updateItems(@RequestBody Item item) {

        sl.updateItem(item);

        return sl.findItem(item.getItem_id());
    };

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItems(@RequestBody Item item ) {

        return sl.addItem(item);
    };

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItemById(@PathVariable Integer id) {

        return sl.findItem(id);
    };

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteItemById(@PathVariable Integer id) {

        sl.deleteItem(id);

        return "successfully deleted";
    }

}
