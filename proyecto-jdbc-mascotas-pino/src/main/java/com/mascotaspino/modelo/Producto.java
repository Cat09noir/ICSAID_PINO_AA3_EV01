package com.mascotaspino.modelo;

import java.math.BigDecimal;

/**
 * Representa un producto del catálogo de Mascotas Pino.
 */
public class Producto {

    private int idProducto;
    private String imagen;
    private String nombre;
    private int idCategoria;
    private String categoriaNombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;

    public Producto() {
    }

    public Producto(String imagen, String nombre, int idCategoria,
                    String descripcion, BigDecimal precio, int stock) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Producto{"
                + "idProducto=" + idProducto
                + ", nombre='" + nombre + '\''
                + ", categoria='" + categoriaNombre + '\''
                + ", precio=" + precio
                + ", stock=" + stock
                + '}';
    }
}
