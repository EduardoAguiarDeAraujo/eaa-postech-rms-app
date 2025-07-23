package br.eng.eaa.infra.api.handler;

import br.eng.eaa.infra.api.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {


    @Test
    @DisplayName("Deve retornar uma instância de GlobalExceptionHandler")
    void shouldReturnInstanceOfGlobalExceptionHandler() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        assertNotNull(handler);
        System.out.println("Instância de GlobalExceptionHandler criada com sucesso.");
    }

    @Test
    @DisplayName("Deve retornar uma mensagem de erro para UserNotFoundException")
    void shouldReturnErrorMessageForUserNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Usuário não encontrado";
        UserNotFoundException exception = new UserNotFoundException(errorMessage);

        ResponseEntity<String> response = handler.handleUserNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma mensagem de erro para IllegalArgumentException")
    void shouldReturnErrorMessageForIllegalArgumentException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Argumento inválido";
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        ResponseEntity<String> response = handler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma mensagem de erro para RestaurantNotFoundException")
    void shouldReturnErrorMessageForRestaurantNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Restaurante não encontrado";
        br.eng.eaa.infra.api.exception.RestaurantNotFoundException exception = new br.eng.eaa.infra.api.exception.RestaurantNotFoundException(errorMessage);

        ResponseEntity<String> response = handler.handleRestaurantNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma mensagem de erro para MenuNotFoundException")
    void shouldReturnErrorMessageForMenuNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Menu não encontrado";
        br.eng.eaa.infra.api.exception.MenuNotFoundException exception = new br.eng.eaa.infra.api.exception.MenuNotFoundException(errorMessage);

        ResponseEntity<String> response = handler.handleMenuNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma mensagem de erro para DataIntegrityViolationException")
    void shouldReturnErrorMessageForDataIntegrityViolationException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Violação de integridade de dados";
        org.springframework.dao.DataIntegrityViolationException exception = new org.springframework.dao.DataIntegrityViolationException(errorMessage);

        ResponseEntity<String> response = handler.handleDataIntegrityViolationException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma mensagem de erro para RoleNotFoundException")
    void shouldReturnErrorMessageForRoleNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Função não encontrada";
        br.eng.eaa.infra.api.exception.RoleNotFoundException exception = new br.eng.eaa.infra.api.exception.RoleNotFoundException(errorMessage);

        ResponseEntity<String> response = handler.handleRoleNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }


}