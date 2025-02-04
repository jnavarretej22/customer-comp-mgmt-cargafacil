package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    private Trip trip; // Relación con la entidad Trip (Viaje)

    @Column(name = "forma_pago", nullable = false)
    private String formaPago; // Tarjeta, Efectivo, Transferencia

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "impuestos", nullable = false)
    private BigDecimal impuestos;

    @Column(name = "descuento", nullable = false)
    private BigDecimal descuento;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "estado_pago", nullable = false)
    private String estadoPago; // Pendiente, Completado

    @Column(name = "fecha_pago", nullable = false, updatable = false)
    private LocalDateTime fechaPago;
}
