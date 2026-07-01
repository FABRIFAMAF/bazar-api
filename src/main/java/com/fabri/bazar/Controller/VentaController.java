package com.fabri.bazar.Controller;

import com.fabri.bazar.Model.Cliente;
import com.fabri.bazar.Model.Producto;
import com.fabri.bazar.Model.Venta;
import com.fabri.bazar.Service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaServ;

    @GetMapping("/ventas/traer")
    public List<Venta> getVentas() {
        return ventaServ.getVentas();
    }

    @PostMapping("/venta/crear")
    public String saveVenta(Venta venta) {
        ventaServ.saveVenta(venta);
        return "La nueva venta fue creada correctamente";
    }

    @DeleteMapping("/venta/borrar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta) {
        ventaServ.deleteVenta(codigo_venta);
        return "La venta fue eliminada correctamente";
    }

    @PutMapping("/venta/editar/{codigo_venta}")
    public Venta editVenta(@PathVariable Long codigo_venta,
                           @RequestParam(required = false, name = "fecha_venta") LocalDate nuevaFecha,
                           @RequestParam(required = false, name = "total") Double nuevoTotal,
                           @RequestParam(required = false, name = "listaProductos") List<Producto> nuevaLista,
                           @RequestParam(required = false, name = "unCliente") Cliente nuevoCliente) {
        ventaServ.editVenta(codigo_venta, nuevaFecha, nuevoTotal, nuevaLista, nuevoCliente);

        Venta venta = ventaServ.findVenta(codigo_venta);

        return venta;
    }

    @PutMapping ("/venta/editar")
    public Venta editVenta(@RequestBody Venta venta) {
        ventaServ.editVenta(venta);

        return ventaServ.findVenta(venta.getCodigo_venta());
    }
}
