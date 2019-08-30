package com.company.rentalstorerestwebservicegroupproject.controller;

import com.company.rentalstorerestwebservicegroupproject.model.Item;
import com.company.rentalstorerestwebservicegroupproject.servicelayer.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.util.List;

@RestController
@Validated
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
    public String updateItems(@RequestBody @Valid List<Item> items) {
        items.forEach(item -> sl.updateItem(item));
        return String.format("Successfully updated %s items", items.size());
    };

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> addItems(@RequestBody @Valid List<Item> items) {

        items.forEach(item -> sl.addItem(item));
        return items;
    };

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItemById(@PathVariable @Digits(integer = 11, fraction = 0) Integer id) {

        return sl.findItem(id);
    };

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteItemById(@PathVariable @Digits(integer = 11, fraction = 0) Integer id) {

        sl.deleteItem(id);

        return String.format("Successfully deleted Item id %s.", id);
    }

}
