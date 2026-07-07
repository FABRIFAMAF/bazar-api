package com.fabri.bazar.Service;

import com.fabri.bazar.Model.DetalleVenta;
import com.fabri.bazar.DTO.MayorVentaDTO;
import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Model.Producto;
import com.fabri.bazar.Model.Venta;
import com.fabri.bazar.Repository.IProductoRepository;
import com.fabri.bazar.Repository.IVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IProductoRepository productoRepo;

    @Autowired
    private IVentaRepository ventaRepo;

    @Override
    public List<Venta> getVentas() {
        List<Venta> ventas = ventaRepo.findAll();
        return ventas;
    }

    @Override
    public List<DetalleVenta> getProductosByVenta(Long codigo_venta) {
        Venta venta = ventaRepo.findById(codigo_venta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        return venta.getListaDetalles();
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
    public MayorVentaDTO getMayorVenta() {

        Venta venta = ventaRepo.getMayorVenta();

        MayorVentaDTO dto = new MayorVentaDTO();

        int cantidadTotalProductos = 0;

        for (DetalleVenta detalle : venta.getListaDetalles()) {
            cantidadTotalProductos += detalle.getCantidad();
        }

        dto.setCodigo_venta(venta.getCodigo_venta());
        dto.setTotal(venta.getTotal());
        dto.setCantidadProductos(cantidadTotalProductos);

        dto.setNombreCliente(venta.getUnCliente().getNombre());
        dto.setApellidoCliente(venta.getUnCliente().getApellido());

        return dto;
    }

    @Transactional
    @Override
    public void saveVenta(Venta venta) {

            Double total = 0.0;

            for (DetalleVenta detalle : venta.getListaDetalles()) {

                Producto productoBD = productoRepo.findById(
                        detalle.getProducto().getCodigo_producto()
                ).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                if (productoBD.getCantidad_disponible() < detalle.getCantidad()) {
                    throw new RuntimeException(
                            "Stock insuficiente para el producto: "
                                    + productoBD.getNombre()
                    );
                }

                productoBD.setCantidad_disponible(
                        productoBD.getCantidad_disponible()
                                - detalle.getCantidad()
                );

                detalle.setVenta(venta);
                detalle.setProducto(productoBD);

                detalle.setPrecioUnitario(productoBD.getPrecio());

                detalle.setSubtotal(
                        productoBD.getPrecio() * detalle.getCantidad()
                );

                total += detalle.getSubtotal();

                productoRepo.save(productoBD);
            }

            venta.setTotal(total);

            ventaRepo.save(venta);
        }

    @Override
    public Venta findVenta(Long codigo_venta) {
        Venta venta= ventaRepo.findById(codigo_venta).orElse(null);
        return venta;
    }

    @Transactional
    @Override
    public void deleteVenta(Long codigo_venta) {

        Venta venta = ventaRepo.findById(codigo_venta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        for (DetalleVenta detalle : venta.getListaDetalles()) {

            Producto productoBD = productoRepo.findById(
                    detalle.getProducto().getCodigo_producto()
            ).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            productoBD.setCantidad_disponible(
                    productoBD.getCantidad_disponible()
                            + detalle.getCantidad()
            );

            productoRepo.save(productoBD);
        }

        ventaRepo.delete(venta);
    }

    @Override
    public Venta editVenta(Long codigo_venta, LocalDate nuevaFechaVenta, Double nuevoTotal, List<DetalleVenta> nuevaLista, Cliente nuevoCliente) {
        Venta venta= this.findVenta(codigo_venta);

        venta.setFecha_venta(nuevaFechaVenta);
        venta.setTotal(nuevoTotal);
        venta.setListaDetalles(nuevaLista);
        venta.setUnCliente(nuevoCliente);

        this.saveVenta(venta);

        return venta;
    }

    @Override
    public void editVenta(Venta venta) {
        this.saveVenta(venta);
    }
}
