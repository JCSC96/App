package com.example.prueba.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.prueba.dto.CuentaDTO;
import com.example.prueba.servicio.CuentaService;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<CuentaDTO> obtenerCuentas() {
        return cuentaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.crearCuenta(cuentaDTO);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@PathVariable Long numeroCuenta, @RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.actualizarCuenta(numeroCuenta, cuentaDTO);
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long numeroCuenta) {
        return cuentaService.eliminarCuenta(numeroCuenta);
    }
}
