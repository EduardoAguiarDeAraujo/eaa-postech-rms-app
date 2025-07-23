package br.eng.eaa.domain.valueobject;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Deve retornar uma exception para street null")
    void shouldReturnExceptionForStreetNull(){
        String invalidStreet = null;
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidStreet, city, state, zipCode));
        System.out.printf("Endereço inválido: street null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para street null")
    void shouldReturnExceptionForCitytNull(){
        String invalidCity = null;
        assertThrows(IllegalArgumentException.class, () -> new Address(street, invalidCity, state, zipCode));
        System.out.printf("Endereço inválido: city null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para street null")
    void shouldReturnExceptionForStateNull(){
        String invalidState = null;
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, invalidState, zipCode));
        System.out.printf("Endereço inválido: street null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para state null")
    void shouldReturnExceptionForZipCodeNull(){
        String invalidNullZipCode = null;
        String invalidBrankZipCode = " ";
        String invalidEmptyZipCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, invalidNullZipCode));
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, invalidBrankZipCode));
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, invalidEmptyZipCode));
        System.out.printf("Endereço inválido: zipcode null, blank and empty %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters1(){
        String street = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters2(){
        String city = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters3(){
        String state = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters4(){
        String zipCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters5(){
        String street = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters6(){
        String city = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters7(){
        String state = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exception para todos parâmetros null")
    void shouldReturnExceptionForAllEmptyParameters8(){
        String zipCode = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Address(street, city, state, zipCode));
        System.out.printf("Endereço inválido: todos parâmetros null %n");
    }


}