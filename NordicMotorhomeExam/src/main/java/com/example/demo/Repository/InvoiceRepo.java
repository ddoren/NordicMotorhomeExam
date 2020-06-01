package com.example.demo.Repository;

import com.example.demo.Model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceRepo {

    @Autowired
    JdbcTemplate template;

    public List<Invoice> fetchAll(){
        String sql = "SELECT *\n" +
                "FROM invoices i\n" +
                "Left JOIN  reservations r ON i.invoice_reservation = r.res_id\n" +
                "JOIN customers custs ON r.res_customer = custs.cus_id\n" +
                "JOIN motorhomes m ON r.res_motorhome = m.motor_id \n" +
                "JOIN carmodel c ON m.motor_model = c.model_id\n" +
                "LEFT JOIN extras_in_reservations er ON er.reservation_id = r.res_id\n" +
                "LEFT JOIN extras e ON er.extra_id = e.extra_id\n" +
                "GROUP BY res_id;\n";
        RowMapper<Invoice> rowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        return template.query(sql, rowMapper);
    }

    public void addInvoice(Invoice i){
        String sql = "INSERT INTO invoices VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
        template.update(sql, i.getRes_id(), i.getService(), i.getAddit_expenses(), i.getAddit_exp_descript(), i.getCanceled(), i.getTotal_price());
    }

    public Invoice findInvoiceById(int id) {
        String sql = "SELECT *\n" +
                "FROM invoices i\n" +
                "Left JOIN  reservations r ON i.invoice_reservation = r.res_id\n" +
                "JOIN customers custs ON r.res_customer = custs.cus_id\n" +
                "JOIN motorhomes m ON r.res_motorhome = m.motor_id \n" +
                "JOIN carmodel c ON m.motor_model = c.model_id\n" +
                "LEFT JOIN extras_in_reservations er ON er.reservation_id = r.res_id\n" +
                "LEFT JOIN extras e ON er.extra_id = e.extra_id\n" +
                "WHERE i.invoices_id = ? \n" +
                "GROUP BY res_id;";

        RowMapper<Invoice> rowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        Invoice invoice = template.queryForObject(sql, rowMapper, id);
        return invoice;
    }
}
