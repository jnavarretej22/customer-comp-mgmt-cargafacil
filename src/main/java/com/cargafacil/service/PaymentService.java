package com.cargafacil.service;

import com.cargafacil.dto.Payment;
import com.cargafacil.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(existingPayment -> {
            existingPayment.setFormaPago(updatedPayment.getFormaPago());
            existingPayment.setSubtotal(updatedPayment.getSubtotal());
            existingPayment.setImpuestos(updatedPayment.getImpuestos());
            existingPayment.setDescuento(updatedPayment.getDescuento());
            existingPayment.setTotal(updatedPayment.getTotal());
            existingPayment.setEstadoPago(updatedPayment.getEstadoPago());
            return paymentRepository.save(existingPayment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with ID " + id));
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
