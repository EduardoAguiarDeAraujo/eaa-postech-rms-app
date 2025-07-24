package br.eng.eaa.infra.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    private static final String MENU = "Menu API";
    private static final String RESTAURANT = "Restaurant API";
    private static final String USER = "User API";
    private static final String ROLE = "Role API";

    @Bean
    public OpenApiCustomizer disableSchemas() {
        return openApi -> {
            Components components = openApi.getComponents();
            if (components != null) {
                components.setSchemas(null);
            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {

        Paths paths = new Paths();

        Operation getMenusOperation = new Operation()
                .summary("Lista todos os menus")
                .tags(Arrays.asList(MENU))
                .operationId("getAllMenus")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Lista de todos os menus retornada com sucesso.")));
        PathItem menusPathItem = new PathItem().get(getMenusOperation);
        paths.put("/api/v1/menus", menusPathItem);

        Operation getMenuByIdOperation = new Operation()
                .summary("Busca um menu por ID")
                .tags(Arrays.asList(MENU))
                .operationId("getMenuById")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Menu encontrado com sucesso.")));
        PathItem menuByIdPathItem = new PathItem().get(getMenuByIdOperation);
        paths.put("/api/v1/menus/{id}", menuByIdPathItem);

        Operation saveMenuOperation = new Operation()
                .summary("Salva um novo menu")
                .tags(Arrays.asList(MENU))
                .operationId("saveMenu")
                .responses(new ApiResponses().addApiResponse("201", new ApiResponse().description("Menu salvo com sucesso.")));
        PathItem saveMenuPathItem = new PathItem().post(saveMenuOperation);
        paths.put("/api/v1/menus/create", saveMenuPathItem);

        Operation updateMenuOperation = new Operation()
                .summary("Atualiza um menu existente")
                .tags(Arrays.asList(MENU))
                .operationId("updateMenu")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Menu atualizado com sucesso.")));
        PathItem updateMenuPathItem = new PathItem().put(updateMenuOperation);
        paths.put("/api/v1/menus/update", updateMenuPathItem);

        Operation deleteMenuOperation = new Operation()
                .summary("Deleta um menu por ID")
                .tags(Arrays.asList(MENU))
                .operationId("deleteMenu")
                .responses(new ApiResponses().addApiResponse("204", new ApiResponse().description("Menu deletado com sucesso.")));
        PathItem deleteMenuPathItem = new PathItem().delete(deleteMenuOperation);
        paths.put("/api/v1/menus/delete/{id}", deleteMenuPathItem);

        Operation getRestaurantsOperation = new Operation()
                .summary("Lista todos os restaurantes")
                .tags(Arrays.asList(RESTAURANT))
                .operationId("getAllRestaurants")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Lista de todos os restaurantes retornada com sucesso.")));
        PathItem restaurantsPathItem = new PathItem().get(getRestaurantsOperation);
        paths.put("/api/v1/restaurants", restaurantsPathItem);

        Operation getRestaurantByIdOperation = new Operation()
                .summary("Busca um restaurante por ID")
                .tags(Arrays.asList(RESTAURANT))
                .operationId("getRestaurantById")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Restaurante encontrado com sucesso.")));
        PathItem restaurantByIdPathItem = new PathItem().get(getRestaurantByIdOperation);
        paths.put("/api/v1/restaurants/{id}", restaurantByIdPathItem);

        Operation saveRestaurantOperation = new Operation()
                .summary("Salva um novo restaurante")
                .tags(Arrays.asList(RESTAURANT))
                .operationId("saveRestaurant")
                .responses(new ApiResponses().addApiResponse("201", new ApiResponse().description("Restaurante salvo com sucesso.")));
        PathItem saveRestaurantPathItem = new PathItem().post(saveRestaurantOperation);
        paths.put("/api/v1/restaurants/create", saveRestaurantPathItem);

        Operation updateRestaurantOperation = new Operation()
                .summary("Atualiza um restaurante existente")
                .tags(Arrays.asList(RESTAURANT))
                .operationId("updateRestaurant")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Restaurante atualizado com sucesso.")));
        PathItem updateRestaurantPathItem = new PathItem().put(updateRestaurantOperation);
        paths.put("/api/v1/restaurants/update", updateRestaurantPathItem);

        Operation deleteRestaurantOperation = new Operation()
                .summary("Deleta um restaurante por ID")
                .tags(Arrays.asList(RESTAURANT))
                .operationId("deleteRestaurant")
                .responses(new ApiResponses().addApiResponse("204", new ApiResponse().description("Restaurante deletado com sucesso.")));
        PathItem deleteRestaurantPathItem = new PathItem().delete(deleteRestaurantOperation);
        paths.put("/api/v1/restaurants/delete/{id}", deleteRestaurantPathItem);

        Operation getUsersOperation = new Operation()
                .summary("Lista todos os usuários")
                .tags(Arrays.asList(USER))
                .operationId("getAllUsers")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Lista de todos os usuários retornada com sucesso.")));
        PathItem usersPathItem = new PathItem().get(getUsersOperation);
        paths.put("/api/v1/users", usersPathItem);

        Operation getUserByIdOperation = new Operation()
                .summary("Busca um usuário por ID")
                .tags(Arrays.asList(USER))
                .operationId("getUserById")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Usuário encontrado com sucesso.")));
        PathItem userByIdPathItem = new PathItem().get(getUserByIdOperation);
        paths.put("/api/v1/users/{id}", userByIdPathItem);

        Operation saveUserOperation = new Operation()
                .summary("Salva um novo usuário")
                .tags(Arrays.asList(USER))
                .operationId("saveUser")
                .responses(new ApiResponses().addApiResponse("201", new ApiResponse().description("Usuário salvo com sucesso.")));
        PathItem saveUserPathItem = new PathItem().post(saveUserOperation);
        paths.put("/api/v1/users/create", saveUserPathItem);

        Operation updateUserOperation = new Operation()
                .summary("Atualiza um usuário existente")
                .tags(Arrays.asList(USER))
                .operationId("updateUser")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Usuário atualizado com sucesso.")));
        PathItem updateUserPathItem = new PathItem().put(updateUserOperation);
        paths.put("/api/v1/users/update", updateUserPathItem);

        Operation deleteUserOperation = new Operation()
                .summary("Deleta um usuário por ID")
                .tags(Arrays.asList(USER))
                .operationId("deleteUser")
                .responses(new ApiResponses().addApiResponse("204", new ApiResponse().description("Usuário deletado com sucesso.")));
        PathItem deleteUserPathItem = new PathItem().delete(deleteUserOperation);
        paths.put("/api/v1/users/delete/{id}", deleteUserPathItem);

        Operation getRolesOperation = new Operation()
                .summary("Lista todos os papéis")
                .tags(Arrays.asList(ROLE))
                .operationId("getAllRoles")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Lista de todos os papéis retornada com sucesso.")));
        PathItem rolesPathItem = new PathItem().get(getRolesOperation);
        paths.put("/api/v1/roles", rolesPathItem);

        Operation getRoleByIdOperation = new Operation()
                .summary("Busca um papel por ID")
                .tags(Arrays.asList(ROLE))
                .operationId("getRoleById")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Papel encontrado com sucesso.")));
        PathItem roleByIdPathItem = new PathItem().get(getRoleByIdOperation);
        paths.put("/api/v1/roles/{id}", roleByIdPathItem);

        Operation saveRoleOperation = new Operation()
                .summary("Salva um novo papel")
                .tags(Arrays.asList(ROLE))
                .operationId("saveRole")
                .responses(new ApiResponses().addApiResponse("201", new ApiResponse().description("Papel salvo com sucesso.")));
        PathItem saveRolePathItem = new PathItem().post(saveRoleOperation);
        paths.put("/api/v1/roles/create", saveRolePathItem);

        Operation updateRoleOperation = new Operation()
                .summary("Atualiza um papel existente")
                .tags(Arrays.asList(ROLE))
                .operationId("updateRole")
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Papel atualizado com sucesso.")));
        PathItem updateRolePathItem = new PathItem().put(updateRoleOperation);
        paths.put("/api/v1/roles/update", updateRolePathItem);

        Operation deleteRoleOperation = new Operation()
                .summary("Deleta um papel por ID")
                .tags(Arrays.asList(ROLE))
                .operationId("deleteRole")
                .responses(new ApiResponses().addApiResponse("204", new ApiResponse().description("Papel deletado com sucesso.")));
        PathItem deleteRolePathItem = new PathItem().delete(deleteRoleOperation);
        paths.put("/api/v1/roles/delete/{id}", deleteRolePathItem);


        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant Management Systen API")
                        .version("1.0.0")
                        .description("API para gerenciamento de restaurantes")
                        .license(new License().name("eaa.eng.br").url("http://github.com/eduardoaguiardearaujo")))
                .paths(paths);


    }
}
