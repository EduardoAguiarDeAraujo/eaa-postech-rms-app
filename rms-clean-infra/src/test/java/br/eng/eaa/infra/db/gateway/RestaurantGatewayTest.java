package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import br.eng.eaa.infra.api.exception.RestaurantNotFoundException;
import br.eng.eaa.infra.db.entity.*;
import br.eng.eaa.infra.db.mapper.AddressMapper;
import br.eng.eaa.infra.db.mapper.MenuMapper;
import br.eng.eaa.infra.db.mapper.RestaurantMapper;
import br.eng.eaa.infra.db.mapper.UserMapper;
import br.eng.eaa.infra.db.repository.RestaurantRepository;
import br.eng.eaa.infra.db.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestaurantGatewayTest {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    private RestaurantGateway restaurantGateway;
    private MockedStatic<RestaurantMapper> mockRestaurantMapper;
    private MockedStatic<MenuMapper> mockMenuMapper;
    private MockedStatic<UserMapper> mockUserMapper;
    private MockedStatic<AddressMapper> mockAddressMapper;

    private UUID restaurantId;
    private String restaurantName;

    private CuisineType cuisineTypeDomain;
    private CuisineTypeEnum cuisineTypeEntity;

    private Menu mockMenu;
    private MenuEntity mockMenuEntity;

    private Address mockAddressDomain;
    private AddressEntity mockAddressEntity;

    private User mockOwnerDomain;
    private UserEntity mockOwnerEntity;

    private LocalTime openTime;
    private LocalTime closeTime;

    @BeforeEach
    void setUp() {
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        restaurantGateway = new RestaurantGateway(restaurantRepository, userRepository);

        mockRestaurantMapper = Mockito.mockStatic(RestaurantMapper.class);
        mockMenuMapper = Mockito.mockStatic(MenuMapper.class);
        mockUserMapper = Mockito.mockStatic(UserMapper.class);
        mockAddressMapper = Mockito.mockStatic(AddressMapper.class);

        mockMenu = Mockito.mock(Menu.class);
        mockMenuEntity = Mockito.mock(MenuEntity.class);
        mockAddressDomain = Mockito.mock(Address.class);
        mockAddressEntity = Mockito.mock(AddressEntity.class);
        mockOwnerDomain = new User(UUID.fromString("e0f279d7-5536-11f0-a29e-04bf1b4887e6"), "Eduardo");
        mockOwnerEntity = new UserEntity(UUID.fromString("e0f279d7-5536-11f0-a29e-04bf1b4887e6"), "Eduardo");

        restaurantId = UUID.randomUUID();
        restaurantName = "Restaurante de Maria";
        cuisineTypeDomain = CuisineType.ITALIAN;
        cuisineTypeEntity = CuisineTypeEnum.ITALIAN;

        openTime = LocalTime.of(11, 0);
        closeTime = LocalTime.of(22, 0);
    }

    @AfterEach
    void tearDown() {
        mockUserMapper.close();
        mockAddressMapper.close();
        mockMenuMapper.close();
        mockRestaurantMapper.close();
        restaurantRepository = null;
        restaurantGateway = null;
        mockMenu = null;
        mockMenuEntity = null;
        mockAddressDomain = null;
        mockAddressEntity = null;
        mockOwnerDomain = null;
        mockOwnerEntity = null;
    }

    @Test
    @DisplayName("Deve salvar um restaurante com sucesso")
    void shouldReturnRestaurantSuccessfully() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));
        RestaurantEntity restaurantEntity = new  RestaurantEntity(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity)
        );

        // When
        mockUserMapper.when(() -> UserMapper.toDomain(mockOwnerEntity)).thenReturn(mockOwnerDomain);
        mockAddressMapper.when(() -> AddressMapper.toDomain(mockAddressEntity)).thenReturn(mockAddressDomain);
        mockMenuMapper.when(() -> MenuMapper.toDomain(mockMenuEntity)).thenReturn(mockMenu);
        mockRestaurantMapper.when(() -> RestaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurantDomain);
        mockRestaurantMapper.when(() -> RestaurantMapper.toEntity(restaurantDomain)).thenReturn(restaurantEntity);
        when(userRepository.findById(mockOwnerDomain.getId())).thenReturn(Optional.of(mockOwnerEntity));
        when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        Restaurant savedRestaurantDomain = restaurantGateway.save(restaurantDomain);


        // Then
        verify(restaurantRepository, times(1)).save(restaurantEntity);
        assertEquals(restaurantId, savedRestaurantDomain.getId());
        assertEquals(restaurantName, savedRestaurantDomain.getName());
        assertEquals(cuisineTypeDomain, savedRestaurantDomain.getCuisineType());
        assertEquals(mockOwnerDomain, savedRestaurantDomain.getOwner());
        assertEquals(mockAddressDomain, savedRestaurantDomain.getAddress());
        assertEquals(List.of(mockMenu).size(), savedRestaurantDomain.getMenus().size());
    }

    @Test
    @DisplayName("Deve retornar uma lista de restaurantes com sucesso")
    void shouldReturnListOfRestaurantsSuccessfully() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity));

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurantDomain);
        when(restaurantRepository.findAll()).thenReturn(List.of(restaurantEntity));
        List<Restaurant> restaurants = restaurantGateway.findAll();

        // Then
        verify(restaurantRepository, times(1)).findAll();
        assertEquals(1, restaurants.size());
        assertEquals(restaurantId, restaurants.get(0).getId());
        assertEquals(restaurantName, restaurants.get(0).getName());
    }

    @Test
    @DisplayName("Deve retornar um restaurante por ID com sucesso")
    void shouldReturnRestaurantByIdSuccessfully() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity));

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurantDomain);
        when(restaurantRepository.findById(restaurantId)).thenReturn(java.util.Optional.of(restaurantEntity));
        Restaurant foundRestaurant = restaurantGateway.findById(restaurantId);

        // Then
        verify(restaurantRepository, times(1)).findById(restaurantId);
        assertEquals(restaurantId, foundRestaurant.getId());
        assertEquals(restaurantName, foundRestaurant.getName());
    }

    @Test
    @DisplayName("Deve atualizar um restaurante válido")
    void shouldUpdateValidRestaurant() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu, mockMenu));
        UserEntity userEntity = new UserEntity(UUID.randomUUID(),"Eduardo", "MinhaSenhaForte1#", List.of(new RoleEntity(UUID.randomUUID(), "OWNER")));
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity, mockMenuEntity));

        // When
        mockUserMapper.when(() -> UserMapper.toDomain(mockOwnerEntity)).thenReturn(mockOwnerDomain);
        mockAddressMapper.when(() -> AddressMapper.toDomain(mockAddressEntity)).thenReturn(mockAddressDomain);
        mockMenuMapper.when(() -> MenuMapper.toDomain(mockMenuEntity)).thenReturn(mockMenu);
        mockRestaurantMapper.when(() -> RestaurantMapper.toDomain(restaurantEntity)).thenReturn(restaurantDomain);
        mockRestaurantMapper.when(() -> RestaurantMapper.toEntity(restaurantDomain)).thenReturn(restaurantEntity);
        when(restaurantRepository.findById(restaurantDomain.getId())).thenReturn(Optional.of(restaurantEntity));
        when(userRepository.findById(mockOwnerDomain.getId())).thenReturn(Optional.of(userEntity));
        when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        Restaurant updatedRestaurant = restaurantGateway.update(restaurantDomain);

        // Then
        verify(restaurantRepository, times(1)).findById(restaurantId);
        verify(restaurantRepository, times(1)).save(restaurantEntity);
        assertEquals(restaurantId, updatedRestaurant.getId());
        assertEquals(restaurantName, updatedRestaurant.getName());
    }

    @Test
    @DisplayName("Deve deletar um restaurante por ID com sucesso")
    void shouldDeleteRestaurantByIdSuccessfully() {
        // Given
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity));

        // When
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));
        doNothing().when(restaurantRepository).delete(restaurantEntity);
        Boolean isDeleted = restaurantGateway.delete(restaurantId);

        // Then
        verify(restaurantRepository, times(1)).findById(restaurantId);
        verify(restaurantRepository, times(1)).delete(restaurantEntity);
        assertEquals(true, isDeleted);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar encontrar um restaurante com ID inexistente")
    void shouldThrowExceptionWhenDeletingNonExistentRestaurant() {
        // Given
        UUID nonExistentRestaurantId = UUID.randomUUID();

        // When
        when(restaurantRepository.findById(nonExistentRestaurantId)).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {restaurantGateway.findById(nonExistentRestaurantId);});

        // Then
        verify(restaurantRepository, times(1)).findById(nonExistentRestaurantId);
        assertEquals("Restaurant not found with id: " + nonExistentRestaurantId, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar um restaurante com ID inexistente")
    void shouldThrowExceptionWhenUpdatingNonExistentRestaurant() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));

        // When
        when(restaurantRepository.findById(restaurantDomain.getId())).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {restaurantGateway.update(restaurantDomain);});

        // Then
        verify(restaurantRepository, times(1)).findById(restaurantId);
        assertEquals("Restaurant not found with id: " + restaurantId, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar excluir um restaurante com ID inexistente")
    void shouldThrowExceptionWhenFindingNonExistentRestaurant() {
        // Given
        UUID nonExistentRestaurantId = UUID.randomUUID();

        // When
        when(restaurantRepository.findById(nonExistentRestaurantId)).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantGateway.delete(nonExistentRestaurantId);
        });

        // Then
        verify(restaurantRepository, times(1)).findById(nonExistentRestaurantId);
        assertEquals("Restaurant not found with id: " + nonExistentRestaurantId, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção RestaurantNotFoundException salvar restaurante com Id do owner inválido")
    void shouldThrowExceptionWhenSavingRestaurantWithInvalidOwnerId() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));

        // When
        when(userRepository.findById(mockOwnerDomain.getId())).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantGateway.save(restaurantDomain);
        });

        // Then
        verify(userRepository, times(1)).findById(mockOwnerDomain.getId());
        assertEquals("Restaurant owner not found with id: " + mockOwnerDomain.getId(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar um restaurante com Id do owner invalido")
    void shouldThrowExceptionWhenUpdatingRestaurantWithInvalidOwnerId() {
        // Given
        Restaurant restaurantDomain = new Restaurant(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));

        // When
        when(restaurantRepository.findById(restaurantDomain.getId())).thenReturn(Optional.of(new RestaurantEntity()));
        when(userRepository.findById(mockOwnerDomain.getId())).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantGateway.update(restaurantDomain);
        });

        // Then
        verify(userRepository, times(1)).findById(mockOwnerDomain.getId());
        assertEquals("Restaurant owner not found with id: " + mockOwnerDomain.getId(), exception.getMessage());
    }

}