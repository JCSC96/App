package com.example.prueba.controlador;

import com.example.prueba.dto.CuentaDTO;
import com.example.prueba.servicio.CuentaService;
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
public class CuentaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerCuentas() {
        CuentaDTO cuenta1 = new CuentaDTO();
        cuenta1.setNumeroCuenta(1L);
        cuenta1.setTipoCuenta("Ahorro");
        cuenta1.setSaldoInicial(2000.00);
        cuenta1.setEstado("True");

        CuentaDTO cuenta2 = new CuentaDTO();
        cuenta2.setNumeroCuenta(2L);
        cuenta2.setTipoCuenta("Corriente");
        cuenta2.setSaldoInicial(1000.00);
        cuenta2.setEstado("True");

        List<CuentaDTO> cuentas = Arrays.asList(cuenta1, cuenta2);

        when(cuentaService.obtenerTodas()).thenReturn(cuentas);

        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/api/cuentas", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testCrearCuenta() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(3L);
        cuentaDTO.setTipoCuenta("Ahorro");
        cuentaDTO.setSaldoInicial(1500.00);
        cuentaDTO.setEstado("True");

        when(cuentaService.crearCuenta(any(CuentaDTO.class))).thenReturn(ResponseEntity.ok(cuentaDTO));

        ResponseEntity<CuentaDTO> response = restTemplate.postForEntity("http://localhost:" + port + "/api/cuentas", cuentaDTO, CuentaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cuentaDTO.getNumeroCuenta(), response.getBody().getNumeroCuenta());
    }
}
