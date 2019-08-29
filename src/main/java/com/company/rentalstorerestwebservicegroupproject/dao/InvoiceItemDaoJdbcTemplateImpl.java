package com.company.rentalstorerestwebservicegroupproject.dao;

import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id, item_id, quantity, unit_rate, discount) values (?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "select * from invoice_item";

    private static final String UPDATE_INVOICE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, item_id = ?, quantity = ?, unit_rate = ?, discount = ? where " +
                    "invoice_item_id = ?";

    private static final String DELETE_INVOICE_ITEM =
            "delete from invoice_item where invoice_item_id = ?";


    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceitem) {

        jdbcTemplate.update(
                INSERT_INVOICE_ITEM_SQL,
                invoiceitem.getInvoiceId(),
                invoiceitem.getItemId(),
                invoiceitem.getQuantity(),
                invoiceitem.getUnitRate(),
                invoiceitem.getDiscount());

        int invoiceItemId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoiceitem.setInvoiceItemId(invoiceItemId);

        return invoiceitem;
    }

    @Override
    public InvoiceItem getInvoiceItem(Integer invoiceItemId) {

        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, invoiceItemId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {

        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceitem) {

        jdbcTemplate.update(
                UPDATE_INVOICE_ITEM_SQL,
                invoiceitem.getInvoiceId(),
                invoiceitem.getItemId(),
                invoiceitem.getQuantity(),
                invoiceitem.getUnitRate(),
                invoiceitem.getDiscount(),
                invoiceitem.getInvoiceItemId());

    }

    @Override
    public void deleteInvoiceItem(Integer invoiceItemId) {

        jdbcTemplate.update(DELETE_INVOICE_ITEM, invoiceItemId);

    }

    @Override
    public List<InvoiceItem> getInvoiceItemByInvoiceId(Integer invoiceId) {
        return null;
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceitem = new InvoiceItem();
        invoiceitem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceitem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceitem.setItemId(rs.getInt("item_id"));
        invoiceitem.setQuantity(rs.getInt("quantity"));
        invoiceitem.setUnitRate(rs.getDouble("unit_rate"));
        invoiceitem.setDiscount(rs.getDouble("discount"));

        return invoiceitem;
    }


}