package com.fabri.bazar.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double precio;
    private Double cantidad_disponible;

    public Producto() {
    }

    public Producto(Long codigo_producto, String nombre, String marca, Double precio, Double cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
    }


}
