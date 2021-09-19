CREATE DATABASE Papeleria;
USE Papeleria;

CREATE TABLE cliente(
id INT PRIMARY KEY,
nombre VARCHAR(100),
tipo_documento INT,
documento BIGINT,
direccion VARCHAR(300)
);

CREATE TABLE usuario(
id INT PRIMARY KEY,
nombre VARCHAR(100),
contrasenna VARCHAR(10),
tipo_documento INT,
documento BIGINT,
direccion VARCHAR(300),
perfil BOOLEAN
);

CREATE TABLE producto (
id INT PRIMARY KEY,
nombre VARCHAR(50),
unidades INT,
proveedor VARCHAR(60),
marca VARCHAR(30),
precio DOUBLE,
costo DOUBLE);

CREATE TABLE pedido(
id INT PRIMARY KEY,
fecha DATE,
cliente_id INT,
vendedor_id INT,
FOREIGN KEY (cliente_id) REFERENCES cliente(id),
FOREIGN KEY (vendedor_id) REFERENCES usuario(id)
);

CREATE TABLE venta(
id INT PRIMARY KEY,
pedido_id INT,
producto_id INT,
cantidad INT,
FOREIGN KEY (pedido_id) REFERENCES pedido(id),
FOREIGN KEY (producto_id) REFERENCES producto(id)
);
