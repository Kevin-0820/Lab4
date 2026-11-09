package co.edu.demoacademico.grupos;

import co.edu.demoacademico.asignaturas.AsignaturaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GrupoHandler {
    private final GrupoService grupoService;

    public GrupoHandler(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    private GrupoDTO toDto(GrupoEntity grupoEntity) {
        GrupoDTO grupoDTO = new GrupoDTO();

        grupoDTO.setId(grupoEntity.getId());
        grupoDTO.setCodigo(grupoEntity.getCodigo());
        grupoDTO.setMaximoCupos(grupoEntity.getMaximoCupos());
        grupoDTO.setIdAsignatura(grupoEntity.getAsignatura().getId());
        grupoDTO.setNombreAsignatura(grupoEntity.getAsignatura().getNombre());

        return grupoDTO;
    }

    public GrupoDTO create(GrupoCreateDTO in) {
        AsignaturaEntity asignatura = new AsignaturaEntity();

        asignatura.setId(in.getIdAsignatura());

        GrupoEntity grupoEntity = new GrupoEntity();

        grupoEntity.setCodigo(in.getCodigo());
        grupoEntity.setMaximoCupos(in.getMaximoCupos());
        grupoEntity.setAsignatura(asignatura);

        return toDto(grupoService.create(grupoEntity));
    }

    public GrupoDTO findById(Long id) {
        return toDto(grupoService.findById(id));
    }

    public Page<GrupoDTO> findAll(Pageable pageable) {
        return grupoService.findAll(pageable).map(this::toDto);
    }

    public void delete(Long id) {
        grupoService.delete(id);
    }
}
