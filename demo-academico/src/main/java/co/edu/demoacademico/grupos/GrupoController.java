package co.edu.demoacademico.grupos;

import co.edu.demoacademico.common.api.ApiResponse;
import co.edu.demoacademico.common.api.ResponseBuilder;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {
    private final GrupoHandler grupoHandler;

    public GrupoController(GrupoHandler grupoHandler) {
        this.grupoHandler = grupoHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GrupoDTO>> create(@Valid @RequestBody GrupoCreateDTO in) {
        return ResponseBuilder.created("Se ha creado correctamente el grupo.", grupoHandler.create(in));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GrupoDTO>> findById(@PathVariable Long id) {
        return ResponseBuilder.ok("OK", grupoHandler.findById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<GrupoDTO>>> findAll(@ParameterObject Pageable pageable) {
        return ResponseBuilder.ok("OK", grupoHandler.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        grupoHandler.delete(id);

        return ResponseBuilder.ok("Se ha eliminado correctamente el grupo.", null);
    }
}
