-- Base de datos para el módulo web Mascotas Pino.
-- Importar este archivo en phpMyAdmin. Si ya existe información, hacer una copia de seguridad.
CREATE DATABASE IF NOT EXISTS db_mascotas_pino
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE db_mascotas_pino;

CREATE TABLE IF NOT EXISTS categoria (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uq_categoria_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS productos (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    id_categoria INT NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    stock INT NOT NULL DEFAULT 0,
    imagen VARCHAR(255),
    PRIMARY KEY (id),
    KEY idx_productos_categoria (id_categoria),
    CONSTRAINT fk_productos_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_productos_precio CHECK (precio >= 0),
    CONSTRAINT chk_productos_stock CHECK (stock >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT IGNORE INTO categoria (id, nombre) VALUES
    (1, 'Ropa'), (2, 'Collar'), (3, 'Accesorios'),
    (4, 'Cadena'), (5, 'Camas'), (6, 'Pecheras');

INSERT INTO productos (nombre, id_categoria, descripcion, precio, stock, imagen)
SELECT 'Pechera para perro', 6, 'Pechera resistente para perros.', 45000.00, 15,
       'https://via.placeholder.com/400x300?text=Pecheras'
WHERE NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Pechera para perro');
