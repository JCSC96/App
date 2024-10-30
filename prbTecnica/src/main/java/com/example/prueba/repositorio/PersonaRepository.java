package com.example.prueba.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba.entidad.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}