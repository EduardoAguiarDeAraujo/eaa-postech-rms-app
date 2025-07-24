package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuGatewayStub implements IMenuGateway{

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    public MenuGatewayStub() {
        this.id = UUID.fromString("31d21f36-6edc-4f3d-9fda-879e09c739fe");
        this.name = "Contra-filé acebolado";
        this.description = "Contra-filé acebolado com arroz branco e feijão";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }

    @Override
    public Menu save(Menu menu) {
        return new Menu(id,name,description,price,available,imageUrl, restaurantId);
    }

    @Override
    public Menu update(Menu menu) {
        return new Menu(id,name,description,price,available,imageUrl, restaurantId);
    }

    @Override
    public Menu findById(UUID id) {
        return new Menu(id,name,description,price,available,imageUrl, restaurantId);
    }

    @Override
    public List<Menu> findAll() {
        return List.of(
                new Menu(id,name,description,price,available,imageUrl, restaurantId),
                new Menu(id,name,description,price,available,imageUrl, restaurantId)
        );
    }

    @Override
    public Boolean delete(UUID id) {
        if(id.equals(this.id)){
            return true;
        }
        return false;
    }
}
