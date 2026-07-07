package com.fabri.bazar.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_detalle;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "codigo_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "codigo_producto")
    private Producto producto;

    private Integer cantidad;

    private Double precioUnitario;

    private Double subtotal;
}
