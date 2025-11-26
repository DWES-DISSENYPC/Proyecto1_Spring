package com.example.demo.controler;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.FormContact;
import com.example.demo.model.FormEntry;
import com.example.demo.model.Producto;

import jakarta.validation.Valid;


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
        
        // Creamos un nuevo producto para pasarselo a producto-info.html con el model
        Producto p = new Producto("6", "teclado", "pvc", 40.32, 0.25, "negro");
        model.addAttribute("producto", p); //Le damos nombre para el model al nuevo producto p
        return "product-info";// Llamamos a producto-info.html
    }

    // Ejercicio 1.2

    @GetMapping("/bestProduct")
    public String bestProduct(Model model) {

        //creamos una lista que se llena con la lista estatica que hay en la clase Producto
        List<Producto> lista = Producto.obtenerProdutosDestacados(3);
        model.addAttribute("productosMejores", lista); //La metemos en el model para pòder pasarsela a welcome.html
        return "welcome";
    }

    // Método para la ruta /home
    @GetMapping("/home") //Para poder llamar a un html, es necesario haber creado un getmapping
    public String goHome() {
        // Busca y renderiza el archivo en: src/main/resources/templates/home.html
        return "home"; 
    }

    // Método para la ruta /contact
    @GetMapping("/contact") //Para poder llamar a un html, es necesario haber creado un getmapping
    public String goContact() {
        // Busca y renderiza el archivo en: src/main/resources/templates/contact.html
        return "contact";
    }
    
    
    // Ejercicio 1.3

    @GetMapping("/producto/{id}")
    public String ProductoPoId(@PathVariable String id, Model model) {

        
        List<Producto> lista = Producto.ListaProductos; // Ver Producto.java para saber mas
       // System.out.println("******************************************   " + id);
        
        for (Producto p: lista) {

               // System.out.println("----------------------------------------- " + p.getId());
            if (p.getId().equals(id)) {

                
                model.addAttribute("producto", p); // metemos en el model el producto p
                return "producto";
            }

        }

        return "redirect:/productos"; // Si no encuentra el id devuelve la lista completa
        // Con un redirect que lo que hace es no buscar una plantilla si no enviar esa url al servidor
    }
    
    
   
    // EJERCICIO 1.4 b) y c): Formulario estático y Validación

     // @GetMapping: Con este lo que hacemos es capturar la llamada al formulario que está en contact.html
     // y como en spring no se puede llamar directamente a un html lo hacemos capturando la ruta con este
     
    @GetMapping("/formularioContacto")
    public String showContactForm(Model model) {
        // Al GET se debe pasar un objeto vacío al modelo para que Thymeleaf pueda mapear los campos (*{...})
        model.addAttribute("formContact", new FormContact());
        System.out.println("DEBUG: Cargando el formulario de contacto estático.");
        return "contact"; 
    }

     // @PostMapping: Recibe la información enviada desde el formulario.
     // @param formContact El objeto FormContact que recibe los datos del formulario.
     // @param bindingResult Almacena los resultados de la validación (@Valid).
     // @return Si hay errores, vuelve a la vista contact.html.sin recargar, por lo que se mantiene los datos
     // que el cliente habia enviado mas los mensajes de error
     //  Si no hay errores, redirige o retorna éxito.
     
    @PostMapping("/formularioContacto")
    public String submitContactForm( // Todo esto son los parametros que recibe el post
            @Valid FormContact formContact, // 1. @Valid hace la validación en el objeto FormContact
            // Ademas @postMapping se encarga de coger los datos sueltos del formulario y converitlos en el objeto
            BindingResult bindingResult,     // 2. BindingResult DEBE seguir inmediatamente al objeto @Valid por que 
            // es quien recoge el resultado de la validacion
            Model model
    ) {
        // c) Validación del formulario: Comprueba si hay errores
        if (bindingResult.hasErrors()) { // La instancia de BindingResult devuelve true si hay errores de validacion
            System.err.println("ERROR: El formulario tiene errores de validación. Volviendo a la vista.");
            // Si hay errores, regresamos a la misma vista para mostrar los mensajes de error
            // y la vista mostrará todos los datos introducidos por el cliente mas los errores 
            return "contact"; // volvemos a la vista sin recargar
        }

        // b) Obtener la información del formulario: Comprobación mediante print()
        System.out.println("-----------------------------------------");
        System.out.println("¡Formulario de Contacto Recibido y Válido!");
        System.out.println("Datos obtenidos:");
        System.out.println(formContact.toString()); // Muestra los datos en la consola
        System.out.println("-----------------------------------------");

        // Después del éxito, puedes redirigir a una página de agradecimiento
        return "redirect:/welcome"; // Redirige a la página principal
    }

    // EJERCICIO 1.4 d): Formulario dinámico

     // @GetMapping: Genera un listado de FormEntry y lo pasa a la vista contact2.html.
     
    @GetMapping("/contact2")
    public String showDynamicForm(Model model) {
        // Crea la lista de elementos FormEntry dinámicamente
        // Esta lista podria tener los elementos que hiciera falta ya que el html iterará sobre ella para crear el formulario
        List<FormEntry> formFields = Arrays.asList(
                // El objeto formFields tiene 3 campos name type y label
                // El label no se le pasa en esta lista por que se crea apartir del consturctor en la propia clase
                new FormEntry("nombre", "text"),
                new FormEntry("email", "email"), 
                new FormEntry("asunto", "text"),
                new FormEntry("mensaje", "textarea") // Usaremos 'textarea' como type para el HTML
        );

        model.addAttribute("formFields", formFields); // Una vez mas pasamos al model el dato en este caso el objeto
        System.out.println("DEBUG: Cargando el formulario de contacto dinámico."); // Prueba de que estamos por el camino correcto, solo para el desarrollador
        return "contact2"; // devolvemos al contacto 2 para que se muestre la plantilla
    }
    


}
