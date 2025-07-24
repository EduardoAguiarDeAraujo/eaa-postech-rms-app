package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressEntityTest {

    private UUID id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        street = "Av. Paulista, 1106";
        city = "São Paulo";
        state = "SP";
        zipCode = "01311-000";
    }

    @Test
    @DisplayName("Deve retornar um endereço válido")
    void shouldReturnValidAddressEntity() {
        AddressEntity address = new AddressEntity(street, city, state, zipCode);

        assertNotNull(address);
        assertEquals(street, address.getStreet());
        assertEquals(city, address.getCity());
        assertEquals(state, address.getState());
        assertEquals(zipCode, address.getZipCode());

        System.out.printf("Endereço válido - Id: %s - %s %n", address.getId(), address.getStreet());
    }

    @Test
    @DisplayName("Deve retornar um endereço válido com ID")
    void shouldReturnValidAddressEntityWithId() {
        AddressEntity address = new AddressEntity(street, city, state, zipCode);
        address.setId(id);

        assertNotNull(address);
        assertEquals(id, address.getId());
        assertEquals(street, address.getStreet());
        assertEquals(city, address.getCity());
        assertEquals(state, address.getState());
        assertEquals(zipCode, address.getZipCode());

        System.out.printf("Endereço válido com ID - Id: %s - %s %n", address.getId(), address.getStreet());
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when street is null, empty, or blank")
    void shouldReturnExceptionForInvalidStreet(String invalidStreet) {
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(invalidStreet, city, state, zipCode));
        System.out.printf("Invalid address - Street: '%s'%n", invalidStreet != null ? invalidStreet : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when city is null, empty, or blank")
    void shouldReturnExceptionForInvalidCity(String invalidCity) {
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, invalidCity, state, zipCode));
        System.out.printf("Invalid address - City: '%s'%n", invalidCity != null ? invalidCity : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when state is null, empty, or blank")
    void shouldReturnExceptionForInvalidState(String invalidState) {
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, invalidState, zipCode));
        System.out.printf("Invalid address - State: '%s'%n", invalidState != null ? invalidState : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when zipCode is null, empty, or blank")
    void shouldReturnExceptionForInvalidZipCode(String invalidZipCode) {
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, state, invalidZipCode));
        System.out.printf("Invalid address - ZipCode: '%s'%n", invalidZipCode != null ? invalidZipCode : "null");
    }

    @Test
    @DisplayName("Deve criar um endereço pelo construtor vazio")
    void shouldCreateAddressEntityWithEmptyConstructor() {
        AddressEntity address = new AddressEntity();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        assertNotNull(address);
        assertEquals(street, address.getStreet());
        assertEquals(city, address.getCity());
        assertEquals(state, address.getState());
        assertEquals(zipCode, address.getZipCode());

        System.out.printf("Endereço criado pelo construtor vazio - %s %n", address.getStreet());
    }

}