package br.eng.eaa.domain.valueobject;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Deve retornar exception para senha menor que 8 caracteres")
    void shouldReturnExceptionForLessThan8(){
        String invalidPassword = "Fraca1@";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha sem UpperCase")
    void shouldReturnExceptionForNoUpperCase(){
        String invalidPassword = "minhasenhafraca1@";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha sem LowerCase")
    void shouldReturnExceptionForNoLowerCase(){
        String invalidPassword = "SENHAFRACA1@";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha vazia")
    void shouldReturnExceptionForEmpty(){
        String invalidPassword = "";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha null")
    void shouldReturnExceptionForNully(){
        String invalidPassword = null;
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha em branco")
    void shouldReturnExceptionForBlank(){
        String invalidPassword = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha sem número")
    void shouldReturnExceptionForNoNumbers(){
        String invalidPassword = "MinhaSenhaForte#";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }

    @Test
    @DisplayName("Deve retornar exception para senha sem caracter especial")
    void shouldReturnExceptionForNoSpecialCaracter(){
        String invalidPassword = "MinhaSenhaForte10";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
        System.out.printf("Password inválida: %s %n", invalidPassword);
    }


}