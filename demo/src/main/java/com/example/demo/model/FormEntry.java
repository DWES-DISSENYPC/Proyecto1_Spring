package com.example.demo.model;

import java.util.Locale;

/**
 * Clase usada para describir y generar un campo de formulario
 * de forma dinámica (label, name y type).
 */
public class FormEntry {
    
    private String name;
    private String type;
    private String label;

    public FormEntry(String name, String type) {
        this.name = name;
        this.type = type;
        // Lógica del ejercicio: Generar el label a partir del name
        if (name != null && !name.isEmpty()) {
            String capitalizedName = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1).toLowerCase(Locale.ROOT);
            this.label = capitalizedName + ":";
        } else {
            this.label = "";
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }
    
    // Setters (opcionales, pero se incluyen por convención si fueran necesarios)
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
