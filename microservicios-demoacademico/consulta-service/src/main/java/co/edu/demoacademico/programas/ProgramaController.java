package co.edu.demoacademico.programas;

import co.edu.demoacademico.common.api.ApiResponse;
import co.edu.demoacademico.common.api.ResponseBuilder;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programas")
public class ProgramaController {
    private final ProgramaHandler programaHandler;

    public ProgramaController(ProgramaHandler programaHandler) {
        this.programaHandler = programaHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProgramaDTO>> create(@Valid @RequestBody ProgramaCreateDTO in) {
        return ResponseBuilder.created("Se ha creado correctamente el programa.", programaHandler.create(in));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramaDTO>> findById(@PathVariable Long id) {
        return ResponseBuilder.ok("OK", programaHandler.findById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProgramaDTO>>> findAll(@ParameterObject Pageable pageable) {
        return ResponseBuilder.ok("OK", programaHandler.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramaDTO>> update(@PathVariable Long id, @Valid @RequestBody ProgramaUpdateDTO in) {
        return ResponseBuilder.ok("Se ha actualizado correctamente el programa.", programaHandler.update(id, in));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        programaHandler.delete(id);
        return ResponseBuilder.ok("Se ha eliminado correctamente el programa.", null);
    }
}
