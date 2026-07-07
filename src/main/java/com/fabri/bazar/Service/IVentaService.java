package com.fabri.bazar.Service;

import com.fabri.bazar.Model.DetalleVenta;
import com.fabri.bazar.DTO.MayorVentaDTO;
import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public List<Venta> getVentas();

    public List<DetalleVenta> getProductosByVenta(Long codigo_venta);

    public String getResumenVentasPorFecha(LocalDate fecha);

    public MayorVentaDTO getMayorVenta();

    public void saveVenta(Venta venta);

    public Venta findVenta(Long codigo_venta);

    public void deleteVenta(Long codigo_venta);

    public Venta editVenta(Long codigo_venta, LocalDate nuevaFechaVenta, Double nuevoTotal, List<DetalleVenta> nuevaLista, Cliente nuevoCliente);

    public void editVenta(Venta venta);



}
