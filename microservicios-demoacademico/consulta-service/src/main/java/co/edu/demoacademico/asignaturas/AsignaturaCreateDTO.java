package co.edu.demoacademico.asignaturas;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AsignaturaCreateDTO {
    @NotBlank(message = "El código es requerido.")
    private String codigo;

    @NotBlank(message = "El nombre es requerido.")
    private String nombre;

    @NotNull(message = "La cantidad de créditos es requerida.")
    @Min(value = 1, message = "El mínimo de créditos es 1.")
    private Integer creditos;

    @NotNull(message = "El id del programa es requerido.")
    private Long idPrograma;

    public AsignaturaCreateDTO() {
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

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }
}
