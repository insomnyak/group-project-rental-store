package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.model.Item;

import java.util.List;

public interface ItemDao {

    Item addItem(Item item);

    Item getItem(Integer id);

    List<Item> getAllItems();

    void updateItem(Item item);

    void deleteItem(Integer id);
}
