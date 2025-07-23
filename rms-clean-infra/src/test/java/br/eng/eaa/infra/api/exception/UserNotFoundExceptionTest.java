package br.eng.eaa.infra.api.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserNotFoundExceptionTest {

    @Test
    @DisplayName("Deve lançar UserNotFoundException com mensagem correta")
    void shouldThrowUserNotFoundExceptionWithCorrectMessage() {
        String expectedMessage = "Usuário não encontrado";
        UserNotFoundException exception = new UserNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
        System.out.printf("Exceção lançada com mensagem: %s%n", exception.getMessage());
    }

}