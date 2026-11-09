package co.edu.demoacademico.asignaturas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Long> {
    boolean existsByCodigo(String codigo);
}
