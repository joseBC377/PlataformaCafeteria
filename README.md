# ☕ Plataforma Cafetería - Base de Datos

## 🧠 Modelo lógico

![Modelo lógico](https://github.com/user-attachments/assets/752a220d-8372-4909-86a4-05c3d288f3b0)

---

## 🛠️ Modelo físico de la base de datos (SQL optimizado)

```sql
-- CREAR BASE DE DATOS
CREATE DATABASE IF NOT EXISTS cafeteria_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cafeteria_db;

-- TABLA USUARIO
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('cliente', 'admin') DEFAULT 'cliente',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TABLA CATEGORIA
CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- TABLA SUBCATEGORIA
CREATE TABLE IF NOT EXISTS subcategoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE CASCADE
);

-- TABLA PRODUCTOS
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    id_categoria INT NOT NULL,
    id_subcategoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE CASCADE,
    FOREIGN KEY (id_subcategoria) REFERENCES subcategoria(id) ON DELETE SET NULL
);

-- TABLA PEDIDO
CREATE TABLE IF NOT EXISTS pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('pendiente', 'confirmado', 'en_proceso', 'entregado', 'cancelado') DEFAULT 'pendiente',
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

-- TABLA DETALLE_PEDIDO
CREATE TABLE IF NOT EXISTS detalle_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE
);

-- TABLA PAGO
CREATE TABLE IF NOT EXISTS pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    metodo_pago ENUM('tarjeta', 'efectivo', 'transferencia') NOT NULL,
    estado_pago ENUM('pendiente', 'pagado', 'fallido') DEFAULT 'pendiente',
    monto_total DECIMAL(10, 2) NOT NULL,
    fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id) ON DELETE CASCADE
);

-- TABLA CONTACTENOS
CREATE TABLE IF NOT EXISTS contactenos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 🛠️ Datos para insertar a la base de datos

```sql
-- USUARIOS
INSERT INTO usuario (nombre, apellido, correo, telefono, contrasena, rol)
VALUES 
('Ana', 'Ramírez', 'ana@correo.com', '0987654321', 'contrasena123', 'cliente'),
('Luis', 'Pérez', 'luis@correo.com', '0976543210', 'contrasena456', 'admin');

-- CATEGORÍAS
INSERT INTO categoria (nombre, descripcion)
VALUES 
('Bebidas Calientes', 'Cafés, infusiones y más'),
('Postres', 'Dulces y repostería artesanal');

-- SUBCATEGORÍAS
INSERT INTO subcategoria (id_categoria, nombre)
VALUES 
(1, 'Café'),
(1, 'Té'),
(2, 'Pasteles'),
(2, 'Galletas');

-- PRODUCTOS
INSERT INTO productos (nombre, descripcion, precio, imagen, id_categoria, id_subcategoria)
VALUES 
('Capuchino', 'Café espumoso con leche', 3.50, 'capuchino.jpg', 1, 1),
('Té Verde', 'Infusión natural y saludable', 2.00, 'te_verde.jpg', 1, 2),
('Brownie', 'Delicioso brownie de chocolate', 2.50, 'brownie.jpg', 2, 4),
('Tarta de Queso', 'Tarta de queso casera', 3.00, 'tarta_queso.jpg', 2, 3);

-- PEDIDOS
INSERT INTO pedido (id_usuario, estado)
VALUES 
(1, 'pendiente'),
(1, 'confirmado');

-- DETALLE PEDIDO
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal)
VALUES 
(1, 1, 2, 7.00),
(1, 3, 1, 2.50),
(2, 2, 1, 2.00);

-- PAGOS
INSERT INTO pago (id_pedido, metodo_pago, estado_pago, monto_total)
VALUES 
(1, 'tarjeta', 'pagado', 9.50),
(2, 'efectivo', 'pendiente', 2.00);

-- CONTACTENOS
INSERT INTO contactenos (nombre, correo, mensaje)
VALUES 
('Carlos Mena', 'carlos@correo.com', 'Excelente atención y productos!');
```
## 🛠️ Datos para insertar a la base de datos

```sql
-- USUARIOS
INSERT INTO usuario (nombre, apellido, correo, telefono, contrasena, rol)
VALUES 
('Ana', 'Ramírez', 'ana@correo.com', '0987654321', 'contrasena123', 'cliente'),
('Luis', 'Pérez', 'luis@correo.com', '0976543210', 'contrasena456', 'admin');

-- CATEGORÍAS
INSERT INTO categoria (nombre, descripcion)
VALUES 
('Bebidas Calientes', 'Cafés, infusiones y más'),
('Postres', 'Dulces y repostería artesanal');

-- SUBCATEGORÍAS
INSERT INTO subcategoria (id_categoria, nombre)
VALUES 
(1, 'Café'),
(1, 'Té'),
(2, 'Pasteles'),
(2, 'Galletas');

-- PRODUCTOS
INSERT INTO productos (nombre, descripcion, precio, imagen, id_categoria, id_subcategoria)
VALUES 
('Capuchino', 'Café espumoso con leche', 3.50, 'capuchino.jpg', 1, 1),
('Té Verde', 'Infusión natural y saludable', 2.00, 'te_verde.jpg', 1, 2),
('Brownie', 'Delicioso brownie de chocolate', 2.50, 'brownie.jpg', 2, 4),
('Tarta de Queso', 'Tarta de queso casera', 3.00, 'tarta_queso.jpg', 2, 3);

-- PEDIDOS
INSERT INTO pedido (id_usuario, estado)
VALUES 
(1, 'pendiente'),
(1, 'confirmado');

-- DETALLE PEDIDO
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal)
VALUES 
(1, 1, 2, 7.00),
(1, 3, 1, 2.50),
(2, 2, 1, 2.00);

-- PAGOS
INSERT INTO pago (id_pedido, metodo_pago, estado_pago, monto_total)
VALUES 
(1, 'tarjeta', 'pagado', 9.50),
(2, 'efectivo', 'pendiente', 2.00);

-- CONTACTENOS
INSERT INTO contactenos (nombre, correo, mensaje)
VALUES 
('Carlos Mena', 'carlos@correo.com', 'Excelente atención y productos!');
```
