package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import br.eng.eaa.domain.entity.Role;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class RestaurantGatewayStub implements IRestaurantGateway {

    private UUID id;
    private String name;
    private CuisineType cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private User owner;
    private Address address;
    private List<Menu> menus;
    private UUID restaurantId;
    private Restaurant restaurant;

    public RestaurantGatewayStub() {
        this.id = UUID.fromString("31d21f36-6edc-4f3d-9fda-879e09c739fe");
        this.name = "Restaurante da Maria";
        this.restaurantId = UUID.randomUUID();
        this.openTime = LocalTime.of(11, 0);
        this.closeTime = LocalTime.of(22, 0);
        List<Role> roles = List.of(new Role("ADMIN"), new Role("OWNER"));
        List<Menu> menus = List.of(
                new Menu("Contra-filé ", "Contra-file com fritas, arroz branco e feijão", BigDecimal.valueOf(45.00), true, "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg", restaurantId),
                new Menu("Contra-filé a cavalo", "Contra-filé com ovo frito, arroz brando e feijão carioquinha",BigDecimal.valueOf(47.00), true, "https://sabores-new.s3.amazonaws.com/public/2024/11/bife-a-cavalo-1024x494.jpg", restaurantId)
        );
        Address address = new Address("Av. Paulista, 1106", "São Paulo", "SP", "01311-000");
        this.cuisineType = CuisineType.BRASILIAN;
        this.owner = new User (UUID.randomUUID(), "Eduardo",  roles);
        this.address = address;
        this.menus = menus;

        this.restaurant = new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurant;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return restaurant;
    }

    @Override
    public Restaurant findById(UUID id) {
        if(id.equals(this.id)) {
            return restaurant;
        }
        return null;
    }

    @Override
    public List<Restaurant> findAll() {
        return List.of(restaurant, restaurant);
    }

    @Override
    public Boolean delete(UUID id) {
        if(id.equals(this.id)){
            return true;
        }
        return false;
    }
}
