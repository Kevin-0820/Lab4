package co.edu.demoacademico.estudiantes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EstudianteCreateDTO {
    @NotBlank(message = "El nombre es requerido.")
    private String nombre;

    @NotBlank(message = "El email es requerido.")
    @Email(message = "Formato de email inválido.")
    private String email;

    public EstudianteCreateDTO() {
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
}
