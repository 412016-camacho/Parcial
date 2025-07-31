package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.common.ErrorApi;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler = new ControllerExceptionHandler();

    @Test
    void testGenericException() {
        Exception ex = new Exception("Error genérico");

        ResponseEntity<ErrorApi> response = handler.handleError(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error genérico", response.getBody().getMessage());
        assertEquals(500, response.getBody().getStatus());
    }

    @Test
    void testEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Usuario no encontrado");

        ResponseEntity<ErrorApi> response = handler.handleError(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatus());
        assertEquals("Not Found", response.getBody().getError());
    }

    @Test
    void testResponseStatusException() {
        ResponseStatusException ex = new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Datos inválidos"
        );

        ResponseEntity<ErrorApi> response = handler.handleError(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Datos inválidos", response.getBody().getMessage());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Bad Request", response.getBody().getError());
    }

    @Test
    void testErrorApiContainsTimestamp() {
        Exception ex = new Exception("Test");

        ResponseEntity<ErrorApi> response = handler.handleError(ex);

        assertNotNull(response.getBody().getTimestamp());
        assertFalse(response.getBody().getTimestamp().isEmpty());
    }

}