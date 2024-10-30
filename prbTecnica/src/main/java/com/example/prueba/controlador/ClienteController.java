package com.example.prueba.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.prueba.dto.ClienteDTO;
import com.example.prueba.servicio.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> obtenerClientes() {
        return clienteService.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.crearCliente(clienteDTO);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.actualizarCliente(clienteId, clienteDTO);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long clienteId) {
        return clienteService.eliminarCliente(clienteId);
    }
}
