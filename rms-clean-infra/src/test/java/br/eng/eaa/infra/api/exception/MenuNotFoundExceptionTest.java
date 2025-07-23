package br.eng.eaa.infra.api.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuNotFoundExceptionTest {

    @Test
    @DisplayName("Deve lançar MenuNotFoundException com mensagem correta")
    void shouldThrowMenuNotFoundExceptionWithCorrectMessage() {
        String expectedMessage = "Menu não encontrado";
        MenuNotFoundException exception = new MenuNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
        System.out.printf("Exceção lançada com mensagem: %s%n", exception.getMessage());
    }

}