package com.fabri.bazar.Service;

import com.fabri.bazar.Model.Producto;
import com.fabri.bazar.Repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public List<Producto> getProductos() {
        List <Producto> productos = productoRepo.findAll();
        return productos;
    }

    @Override
    public void newProducto(Producto produc) {

        productoRepo.save(produc);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public Producto findProducto(Long id) {
        Producto produc= productoRepo.findById(id).orElse(null);
        return produc;
    }

    @Override
    public void editProducto(Long id, String nuevoNombre, String nuevaMarca, Double nuevoPrecio, Double nuevaCantidadDisponible) {

        Producto produc = this.findProducto(id);

        produc.setNombre(nuevoNombre);
        produc.setMarca(nuevaMarca);
        produc.setPrecio(nuevoPrecio);
        produc.setCantidad_disponible(nuevaCantidadDisponible);

        this.newProducto(produc);
    }


}
