package com.fabri.bazar.Repository;

import com.fabri.bazar.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {
}
