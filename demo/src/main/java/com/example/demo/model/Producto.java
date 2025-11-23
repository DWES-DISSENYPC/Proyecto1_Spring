package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Producto {

    private String id;
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    private String nombre;
    private String material;
    private Double precio;
    private Double peso;
    private String color;

    public static List<Producto> ListaProductos = new ArrayList<>();

    static {

       ListaProductos.add(new Producto("1", "Silla Gamer Ergonómica", "Cuero Sintético", 1249.95, 22.5, "Negro/Rojo")) ;
        ListaProductos.add(new Producto("2", "Taza de Café", "Cerámica", 12.50, 0.35, "Blanco"));
        ListaProductos.add(new Producto("3", "Disco Duro Externo 2TB", "Plástico Reforzado", 79.99, 0.18, "Azul"));
        ListaProductos.add(new Producto("4", "Mochila para Portátil", "Nylon Impermeable", 55.00, 0.8, "Gris Oscuro"));
        ListaProductos.add(new Producto("5", "Lámpara de Escritorio LED", "Aluminio", 34.90, 1.2, "Plateado"));

    }

    public Producto() {

        precio = 0.0;
        peso = 0.0;
        

    }


    public Producto(String id, String nombre, String material, Double precio, Double peso, String color) {
        this.id= id;
        this.nombre = nombre;
        this.material = material;
        this.precio = precio;
        this.peso = peso;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    // Como pide el ejercicio, generamos aqui la lista aleatoria
    public static List<Producto> obtenerProdutosDestacados(int i) {

        List<Producto> lista = new ArrayList<>(ListaProductos); // Instanciamos la lista estatica que ya existe en la clase

        Collections.shuffle(lista); // La desordenamos con este metodo de la clase Collections

        int limite = Math.min(i, lista.size()); // Nos aseguramos que i no supera al tammaño de la lista y nos quedamos con lo mas pequeño

        return lista.subList(0, limite); // Devolvemos una sub lista desde el primer elemento "0" hasta el elemento "limite"
       
    }

    

}
