package co.edu.demoacademico.matriculas;

import jakarta.validation.constraints.NotNull;

public class MatriculaCreateDTO {
    @NotNull(message = "El id del estudiante es requerido.")
    private Long idEstudiante;

    @NotNull(message = "El id del grupo es requerido.")
    private Long idGrupo;

    public MatriculaCreateDTO() {
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }
}
