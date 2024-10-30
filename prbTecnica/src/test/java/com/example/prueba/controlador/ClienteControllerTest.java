package com.example.prueba.controlador;

import com.example.prueba.dto.ClienteDTO;
import com.example.prueba.servicio.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerClientes() {
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setId(1L);
        cliente1.setContraseña("1234");
        cliente1.setEstado("true");

        ClienteDTO cliente2 = new ClienteDTO();
        cliente2.setId(2L);
        cliente2.setContraseña("5678");
        cliente2.setEstado("true");

        List<ClienteDTO> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteService.obtenerTodos()).thenReturn(clientes);

        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/api/clientes", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testCrearCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(3L);
        clienteDTO.setContraseña("abcd");
        clienteDTO.setEstado("true");

        when(clienteService.crearCliente(any(ClienteDTO.class))).thenReturn(ResponseEntity.ok(clienteDTO));

        ResponseEntity<ClienteDTO> response = restTemplate.postForEntity("http://localhost:" + port + "/api/clientes", clienteDTO, ClienteDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(clienteDTO.getId(), response.getBody().getId());
    }
}
