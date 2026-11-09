package co.edu.demoacademico.programas;

import jakarta.validation.constraints.NotBlank;

public class ProgramaUpdateDTO {
    @NotBlank(message = "El nombre es requerido.")
    private String nombre;

    public ProgramaUpdateDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
