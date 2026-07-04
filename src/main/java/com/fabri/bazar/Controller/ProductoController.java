package com.fabri.bazar.Controller;

import com.fabri.bazar.Model.Producto;
import com.fabri.bazar.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService producServ;

    @GetMapping("/productos/traer")
    public List<Producto> getProductos(){
        return producServ.getProductos();
    }

    @GetMapping("/productos/{id}")
    public Producto findProducto(Long id){
        Producto produc = this.producServ.findProducto(id);
        return produc;
    }

    @GetMapping("/productos/falta_stock")
    public List<Producto> findProductosPocoStock(){
        List<Producto> pocoStock = producServ.obtenerProductosConStockMenorA(5.0);
        return pocoStock;
    }

    @PostMapping("/producto/crear")
    public String newProducto(Producto produc){
        producServ.newProducto(produc);
        return "El nuevo producto fue creado correctamente";
    }

    @DeleteMapping("/producto/borrar/{id}")
    public String deleteProducto(@PathVariable Long id){
        producServ.deleteProducto(id);
        return "El producto fue eliminado correctamente";
    }

    @PutMapping("/producto/editar/{id}")
    public Producto editProducto(@PathVariable Long id,
                                 @RequestParam(required=false, name="nombre") String nuevoNombre,
                                 @RequestParam (required=false, name="marca") String nuevaMarca,
                                 @RequestParam (required=false, name="precio") Double nuevoPrecio,
                                 @RequestParam (required=false, name="cantidadDisponible") Double nuevaCantidadDisponible){
        producServ.editProducto(id, nuevoNombre, nuevaMarca, nuevoPrecio, nuevaCantidadDisponible);

        Producto produc= producServ.findProducto(id);

        return produc;

    }
}
