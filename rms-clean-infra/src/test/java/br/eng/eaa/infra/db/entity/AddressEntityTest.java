package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Deve retornar um exception para street com nome null")
    void shouldReturnExceptionForStreetNull() {
        String invalidStreet = null;
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(invalidStreet, city, state, zipCode));
        System.out.printf("Endereço inválido - Street null %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para street vazio")
    void shouldReturnExceptionForStreetEmpty() {
        String invalidStreet = "";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(invalidStreet, city, state, zipCode));
        System.out.printf("Endereço inválido - Street empty %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para street em branco")
    void shouldReturnExceptionForStreetBlank() {
        String invalidStreet = "  ";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(invalidStreet, city, state, zipCode));
        System.out.printf("Endereço inválido - Street blank %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para city com nome null")
    void shouldReturnExceptionForCityNull() {
        String invalidCity = null;
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, invalidCity, state, zipCode));
        System.out.printf("Endereço inválido - City null %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para city vazio")
    void shouldReturnExceptionForCityEmpty() {
        String invalidCity = "";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, invalidCity, state, zipCode));
        System.out.printf("Endereço inválido - City empty %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para city em branco")
    void shouldReturnExceptionForCityBlank() {
        String invalidCity = "  ";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, invalidCity, state, zipCode));
        System.out.printf("Endereço inválido - City blank %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para state com nome null")
    void shouldReturnExceptionForStateNull() {
        String invalidState = null;
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, invalidState, zipCode));
        System.out.printf("Endereço inválido - State null %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para state vazio")
    void shouldReturnExceptionForStateEmpty() {
        String invalidState = "";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, invalidState, zipCode));
        System.out.printf("Endereço inválido - State empty %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para state em branco")
    void shouldReturnExceptionForStateBlank() {
        String invalidState = "  ";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, invalidState, zipCode));
        System.out.printf("Endereço inválido - State blank %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para zipCode com nome null")
    void shouldReturnExceptionForZipCodeNull() {
        String invalidZipCode = null;
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, state, invalidZipCode));
        System.out.printf("Endereço inválido - ZipCode null %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para zipCode vazio")
    void shouldReturnExceptionForZipCodeEmpty() {
        String invalidZipCode = "";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, state, invalidZipCode));
        System.out.printf("Endereço inválido - ZipCode empty %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para zipCode em branco")
    void shouldReturnExceptionForZipCodeBlank() {
        String invalidZipCode = "  ";
        assertThrows(IllegalArgumentException.class, () -> new AddressEntity(street, city, state, invalidZipCode));
        System.out.printf("Endereço inválido - ZipCode blank %n");
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