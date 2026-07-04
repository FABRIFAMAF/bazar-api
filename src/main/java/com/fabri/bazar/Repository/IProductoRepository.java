package com.fabri.bazar.Repository;

import com.fabri.bazar.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductoRepository extends JpaRepository <Producto, Long>{

    @Query(
            value = """
            SELECT *
            FROM producto
            WHERE cantidad_disponible < :limite
            """,
            nativeQuery = true
    )
    List<Producto> obtenerProductosConStockMenorA(
            @Param("limite") Double limite);
}
