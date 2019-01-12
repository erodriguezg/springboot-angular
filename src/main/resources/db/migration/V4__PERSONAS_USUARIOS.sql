INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (1, 5801, 11111111, 'Admin', 'Admin ', 'Admin', '1990-01-01', '12345678', 'admin@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (2, 5801, 22222222, 'Nombres 1', 'Paterno 1', 'Materno 1', '1990-01-01', '12345678', 'usuario1@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (3, 5801, 33333333, 'Nombres 2', 'Paterno 2', 'Materno 2', '1990-01-01', '12345678', 'usuario2@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (4, 5801, 44444444, 'Nombres 3', 'Paterno 3', 'Materno 3', '1990-01-01', '12345678', 'usuario3@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (5, 5801, 55555555, 'Nombres 4', 'Paterno 4', 'Materno 4', '1990-01-01', '12345678', 'usuario4@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (6, 5801, 66666666, 'Nombres 5', 'Paterno 5', 'Materno 5', '1990-01-01', '12345678', 'usuario5@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (7, 5801, 77777777, 'Nombres 6', 'Paterno 6', 'Materno 8', '1990-01-01', '12345678', 'usuario6@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (8, 5801, 88888888, 'Nombres 7', 'Paterno 7', 'Materno 9', '1990-01-01', '12345678', 'usuario8@correo.com');
INSERT INTO "persona" ("id_persona", "id_comuna", "run", "nombres", "apellido_paterno", "apellido_materno", "fechanacimiento", "telefono", "email") VALUES (9, 5801, 99999999, 'Nombres 8', 'Paterno 8', 'Materno 9', '1990-01-01', '12345678', 'usuario9@correo.com');

ALTER SEQUENCE persona_id_persona_seq RESTART WITH 10;

INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (1, 1, 'admin', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (2, 2, 'user1', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (3, 2, 'user2', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (4, 2, 'user3', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (5, 2, 'user4', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (6, 2, 'user5', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (7, 2, 'user6', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (8, 2, 'user7', '70be2932a9786b17a1351b8d3b9fdf22', true);
INSERT INTO "usuario" ("id_persona", "id_perfil_usuario", "username", "password", "habilitado") VALUES (9, 2, 'user8', '70be2932a9786b17a1351b8d3b9fdf22', true);
