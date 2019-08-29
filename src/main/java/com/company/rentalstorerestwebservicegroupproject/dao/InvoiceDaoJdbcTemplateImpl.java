package com.company.rentalstorerestwebservicegroupproject.dao;


import com.company.rentalstorerestwebservicegroupproject.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao{
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, order_date, pickup_date, return_date, late_fee) values (?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";

    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    private static final String SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL =
            "select * from invoice where customer_id = ?";


    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {

        jdbcTemplate.update(
                INSERT_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getOrderDate(),
                invoice.getPickupDate(),
                invoice.getReturnDate(),
                invoice.getLateFee());

        int invoiceId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoice.setInvoiceId(invoiceId);

        return invoice;
    }

    @Override
    public Invoice getInvoice(Integer invoiceId) {

        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoiceId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {

        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public void deleteInvoice(Integer invoiceId) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoicesByCustomerId(Integer customerId) {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customerId);
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId((rs.getInt("invoice_id")));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setOrderDate(LocalDate.parse(rs.getString("order_date")));
        invoice.setPickupDate(LocalDate.parse(rs.getString("pickup_date")));
        invoice.setReturnDate(LocalDate.parse(rs.getString("return_date")));
        invoice.setLateFee(rs.getDouble("late_fee"));

        return invoice;
    }
}
