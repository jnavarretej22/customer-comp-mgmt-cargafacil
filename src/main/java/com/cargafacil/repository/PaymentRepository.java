package com.cargafacil.repository;

import com.cargafacil.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Opcional: Métodos personalizados si los necesitas
}
