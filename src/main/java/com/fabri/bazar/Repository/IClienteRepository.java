package com.fabri.bazar.Repository;

import com.fabri.bazar.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
