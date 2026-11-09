package co.edu.demoacademico.asignaturas;

import co.edu.demoacademico.common.api.ApiResponse;
import co.edu.demoacademico.common.api.ResponseBuilder;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {
    private final AsignaturaHandler asignaturaHandler;

    public AsignaturaController(AsignaturaHandler asignaturaHandler) {
        this.asignaturaHandler = asignaturaHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AsignaturaDTO>> create(@Valid @RequestBody AsignaturaCreateDTO in) {
        return ResponseBuilder.created("Se ha creado correctamente la asignatura.", asignaturaHandler.create(in));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AsignaturaDTO>> findById(@PathVariable Long id) {
        return ResponseBuilder.ok("OK", asignaturaHandler.findById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AsignaturaDTO>>> findAll(@ParameterObject Pageable pageable) {
        return ResponseBuilder.ok("OK", asignaturaHandler.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        asignaturaHandler.delete(id);

        return ResponseBuilder.ok("Se ha eliminado correctamente la asignatura.", null);
    }
}
