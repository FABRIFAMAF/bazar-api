package com.fabri.bazar.Service;

import com.fabri.bazar.Model.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getProductos();

    public void newProducto(Producto produc);

    public void deleteProducto(Long id);

    public Producto findProducto(Long id);

    public void editProducto(Long id, String nuevoNombre, String nuevaMarca, Double nuevoPrecio, Double nuevaCantidadDisponible);
}
