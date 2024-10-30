package com.example.prueba.servicio;

import org.springframework.stereotype.Service;

import com.example.prueba.dto.ClienteDTO;
import com.example.prueba.entidad.Cliente;
import com.example.prueba.excepciones.ResourceNotFoundException;
import com.example.prueba.repositorio.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obtenerTodos() {
        return clienteRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public ResponseEntity<ClienteDTO> crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId()); 
        cliente.setContraseña(clienteDTO.getContraseña());
        cliente.setEstado(clienteDTO.getEstado());
        clienteRepository.save(cliente);
        return ResponseEntity.ok(convertirADTO(cliente));
    }

    public ResponseEntity<ClienteDTO> actualizarCliente(Long clienteId, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        
       
        cliente.setId(clienteDTO.getId());
        cliente.setContraseña(clienteDTO.getContraseña());
        cliente.setEstado(clienteDTO.getEstado());
        clienteRepository.save(cliente);
        return ResponseEntity.ok(convertirADTO(cliente));
    }

    public ResponseEntity<Void> eliminarCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getClienteId());
        dto.setContraseña(cliente.getContraseña());
        dto.setEstado(cliente.getEstado());
        return dto;
    }
}