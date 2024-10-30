package com.example.prueba.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.prueba.dto.MovimientoDTO;
import com.example.prueba.entidad.Movimiento;
import com.example.prueba.entidad.Cuenta;
import com.example.prueba.repositorio.MovimientoRepository;
import com.example.prueba.repositorio.CuentaRepository;
import com.example.prueba.excepciones.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<MovimientoDTO> obtenerMovimientosPorCuenta(Long numeroCuenta) {
        List<Movimiento> movimientos = movimientoRepository.findByCuentaNumeroCuenta(numeroCuenta);
        return movimientos.stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    public ResponseEntity<MovimientoDTO> crearMovimiento(Long numeroCuenta, MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setSaldo(movimientoDTO.getSaldo());
        movimiento.setCuenta(cuenta);
        movimientoRepository.save(movimiento);
        return ResponseEntity.ok(convertirADTO(movimiento));
    }

    private MovimientoDTO convertirADTO(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setId(movimiento.getId());
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setValor(movimiento.getValor());
        dto.setSaldo(movimiento.getSaldo());
        dto.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        return dto;
    }
}
