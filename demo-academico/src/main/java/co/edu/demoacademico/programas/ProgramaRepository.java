package co.edu.demoacademico.programas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<ProgramaEntity, Long> {
    boolean existsByCodigo(String codigo);
}
