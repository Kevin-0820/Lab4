package co.edu.demoacademico.asignaturas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AsignaturaService {
    AsignaturaEntity create(AsignaturaEntity asignatura);

    AsignaturaEntity findById(Long id);

    Page<AsignaturaEntity> findAll(Pageable pageable);

    void delete(Long id);
}
