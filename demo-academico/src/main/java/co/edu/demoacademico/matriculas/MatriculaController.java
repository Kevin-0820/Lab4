package co.edu.demoacademico.matriculas;

import co.edu.demoacademico.common.api.ApiResponse;
import co.edu.demoacademico.common.api.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    private final MatriculaHandler matriculaHandler;

    public MatriculaController(MatriculaHandler matriculaHandler) {
        this.matriculaHandler = matriculaHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MatriculaDTO>> create(@Valid @RequestBody MatriculaCreateDTO in) {
        return ResponseBuilder.created("Se ha creado correctamente la matrícula.", matriculaHandler.create(in));
    }
}
