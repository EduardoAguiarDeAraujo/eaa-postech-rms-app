package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.infra.db.entity.AddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private UUID id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @BeforeEach
    void setUp() {
        id = UUID.fromString("e0f279d7-5536-11f0-a29e-04bf1b4887e6");
        street = "Av Paulista";
        city = "São Paulo";
        state = "SP";
        zipCode = "01311-000";
    }

    @Test
    @DisplayName("Test AddressMapper toEntity")
    void testAddressMapperCreation() {
        // Given
        Address address = new Address(street, city, state, zipCode);
        AddressEntity addressEntity = new AddressEntity(street, city, state, zipCode);

        // When
        AddressEntity addressConverted = AddressMapper.toEntity(address);

        // Then
        assertNotNull(addressEntity.getId());
        assertEquals(street, addressConverted.getStreet());
        assertEquals(city, addressConverted.getCity());
        assertEquals(state, addressConverted.getState());
        assertEquals(zipCode, addressConverted.getZipCode());
    }

    @Test
    @DisplayName("Test AddressMapper toDomain")
    void testAddressMapperToDomain() {
        // Given
        AddressEntity addressEntity = new AddressEntity(street, city, state, zipCode);

        // When
        Address addressConverted = AddressMapper.toDomain(addressEntity);

        // Then
        assertNotNull(addressConverted);
        assertEquals(street, addressConverted.getStreet());
        assertEquals(city, addressConverted.getCity());
        assertEquals(state, addressConverted.getState());
        assertEquals(zipCode, addressConverted.getZipCode());
    }

    @Test
    @DisplayName("Deve retornar uma exception para toEntity com address null")
    void  shouldReturnExceptionForToEntityWithNullAddress() {
        // Given
        Address address = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> AddressMapper.toEntity(address));
        System.out.printf("Endereço inválido - Address null %n");

    }

    @Test
    @DisplayName("Deve retornar uma exception para toDomain com addressEntity null")
    void shouldReturnExceptionForToDomainWithNullAddressEntity() {
        // Given
        AddressEntity addressEntity = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> AddressMapper.toDomain(addressEntity));
        System.out.printf("Endereço inválido - AddressEntity null %n");
    }

    @Test
    @DisplayName("Deve criar uma instancia de uma classe AddressMapper válida")
    void shouldCreateValidAddressMapperInstance() {
        // When
        AddressMapper addressMapper = new AddressMapper();

        // Then
        assertNotNull(addressMapper);
        assertEquals(AddressMapper.class, addressMapper.getClass());
    }


}