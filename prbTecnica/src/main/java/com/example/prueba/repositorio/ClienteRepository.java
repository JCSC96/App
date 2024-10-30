package com.example.prueba.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba.entidad.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

