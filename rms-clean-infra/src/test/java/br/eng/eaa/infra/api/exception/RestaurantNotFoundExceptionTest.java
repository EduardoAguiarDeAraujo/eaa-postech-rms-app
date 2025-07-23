package br.eng.eaa.infra.api.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantNotFoundExceptionTest {

    @Test
    @DisplayName("Deve lançar RestaurantNotFoundException com mensagem correta")
    void shouldThrowRestaurantNotFoundExceptionWithCorrectMessage() {
        String expectedMessage = "Restaurante não encontrado";
        RestaurantNotFoundException exception = new RestaurantNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
        System.out.printf("Exceção lançada com mensagem: %s%n", exception.getMessage());
    }

}