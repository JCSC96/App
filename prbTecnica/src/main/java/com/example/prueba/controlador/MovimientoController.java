package com.example.prueba.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.prueba.dto.MovimientoDTO;
import com.example.prueba.servicio.MovimientoService;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/cuenta/{numeroCuenta}")
    public List<MovimientoDTO> obtenerMovimientosPorCuenta(@PathVariable Long numeroCuenta) {
        return movimientoService.obtenerMovimientosPorCuenta(numeroCuenta);
    }

    @PostMapping("/cuenta/{numeroCuenta}")
    public ResponseEntity<MovimientoDTO> crearMovimiento(@PathVariable Long numeroCuenta, @RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.crearMovimiento(numeroCuenta, movimientoDTO);
    }
}
