-- Insertar roles necesarios (si no existen)
INSERT IGNORE INTO roles (id_rol, nombreRol) VALUES (1, 'ADMIN');
INSERT IGNORE INTO roles (id_rol, nombreRol) VALUES (2, 'USUARIO');

-- Insertar usuarios
INSERT IGNORE INTO usuarios (nombreUsuario, edad, email, apellidoUsuario, password, id_rol) VALUES ('alonso', 20, 'admin@admin.com', 'juan', '123456', 1);
INSERT IGNORE INTO usuarios (nombreUsuario, edad, email, apellidoUsuario, password, id_rol) VALUES ('pedrin', 23, 'usuario@user.com', 'pedro', '123456', 2);
