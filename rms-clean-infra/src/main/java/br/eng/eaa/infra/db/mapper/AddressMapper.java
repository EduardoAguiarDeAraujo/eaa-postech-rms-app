package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.infra.db.entity.AddressEntity;

public class AddressMapper {

    private AddressMapper() { }

    public static AddressEntity toEntity(Address address) {;
        if (address == null) {
            throw new IllegalArgumentException("Address must be not null");
        }
        return new AddressEntity(
            address.getStreet(),
            address.getCity(),
            address.getState(),
            address.getZipCode()
        );
    }

    public static Address toDomain(AddressEntity addressEntity) {
        if (addressEntity == null) {
            throw new IllegalArgumentException("AddressEntity must be not null");
        }
        return new Address(
            addressEntity.getStreet(),
            addressEntity.getCity(),
            addressEntity.getState(),
            addressEntity.getZipCode()
        );
    }


}
