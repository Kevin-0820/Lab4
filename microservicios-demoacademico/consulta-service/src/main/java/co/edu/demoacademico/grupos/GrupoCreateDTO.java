package co.edu.demoacademico.grupos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GrupoCreateDTO {
    @NotBlank(message = "El código es requerido.")
    private String codigo;

    @NotNull(message = "La cantidad máxima de cupos es requerida.")
    @Min(value = 1, message = "El mínimo de cupos es 1.")
    private Long maximoCupos;

    @NotNull(message = "El id de la asignatura es requerido.")
    private Long idAsignatura;

    public GrupoCreateDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getMaximoCupos() {
        return maximoCupos;
    }

    public void setMaximoCupos(Long maximoCupos) {
        this.maximoCupos = maximoCupos;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }
}
