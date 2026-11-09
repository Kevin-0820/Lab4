package co.edu.demoacademico.matriculas;

import co.edu.demoacademico.common.exception.BusinessException;
import co.edu.demoacademico.estudiantes.EstudianteEntity;
import co.edu.demoacademico.estudiantes.EstudianteQueryPort;
import co.edu.demoacademico.grupos.GrupoEntity;
import co.edu.demoacademico.grupos.GrupoQueryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final EstudianteQueryPort estudianteQueryPort;
    private final GrupoQueryPort grupoQueryPort;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository,
                                EstudianteQueryPort estudianteQueryPort, GrupoQueryPort grupoQueryPort) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteQueryPort = estudianteQueryPort;
        this.grupoQueryPort = grupoQueryPort;
    }

    @Override
    public MatriculaEntity create(Long idEstudiante, Long idGrupo) {
        EstudianteEntity existingEstudiante = estudianteQueryPort.findById(idEstudiante);

        GrupoEntity existingGrupo = grupoQueryPort.findById(idGrupo);

        if (matriculaRepository.existsByEstudianteIdAndGrupoId(idEstudiante, idGrupo)) {
            throw new BusinessException("Ya existe una matrícula con el estudiante con el ID: " + idEstudiante
                    + ", y el grupo con el ID: " + idGrupo + ".");
        }

        long matriculados = matriculaRepository.countByGrupoId(idGrupo);
        if (matriculados >= existingGrupo.getMaximoCupos()) {
            throw new BusinessException("No hay más cupos para el grupo con el ID: " + idGrupo + ".");
        }

        MatriculaEntity matricula = new MatriculaEntity();

        matricula.setEstudiante(existingEstudiante);
        matricula.setGrupo(existingGrupo);

        return matriculaRepository.save(matricula);
    }
}
