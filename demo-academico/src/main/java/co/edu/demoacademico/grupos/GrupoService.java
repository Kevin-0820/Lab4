package co.edu.demoacademico.grupos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrupoService {
    GrupoEntity create(GrupoEntity grupo);

    GrupoEntity findById(Long id);

    Page<GrupoEntity> findAll(Pageable pageable);

    void delete(Long id);
}
