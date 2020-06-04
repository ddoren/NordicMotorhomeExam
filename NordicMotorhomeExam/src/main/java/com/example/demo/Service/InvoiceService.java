package com.example.demo.Service;

import com.example.demo.Model.Invoice;
import com.example.demo.Repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepo invoiceRepo;

    public List<Invoice> fetchAll(){
        return invoiceRepo.fetchAll();
    }

    public void addInvoice(Invoice i){
        invoiceRepo.addInvoice(i);
    }

    public Invoice findInvoiceById(int id) {
        return invoiceRepo.findInvoiceById(id);
    }

    public void updatePrice(int price, int invoices_id) {
        invoiceRepo.updatePrice(price, invoices_id);
    }
}
