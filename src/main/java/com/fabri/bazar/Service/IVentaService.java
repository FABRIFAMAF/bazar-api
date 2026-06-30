package com.fabri.bazar.Service;

import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Model.Producto;
import com.fabri.bazar.Model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public List<Venta> getVentas();

    public void saveVenta(Venta venta);

    public Venta findVenta(Long codigo_venta);

    public void deleteVenta(Long codigo_venta);

    public Venta editVenta(Long codigo_venta, LocalDate nuevaFechaVenta, Double nuevoTotal, List<Producto> nuevaListaProductos, Cliente nuevoCliente);


}
