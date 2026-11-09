package co.edu.demoacademico.grupos;

import co.edu.demoacademico.asignaturas.AsignaturaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "grupos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_grupo_codigo_asignatura", columnNames = {"codigo", "id_asignatura"})
})
public class GrupoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "maximo_cupos", nullable = false)
    private Long maximoCupos;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asignatura", nullable = false, foreignKey = @ForeignKey(name = "fk_grupo_asignatura"))
    private AsignaturaEntity asignatura;

    public GrupoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setMaximoCupos(Long cupoMaximo) {
        this.maximoCupos = cupoMaximo;
    }

    public AsignaturaEntity getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(AsignaturaEntity asignatura) {
        this.asignatura = asignatura;
    }
}
