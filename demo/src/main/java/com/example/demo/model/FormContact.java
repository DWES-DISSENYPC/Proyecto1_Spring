package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID; // Para generar un ID simple

/**
 * Clase que modela los datos de contacto introducidos por el usuario
 * y contiene las anotaciones de validación.
 */
public class FormContact {
    
    // Identificador único (aunque no es estrictamente necesario para el ejercicio,
    // es una buena práctica para DTOs que eventualmente se guardarán)
    private final String id;

    // VALIDACIÓN: No puede estar vacío (incluye nulo y cadena vacía después de trim)
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    // VALIDACIÓN: No puede estar vacío Y debe tener formato de email válido
    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "El formato del email es incorrecto.")
    private String email;

    // VALIDACIÓN: No puede estar vacío
    @NotBlank(message = "El mensaje es obligatorio.")
    private String mensaje;

    public FormContact() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "FormContact [ID=" + id + ", Nombre=" + nombre + ", Email=" + email + ", Mensaje=" + mensaje + "]";
    }
}