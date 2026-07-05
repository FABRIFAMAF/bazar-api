package com.fabri.bazar.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MayorVentaDTO {
    private Long codigo_venta;
    private Double total;
    private Integer cantidadProductos;
    private String nombreCliente;
    private String apellidoCliente;
}
