package co.edu.demoacademico.estudiantes;

import co.edu.demoacademico.common.api.ApiResponse;
import co.edu.demoacademico.common.api.ResponseBuilder;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final EstudianteHandler estudianteHandler;

    public EstudianteController(EstudianteHandler estudianteHandler) {
        this.estudianteHandler = estudianteHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EstudianteDTO>> create(
            @Valid @RequestBody EstudianteCreateDTO in) {
        return ResponseBuilder.created("Se ha creado correctamente el estudiante.", estudianteHandler.create(in));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstudianteDTO>> findById(@PathVariable Long id) {
        return ResponseBuilder.ok("OK", estudianteHandler.findById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EstudianteDTO>>> findAll(@ParameterObject Pageable pageable) {
        return ResponseBuilder.ok("OK", estudianteHandler.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EstudianteDTO>> update(
            @PathVariable Long id, @Valid @RequestBody EstudianteUpdateDTO in) {
        return ResponseBuilder.ok("Se ha actualizado correctamente el estudiante.", estudianteHandler.update(id, in));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        estudianteHandler.delete(id);

        return ResponseBuilder.ok("Se ha eliminado correctamente el estudiante.", null);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<EstudianteDTO>> findByEmail(@RequestParam String email) {
        return ResponseBuilder.ok("OK", estudianteHandler.findByEmail(email));
    }
}
