package com.fabri.bazar.Repository;

import com.fabri.bazar.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Long> {

    @Query(
            value = """
                SELECT *
                FROM venta
                WHERE fecha_venta = :fecha
                """,
            nativeQuery = true
    )
    List<Venta> findVentasByFecha(
            @Param("fecha") LocalDate fecha);

    @Query(
            value = """
            SELECT *
            FROM venta
            ORDER BY total DESC
            LIMIT 1
            """,
            nativeQuery = true
    )
    Venta getMayorVenta();

}
