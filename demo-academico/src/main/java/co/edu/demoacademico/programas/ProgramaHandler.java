package co.edu.demoacademico.programas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProgramaHandler {
    private final ProgramaService programaService;

    public ProgramaHandler(ProgramaService programaService) {
        this.programaService = programaService;
    }

    private ProgramaDTO toDto(ProgramaEntity programaEntity) {
        ProgramaDTO programaDTO = new ProgramaDTO();

        programaDTO.setId(programaEntity.getId());
        programaDTO.setCodigo(programaEntity.getCodigo());
        programaDTO.setNombre(programaEntity.getNombre());

        return programaDTO;
    }

    public ProgramaDTO create(ProgramaCreateDTO in) {
        ProgramaEntity programaEntity = new ProgramaEntity();

        programaEntity.setCodigo(in.getCodigo());
        programaEntity.setNombre(in.getNombre());

        return toDto(programaService.create(programaEntity));
    }

    public ProgramaDTO findById(Long id) {
        return toDto(programaService.findById(id));
    }

    public Page<ProgramaDTO> findAll(Pageable pageable) {
        return programaService.findAll(pageable).map(this::toDto);
    }

    public ProgramaDTO update(Long id, ProgramaUpdateDTO in) {
        ProgramaEntity programaEntity = new ProgramaEntity();

        programaEntity.setNombre(in.getNombre());

        return toDto(programaService.update(id, programaEntity));
    }

    public void delete(Long id) {
        programaService.delete(id);
    }
}
