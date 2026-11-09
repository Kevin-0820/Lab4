package co.edu.demoacademico.grupos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {
    boolean existsByCodigoAndAsignaturaId(String codigo, Long idAsignatura);
}
