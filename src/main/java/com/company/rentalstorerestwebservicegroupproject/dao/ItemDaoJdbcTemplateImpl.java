package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao{

    private static final String INSERT_ITEM_SQL =
            "insert into item (name, description, daily_rate) values (?, ?, ?)";

    private static final String SELECT_ITEM_SQL =
            "select * from item where item_id = ?";

    private static final String SELECT_ALL_ITEMS_SQL =
            "select * from item";

    private static final String UPDATE_ITEM_SQL =
            "update item set name = ?, description = ?, daily_rate = ? where item_id = ?";

    private static final String DELETE_ITEM_SQL =
            "delete from item where item_id = ?";

    @Override
    @Transactional
    public Item addItem(Item item) {

        return null;
    }

    @Override
    public Item getItem(int id) {

        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public void updateItem(Item item) {
    }

    @Override
    public void deleteItem(int id) {
    }

}
