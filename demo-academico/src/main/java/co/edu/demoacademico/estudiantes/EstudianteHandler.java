package co.edu.demoacademico.estudiantes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EstudianteHandler {
    private final EstudianteService estudianteService;

    public EstudianteHandler(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    private EstudianteDTO toDto(EstudianteEntity estudianteEntity) {
        EstudianteDTO estudianteDTO = new EstudianteDTO();

        estudianteDTO.setId(estudianteEntity.getId());
        estudianteDTO.setNombre(estudianteEntity.getNombre());
        estudianteDTO.setEmail(estudianteEntity.getEmail());

        return estudianteDTO;
    }

    public EstudianteDTO create(EstudianteCreateDTO in) {
        EstudianteEntity estudianteEntity = new EstudianteEntity();

        estudianteEntity.setNombre(in.getNombre());
        estudianteEntity.setEmail(in.getEmail());

        return toDto(estudianteService.create(estudianteEntity));
    }

    public EstudianteDTO findById(Long id) {
        return toDto(estudianteService.findById(id));
    }

    public Page<EstudianteDTO> findAll(Pageable pageable) {
        return estudianteService.findAll(pageable).map(this::toDto);
    }

    public EstudianteDTO update(Long id, EstudianteUpdateDTO in) {
        EstudianteEntity estudianteEntity = new EstudianteEntity();

        estudianteEntity.setNombre(in.getNombre());
        estudianteEntity.setEmail(in.getEmail());

        return toDto(estudianteService.update(id, estudianteEntity));
    }

    public void delete(Long id) {
        estudianteService.delete(id);
    }

    public EstudianteDTO findByEmail(String email) {
        return toDto(estudianteService.findByEmail(email));
    }
}
