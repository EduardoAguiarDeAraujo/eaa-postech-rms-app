package br.eng.eaa.domain.valueobject;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @BeforeEach
    void setUp() {
        this.street = "Av. Paulista, 1106";
        this.city = "São Paulo";
        this.state = "SP";
        this.zipCode = "01311-000";
    }

    @Test
    @DisplayName("Deve criar um endereço válido")
    void shouldCreateValidAddress(){
        Address address = new Address(street,city,state, zipCode);

        assertNotNull(address);
        assertEquals("Av. Paulista, 1106", address.getStreet());
        assertEquals("São Paulo", address.getCity());
        assertEquals("SP", address.getState());
        assertEquals("01311-000", address.getZipCode());

        System.out.printf("Endereço válido: Street: %s, City: %s, State: %s, ZipCode: %s %n", address.getStreet(), address.getCity(), address.getState(), address.getZipCode());
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllNullParameters(){
        String invalidStreet = null;
        String invalidCity = null;
        String invalidState = null;
        String invalidZipCode = null;
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidStreet, invalidCity, invalidState, invalidZipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters(){
        String invalidStreet = "";
        String invalidCity = "";
        String invalidState = "";
        String invalidZipCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidStreet, invalidCity, invalidState, invalidZipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllBlankParameters(){
        String invalidStreet = " ";
        String invalidCity = " ";
        String invalidState = " ";
        String invalidZipCode = " ";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidStreet, invalidCity, invalidState, invalidZipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    @DisplayName("Deve retornar uma exception para state null, empty, blank")
    void shouldReturnExceptionForZipCodeNull(String invalidZipCode){
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, invalidZipCode));
        System.out.printf("Endereço inválido: zipcode null, blank and empty %n");
    }

    @ParameterizedTest
    @NullAndEmptySource // Fornece 'null' e "" (string vazia)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Fornece strings em branco (espaços, tabs, quebras de linha)
    @DisplayName("Deve retornar exceção quando a rua é nula, vazia ou em branco")
    void shouldReturnExceptionForInvalidStreet(String invalidStreet) {
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidStreet, city, state, zipCode));
        System.out.printf("Endereço inválido - Rua: '%s'%n", invalidStreet != null ? invalidStreet : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when the city is null, empty, or blank")
    void shouldReturnExceptionForInvalidCity(String invalidCity) {
        assertThrows(IllegalArgumentException.class, () -> new Address(street, invalidCity, state, zipCode));
        System.out.printf("Invalid address - City: '%s'%n", invalidCity != null ? invalidCity : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when the state is null, empty, or blank")
    void shouldReturnExceptionForInvalidState(String invalidState) {
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, invalidState, zipCode));
        System.out.printf("Invalid address - State: '%s'%n", invalidState != null ? invalidState : "null");
    }

}