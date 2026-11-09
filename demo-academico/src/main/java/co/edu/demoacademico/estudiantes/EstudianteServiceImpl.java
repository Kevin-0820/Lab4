package co.edu.demoacademico.estudiantes;

import co.edu.demoacademico.common.exception.BusinessException;
import co.edu.demoacademico.common.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService, EstudianteQueryPort {
    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public EstudianteEntity create(EstudianteEntity estudiante) {
        if (estudianteRepository.existsByEmail(estudiante.getEmail())) {
            throw new BusinessException("Ya existe un estudiante con el email: " + estudiante.getEmail() + ".");
        }

        return estudianteRepository.save(estudiante);
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteEntity findById(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con ID: " + id + "."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstudianteEntity> findAll(Pageable pageable) {
        return estudianteRepository.findAll(pageable);
    }

    @Override
    public EstudianteEntity update(Long id, EstudianteEntity estudiante) {
        EstudianteEntity existingEstudiante = findById(id);

        if (estudianteRepository.existsByEmailAndIdNot(estudiante.getEmail(), id)) {
            throw new BusinessException("Ya existe otro estudiante con el email: " + estudiante.getEmail() + ".");
        }

        existingEstudiante.setNombre(estudiante.getNombre());
        existingEstudiante.setEmail(estudiante.getEmail());

        return estudianteRepository.save(existingEstudiante);
    }

    @Override
    public void delete(Long id) {
        EstudianteEntity existingEstudiante = findById(id);

        estudianteRepository.delete(existingEstudiante);
    }

    @Override
    public EstudianteEntity findByEmail(String email) {
        return estudianteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con email: " + email + "."));
    }
}
