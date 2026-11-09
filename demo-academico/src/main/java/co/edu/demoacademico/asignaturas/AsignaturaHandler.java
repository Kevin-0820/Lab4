package co.edu.demoacademico.asignaturas;

import co.edu.demoacademico.programas.ProgramaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaHandler {
    private final AsignaturaService asignaturaService;

    public AsignaturaHandler(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    private AsignaturaDTO toDto(AsignaturaEntity asignaturaEntity) {
        AsignaturaDTO asignaturaDTO = new AsignaturaDTO();

        asignaturaDTO.setId(asignaturaEntity.getId());
        asignaturaDTO.setCodigo(asignaturaEntity.getCodigo());
        asignaturaDTO.setNombre(asignaturaEntity.getNombre());
        asignaturaDTO.setCreditos(asignaturaEntity.getCreditos());
        asignaturaDTO.setIdPrograma(asignaturaEntity.getPrograma().getId());
        asignaturaDTO.setNombrePrograma(asignaturaEntity.getPrograma().getNombre());

        return asignaturaDTO;
    }

    public AsignaturaDTO create(AsignaturaCreateDTO in) {
        ProgramaEntity programa = new ProgramaEntity();

        programa.setId(in.getIdPrograma());

        AsignaturaEntity asignaturaEntity = new AsignaturaEntity();

        asignaturaEntity.setCodigo(in.getCodigo());
        asignaturaEntity.setNombre(in.getNombre());
        asignaturaEntity.setCreditos(in.getCreditos());
        asignaturaEntity.setPrograma(programa);

        return toDto(asignaturaService.create(asignaturaEntity));
    }

    public AsignaturaDTO findById(Long id) {
        return toDto(asignaturaService.findById(id));
    }

    public Page<AsignaturaDTO> findAll(Pageable pageable) {
        return asignaturaService.findAll(pageable).map(this::toDto);
    }

    public void delete(Long id) {
        asignaturaService.delete(id);
    }
}
