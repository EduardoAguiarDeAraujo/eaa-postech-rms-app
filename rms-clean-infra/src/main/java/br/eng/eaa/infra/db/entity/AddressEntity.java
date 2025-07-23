package br.eng.eaa.infra.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_address")
public class AddressEntity {

    @Id
    private UUID id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public AddressEntity() {
    }

    public AddressEntity(String street, String city, String state, String zipCode) {
        setId(UUID.randomUUID());
        setStreet(street);
        setCity(city);
        setState(state);
        setZipCode(zipCode);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        validateString( street, "Street");
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        validateString( city, "City");
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        validateString( state, "State");
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        validateString(zipCode, "Zip Code");
        this.zipCode = zipCode;
    }

    private void validateString(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " name cannot be null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " name cannot be empty");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " name cannot be blank");
        }
    }

}
