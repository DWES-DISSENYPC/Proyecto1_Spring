package com.example.demo.controler;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Producto;


@Controller
public class controlador {

    // Este primera parte del ejercicio, 
    // Define una ruta en un controlador que atienda a "/" y devuelva la vista welcome.
    // Crea una vista Thymeleaf llamada welcome.html donde aparezca el mensaje:
    // ¡Bienvenido a la tienda <nombre_tienda>!.
    // Al usar @GetMappint("/{nombre}") estamos esperando que la ruta sea / y se le añada un texto
    // Si la ruta no fuera la raiz "/" por ejemplo "/directorio" se esperaria que la ruta fuera "/directorionombre"
    //todo seguido.
    @GetMapping("/{nombre}")
    public String welcome(@PathVariable String nombre, Model model) {
        model.addAttribute("nombre", nombre);
        return "welcome"; // ver welcome.html para saber mas
    }

    @GetMapping("/productos")
    public String ListaProductos(Model model) { // No capturamos ningun valor de la ruta
        List<Producto> lista = Producto.ListaProductos; // Ver Producto.java para saber mas
        model.addAttribute("productos", lista);
        return "productos"; // ver productos.html para sabe mas
    }

    @GetMapping("/product-info")
    public String informacionProducto(Model model) {
        
        Producto p = new Producto("teclado", "pvc", 40.32, 0.25, "negro");
        model.addAttribute("producto", p);
        return "product-info";
    }

    // Ejercicio 1.2

    
    
    
    



}
