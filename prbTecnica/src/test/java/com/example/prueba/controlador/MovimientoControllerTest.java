package com.example.prueba.controlador;

import com.example.prueba.dto.MovimientoDTO;
import com.example.prueba.servicio.MovimientoService;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovimientoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private MovimientoService movimientoService;

    @InjectMocks
    private MovimientoController movimientoController;

    @Test
    public void testObtenerMovimientosPorCuenta() {
        MovimientoDTO movimiento1 = new MovimientoDTO();
        movimiento1.setId(1L);
        movimiento1.setFecha(new Date());
        movimiento1.setTipoMovimiento("Deposito");
        movimiento1.setValor(500.00);
        movimiento1.setSaldo(1500.00);
        movimiento1.setNumeroCuenta(1L);

        MovimientoDTO movimiento2 = new MovimientoDTO();
        movimiento2.setId(2L);
        movimiento2.setFecha(new Date());
        movimiento2.setTipoMovimiento("Retiro");
        movimiento2.setValor(-200.00);
        movimiento2.setSaldo(1300.00);
        movimiento2.setNumeroCuenta(1L);

        List<MovimientoDTO> movimientos = Arrays.asList(movimiento1, movimiento2);

        when(movimientoService.obtenerMovimientosPorCuenta(1L)).thenReturn(movimientos);

        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/api/movimientos/cuenta/1", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testCrearMovimiento() {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(3L);
        movimientoDTO.setFecha(new Date());
        movimientoDTO.setTipoMovimiento("Deposito");
        movimientoDTO.setValor(300.00);
        movimientoDTO.setSaldo(1600.00);
        movimientoDTO.setNumeroCuenta(1L);

        when(movimientoService.crearMovimiento(eq(1L), any(MovimientoDTO.class))).thenReturn(ResponseEntity.ok(movimientoDTO));

        ResponseEntity<MovimientoDTO> response = restTemplate.postForEntity("http://localhost:" + port + "/api/movimientos/cuenta/1", movimientoDTO, MovimientoDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(movimientoDTO.getId(), response.getBody().getId());
    }
}
