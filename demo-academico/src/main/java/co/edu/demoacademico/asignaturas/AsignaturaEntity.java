package co.edu.demoacademico.asignaturas;

import co.edu.demoacademico.programas.ProgramaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "asignaturas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_asignatura_codigo", columnNames = {"codigo"})
})
public class AsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa", nullable = false, foreignKey = @ForeignKey(name = "fk_asignatura_programa"))
    private ProgramaEntity programa;

    public AsignaturaEntity() {
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

    public ProgramaEntity getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
    }
}
