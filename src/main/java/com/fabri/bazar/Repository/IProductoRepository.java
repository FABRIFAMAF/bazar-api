package com.fabri.bazar.Repository;

import com.fabri.bazar.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository <Producto, Long>{
}
