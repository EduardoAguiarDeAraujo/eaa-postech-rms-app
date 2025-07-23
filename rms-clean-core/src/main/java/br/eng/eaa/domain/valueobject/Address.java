package br.eng.eaa.domain.valueobject;

public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address(String street, String city, String state, String zipCode) {
        setStreet(street);
        setCity(city);
        setState(state);
        setZipCode(zipCode);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    private void setStreet(String street) {
        if (street == null){
            throw new IllegalArgumentException("Street parameters must be not null");
        }
        if (street.isEmpty()){
            throw new IllegalArgumentException("Street parameters must be not empty");
        }
        if (street.isBlank()){
            throw new IllegalArgumentException("Street parameters must be not blank");
        }
        this.street = street;
    }

    private void setCity(String city) {
        if (city == null){
            throw new IllegalArgumentException("City parameters are required");
        }
        if (city.isEmpty()){
            throw new IllegalArgumentException("City parameters must be filled");
        }
        if (city.isBlank()){
            throw new IllegalArgumentException("City parameters must be filled");
        }
        this.city = city;
    }

    private void setState(String state) {
        if (state == null){
            throw new IllegalArgumentException("State parameters are required");
        }
        if (state.isEmpty()){
            throw new IllegalArgumentException("State parameters must be filled");
        }
        if (state.isBlank()){
            throw new IllegalArgumentException("State parameters must be filled");
        }
        this.state = state;
    }

    private void setZipCode(String zipCode) {
        if (zipCode == null){
            throw new IllegalArgumentException("ZipCode parameters are required");
        }
        if (zipCode.isEmpty()){
            throw new IllegalArgumentException("ZipCode parameters must be filled");
        }
        if (zipCode.isBlank()){
            throw new IllegalArgumentException("ZipCode parameters must be filled");
        }
        this.zipCode = zipCode;
    }
}
