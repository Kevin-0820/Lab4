package co.edu.demoacademico.estudiantes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstudianteService {
    EstudianteEntity create(EstudianteEntity estudiante);

    EstudianteEntity findById(Long id);

    Page<EstudianteEntity> findAll(Pageable pageable);

    EstudianteEntity update(Long id, EstudianteEntity estudiante);

    void delete(Long id);

    EstudianteEntity findByEmail(String email);
}
