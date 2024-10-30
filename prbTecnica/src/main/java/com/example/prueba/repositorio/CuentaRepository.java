package com.example.prueba.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.prueba.entidad.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
