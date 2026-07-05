package com.fabri.bazar.Service;

import com.fabri.bazar.DTO.MayorVentaDTO;
import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Model.Producto;
import com.fabri.bazar.Model.Venta;
import com.fabri.bazar.Repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;

    @Override
    public List<Venta> getVentas() {
        List<Venta> ventas = ventaRepo.findAll();
        return ventas;
    }

    @Override
    public List<Producto> getProductosByVenta(Long codigo_venta) {
        Venta venta = ventaRepo.findById(codigo_venta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        return venta.getListaProductos();
    }

    @Override
    public String getResumenVentasPorFecha(LocalDate fecha) {
        List<Venta> ventas = ventaRepo.findVentasByFecha(fecha);

        Double sumatoria = 0.0;

        for (Venta venta : ventas) {
            sumatoria += venta.getTotal();
        }

        int cantidadVentas = ventas.size();

        return "Total vendido: " + sumatoria
                + " | Cantidad de ventas: " + cantidadVentas;
    }

    @Override
    public MayorVentaDTO getMayorVenta() {Venta venta = ventaRepo.getMayorVenta();

        MayorVentaDTO dto = new MayorVentaDTO();

        dto.setCodigo_venta(venta.getCodigo_venta());
        dto.setTotal(venta.getTotal());
        dto.setCantidadProductos(
                venta.getListaProductos().size());

        dto.setNombreCliente(
                venta.getUnCliente().getNombre());

        dto.setApellidoCliente(
                venta.getUnCliente().getApellido());

        return dto;
    }

    @Override
    public void saveVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        Venta venta= ventaRepo.findById(codigo_venta).orElse(null);
        return venta;
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public Venta editVenta(Long codigo_venta, LocalDate nuevaFechaVenta, Double nuevoTotal, List<Producto> nuevaListaProductos, Cliente nuevoCliente) {
        Venta venta= this.findVenta(codigo_venta);

        venta.setFecha_venta(nuevaFechaVenta);
        venta.setTotal(nuevoTotal);
        venta.setListaProductos(nuevaListaProductos);
        venta.setUnCliente(nuevoCliente);

        this.saveVenta(venta);

        return venta;
    }

    @Override
    public void editVenta(Venta venta) {
        this.saveVenta(venta);
    }
}
