package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.Model.Item;

import java.util.List;

public interface ItemDao {

    Item addItem(Item item);

    Item getItem(int id);

    List<Item> getAllItems();

    void updateItem(Item item);

    void deleteItem(int id);
}
