package org.example.db;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="orden_compra")
@Data
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="cliente_id")
    private Integer clienteId;

    @Column(name="producto_id")
    private Integer productoId;

    private BigDecimal precio;

}
