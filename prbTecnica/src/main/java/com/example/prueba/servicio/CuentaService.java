package com.example.prueba.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.prueba.dto.CuentaDTO;
import com.example.prueba.entidad.Cuenta;
import com.example.prueba.excepciones.ResourceNotFoundException;
import com.example.prueba.repositorio.CuentaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<CuentaDTO> obtenerTodas() {
        return cuentaRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    public ResponseEntity<CuentaDTO> crearCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuentaRepository.save(cuenta);
        return ResponseEntity.ok(convertirADTO(cuenta));
    }

    public ResponseEntity<CuentaDTO> actualizarCuenta(Long numeroCuenta, CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuentaRepository.save(cuenta);
        return ResponseEntity.ok(convertirADTO(cuenta));
    }

    public ResponseEntity<Void> eliminarCuenta(Long numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        cuentaRepository.delete(cuenta);
        return ResponseEntity.noContent().build();
    }

    private CuentaDTO convertirADTO(Cuenta cuenta) {
        CuentaDTO dto = new CuentaDTO();
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        return dto;
    
    }
}

