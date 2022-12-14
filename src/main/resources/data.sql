INSERT INTO tb_user(nome, email, senha) VALUES ('Murilo', 'murilo@gmail.com', '$2a$12$JuK8AmWZJG3TTceZ/WvlBOl.IlPh5FnDmTREOmbpMpfYAzEUsNCp2');
INSERT INTO tb_user(nome, email, senha) VALUES ('Julão', 'juliao@gmail.com', '$2a$12$JuK8AmWZJG3TTceZ/WvlBOl.IlPh5FnDmTREOmbpMpfYAzEUsNCp2');

INSERT INTO role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO role (authority) VALUES ('ROLE_CLIENT');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
