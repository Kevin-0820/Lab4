package co.edu.demoacademico.programas;

import jakarta.validation.constraints.NotBlank;

public class ProgramaCreateDTO {
    @NotBlank(message = "El código es requerido.")
    private String codigo;

    @NotBlank(message = "El nombre es requerido.")
    private String nombre;

    public ProgramaCreateDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
