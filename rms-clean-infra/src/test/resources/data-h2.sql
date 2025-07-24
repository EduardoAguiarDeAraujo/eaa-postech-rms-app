INSERT INTO tb_user (ID, USER_NAME, PASSWORD) VALUES ('e0f279d7-5536-11f0-a29e-04bf1b4887e6','EDUARDO', 'MinhaSenhaForte123#');
INSERT INTO tb_user (ID, USER_NAME, PASSWORD) VALUES ('77ed86e6-5537-11f0-a29e-04bf1b4887e6','KATIA', 'MinhaSenhaForte123#');
INSERT INTO tb_user (ID, USER_NAME, PASSWORD) VALUES ('82c9e3fe-5537-11f0-a29e-04bf1b4887e6','REBECA', 'MinhaSernhaForte123#');
INSERT INTO tb_user (ID, USER_NAME, PASSWORD) VALUES ('dea722bd-3383-4367-96a7-81d0232cc392','VALERIA', 'MinhaSernhaForte123#');

INSERT INTO tb_address (ID, STREET, CITY, STATE, ZIP_CODE) VALUES ('1a2b3c4d-55ca-11f0-a29e-04bf1b4887e6','Av. Paulista, 123', 'São Paulo', 'SP', '01334-567');
INSERT INTO tb_address (ID, STREET, CITY, STATE, ZIP_CODE) VALUES ('2b3c4d5e-55ca-11f0-a29e-04bf1b4887e6','Rua da Consolação, 456', 'São Paulo', 'SP', '01323-456');
INSERT INTO tb_address (ID, STREET, CITY, STATE, ZIP_CODE) VALUES ('3c4d5e6f-55ca-11f0-a29e-04bf1b4887e6','Av. Brigadeiro Faria Lima, 789', 'São Paulo', 'SP', '01452-789');

INSERT INTO tb_restaurant (ID, NAME, CUISINE_TYPE, OPEN_TIME, CLOSE_TIME, OWNER_ID, ADDRESS_ID) VALUES ('6c642e31-55ca-11f0-a29e-04bf1b4887e6','EDU PIZZA', 'ITALIAN', '11:00:00','22:00:00','e0f279d7-5536-11f0-a29e-04bf1b4887e6' , '1a2b3c4d-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_restaurant (ID, NAME, CUISINE_TYPE, OPEN_TIME, CLOSE_TIME, OWNER_ID, ADDRESS_ID) VALUES ('7bacc50f-55ca-11f0-a29e-04bf1b4887e6','KATIA BURGER', 'AMERICAN', '11:00:00','22:00:00','77ed86e6-5537-11f0-a29e-04bf1b4887e6', '2b3c4d5e-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_restaurant (ID, NAME, CUISINE_TYPE, OPEN_TIME, CLOSE_TIME, OWNER_ID, ADDRESS_ID) VALUES ('8c41874b-55ca-11f0-a29e-04bf1b4887e6','REBECA SUSHI', 'JAPANESE', '11:00:00','22:00:00','82c9e3fe-5537-11f0-a29e-04bf1b4887e6', '3c4d5e6f-55ca-11f0-a29e-04bf1b4887e6');

INSERT INTO tb_menu (ID, NAME, DESCRIPTION, PRICE, AVAILABLE, IMAGE_URL, RESTAURANT_ID) VALUES ('9d5f1c6b-55ca-11f0-a29e-04bf1b4887e6','MARGUERITA', 'Pizza de mussarela com tomate e manjericão', 29.90, TRUE, 'https://example.com/images/marguerita.jpg', '6c642e31-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_menu (ID, NAME, DESCRIPTION, PRICE, AVAILABLE, IMAGE_URL, RESTAURANT_ID) VALUES ('a0b1c2d3-55ca-11f0-a29e-04bf1b4887e6','PEPPERONI', 'Pizza de pepperoni com queijo e molho de tomate', 34.90, TRUE, 'https://example.com/images/pepperoni.jpg', '6c642e31-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_menu (ID, NAME, DESCRIPTION, PRICE, AVAILABLE, IMAGE_URL, RESTAURANT_ID) VALUES ('b1c2d3e4-55ca-11f0-a29e-04bf1b4887e6','CLASSIC BURGER', 'Hambúrguer clássico com queijo, alface e tomate', 19.90, TRUE, 'https://example.com/images/classic_burger.jpg', '7bacc50f-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_menu (ID, NAME, DESCRIPTION, PRICE, AVAILABLE, IMAGE_URL, RESTAURANT_ID) VALUES ('c2d3e4f5-55ca-11f0-a29e-04bf1b4887e6','SPICY BURGER', 'Hambúrguer picante com molho especial', 21.90, TRUE, 'https://example.com/images/spicy_burger.jpg', '7bacc50f-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_menu (ID, NAME, DESCRIPTION, PRICE, AVAILABLE, IMAGE_URL, RESTAURANT_ID) VALUES ('dc359bb4-583a-11f0-a29e-04bf1b4887e6','SASHIMI DE SALMÃO', 'Fatias finas de salmão fresco', 39.90, TRUE, 'https://example.com/images/sashimi_salmon.jpg', '8c41874b-55ca-11f0-a29e-04bf1b4887e6');
INSERT INTO tb_menu (ID, NAME, DESCRIPTION, PRICE, AVAILABLE, IMAGE_URL, RESTAURANT_ID) VALUES ('f3b10dcc-583a-11f0-a29e-04bf1b4887e6','NIGIRI DE ATUM', 'Bolinho de arroz com fatia de atum', 34.90, TRUE, 'https://example.com/images/nigiri_tuna.jpg', '8c41874b-55ca-11f0-a29e-04bf1b4887e6');

INSERT INTO tb_role (ID, NAME) VALUES ('4d5e6f7a-55ca-11f0-a29e-04bf1b4887e6', 'ADMIN');
INSERT INTO tb_role (ID, NAME) VALUES ('6f7a8b9c-55ca-11f0-a29e-04bf1b4887e6', 'OWNER');
INSERT INTO tb_role (ID, NAME) VALUES ('5e6f7a8b-55ca-11f0-a29e-04bf1b4887e6', 'CUSTOMER');

INSERT INTO tb_user_role (USER_ID, ROLE_ID) VALUES ('e0f279d7-5536-11f0-a29e-04bf1b4887e6', '4d5e6f7a-55ca-11f0-a29e-04bf1b4887e6'); -- ADMIN
INSERT INTO tb_user_role (USER_ID, ROLE_ID) VALUES ('e0f279d7-5536-11f0-a29e-04bf1b4887e6', '6f7a8b9c-55ca-11f0-a29e-04bf1b4887e6'); -- OWNER
INSERT INTO tb_user_role (USER_ID, ROLE_ID) VALUES ('77ed86e6-5537-11f0-a29e-04bf1b4887e6', '5e6f7a8b-55ca-11f0-a29e-04bf1b4887e6'); -- CUSTOMER
INSERT INTO tb_user_role (USER_ID, ROLE_ID) VALUES ('82c9e3fe-5537-11f0-a29e-04bf1b4887e6', '6f7a8b9c-55ca-11f0-a29e-04bf1b4887e6'); -- OWNER
INSERT INTO tb_user_role (USER_ID, ROLE_ID) VALUES ('82c9e3fe-5537-11f0-a29e-04bf1b4887e6', '4d5e6f7a-55ca-11f0-a29e-04bf1b4887e6'); -- OWNER
