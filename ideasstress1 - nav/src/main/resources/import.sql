-- Active: 1682466125569@@127.0.0.1@3306@capsworld
INSERT INTO Clientes(Nombre, Apellido, Email, creat_at) VALUES ('Juan', 'Eusse', 'juan_eusse@gmail.com', '2023-02-10');
INSERT INTO Clientes(Nombre, Apellido, Email, creat_at) VALUES ('Pabla', 'Zapata', 'pablo_zapata@gmail.com', '2023-02-11');

INSERT INTO Productos(Nombre, Descripcion, Existencias, Precio, codigo) VALUES ('Gorra', 'Hugo Boos', 10, 300000, 'A1');
INSERT INTO Productos(Nombre, Descripcion, Existencias, Precio, codigo) VALUES ('Gorra', 'Gorrin bros', 15, 200000, 'A2');

INSERT INTO usuarios(email, Password, Nombre, Apellido) VALUES ('jer2113@gmail.com', '$2a$10$y/GqqtAxnm3MQoRBLtCVgu2cggyA/Yp.fjgcliKDos/ALlzk3DxeS', 'Juan', 'Eusse');