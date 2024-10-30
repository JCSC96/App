package com.example.prueba.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.prueba.entidad.Movimiento;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaNumeroCuenta(Long numeroCuenta);
}
