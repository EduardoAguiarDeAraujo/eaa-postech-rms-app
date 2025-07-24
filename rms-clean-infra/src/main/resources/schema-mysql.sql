DROP TABLE IF EXISTS tb_menu;
DROP TABLE IF EXISTS tb_restaurant;
DROP TABLE IF EXISTS tb_address;
DROP TABLE IF EXISTS tb_user_role;
DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_role;

CREATE TABLE tb_user (
    id BINARY(16) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_role (
     id BINARY(16) NOT NULL,
     name VARCHAR(50) NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE tb_user_role (
      user_id BINARY(16) NOT NULL,
      role_id BINARY(16) NOT NULL,
      PRIMARY KEY (user_id, role_id),
      FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE,
      FOREIGN KEY (role_id) REFERENCES tb_role(id) ON DELETE CASCADE
);

CREATE TABLE tb_address (
    id BINARY(16) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_restaurant (
    id BINARY(16) NOT NULL,
    name VARCHAR(50) NOT NULL,
    cuisine_type varchar (50) NOT NULL,
    open_time TIME NOT NULL,
    close_time TIME NOT NULL,
    owner_id BINARY(16) NOT NULL,
    address_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES tb_user(id),
    FOREIGN KEY (address_id) REFERENCES tb_address(id) ON DELETE CASCADE
);

CREATE TABLE tb_menu (
    id BINARY(16) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    image_url VARCHAR(255) NOT NULL,
    restaurant_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES tb_restaurant(id)
);
