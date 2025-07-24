package br.eng.eaa.domain.valueobject;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    @DisplayName("Deve retornar um password válido")
    void shouldRetornValidPassword(){

        Password password = new Password("MinhaSenhaForte10#");
        assertNotNull(password);
        assertEquals("MinhaSenhaForte10#", password.getValue());
        assertEquals("MinhaSenhaForte10#", password.toString());
        System.out.printf("Password válida: %s %n", password.getValue());
    }

    @ParameterizedTest
    @NullAndEmptySource // Cobre casos com senha = null e senha = ""
    @ValueSource(strings = {
            " ",              // Senha em branco
            "Fraca1@",        // Menor que 8 caracteres
            "minhasenhafraca1@", // Sem UpperCase
            "SENHAFRACA1@",   // Sem LowerCase
            "MinhaSenhaForte#", // Sem número
            "MinhaSenhaForte10" // Sem caracter especial
    })
    @DisplayName("Deve retornar exceção para senhas inválidas (nula, vazia, em branco, sem requisitos)")
    void shouldReturnExceptionForInvalidPasswords(String invalidPassword) {
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: '%s'%n", invalidPassword != null ? invalidPassword : "null");
    }

}