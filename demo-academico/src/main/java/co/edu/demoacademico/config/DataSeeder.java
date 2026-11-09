package co.edu.demoacademico.config;

import co.edu.demoacademico.asignaturas.AsignaturaEntity;
import co.edu.demoacademico.asignaturas.AsignaturaRepository;
import co.edu.demoacademico.estudiantes.EstudianteEntity;
import co.edu.demoacademico.estudiantes.EstudianteRepository;
import co.edu.demoacademico.grupos.GrupoEntity;
import co.edu.demoacademico.grupos.GrupoRepository;
import co.edu.demoacademico.programas.ProgramaEntity;
import co.edu.demoacademico.programas.ProgramaRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataSeeder {
    @Value("${app.seed.enabled:true}")
    private boolean enabled;

    @Value("${app.seed.cantidad-estudiantes:50}")
    private int cantidadEstudiantes;

    @Value("${app.seed.cantidad-programas:3}")
    private int cantidadProgramas;

    @Value("${app.seed.cantidad-asignaturas:10}")
    private int cantidadAsignaturas;

    @Value("${app.seed.cantidad-grupos:20}")
    private int cantidadGrupos;

    @Bean
    CommandLineRunner seedAcademico(EstudianteRepository estudianteRepository,
                                    ProgramaRepository programaRepository,
                                    AsignaturaRepository asignaturaRepository,
                                    GrupoRepository grupoRepository) {
        return args -> {
            if (!enabled) return;

            if (estudianteRepository.count() > 0 || programaRepository.count() > 0 || asignaturaRepository.count() > 0 || grupoRepository.count() > 0)
                return;

            Faker faker = new Faker(Locale.of("es"));

            // 1) Estudiantes
            EstudianteEntity[] estudiantes = new EstudianteEntity[cantidadEstudiantes];
            for (int i = 0; i < cantidadEstudiantes; i++) {
                EstudianteEntity estudiante = new EstudianteEntity();

                estudiante.setNombre(faker.name().fullName());
                estudiante.setEmail(("est" + i + "_" + faker.internet().emailAddress())
                        .toLowerCase()
                        .replace(" ", "")
                        .replace("..", "."));

                estudiantes[i] = estudianteRepository.save(estudiante);
            }

            // 2) Programas
            ProgramaEntity[] programas = new ProgramaEntity[cantidadProgramas];
            for (int i = 0; i < cantidadProgramas; i++) {
                ProgramaEntity programa = new ProgramaEntity();

                programa.setCodigo("PROG-" + (i + 1));
                programa.setNombre("Programa " + faker.educator().course());

                programas[i] = programaRepository.save(programa);
            }

            // 3) Asignaturas (reparte entre programas)
            AsignaturaEntity[] asignaturas = new AsignaturaEntity[cantidadAsignaturas];
            for (int i = 0; i < cantidadAsignaturas; i++) {
                ProgramaEntity programa = programas[i % cantidadProgramas];

                AsignaturaEntity asignatura = new AsignaturaEntity();

                asignatura.setCodigo("ASIG-" + (i + 1));
                asignatura.setNombre("Asignatura " + faker.educator().course());
                asignatura.setCreditos(2 + (i % 3));
                asignatura.setPrograma(programa);

                asignaturas[i] = asignaturaRepository.save(asignatura);
            }

            // 4) Grupos (reparte entre asignaturas)
            for (int i = 0; i < cantidadGrupos; i++) {
                AsignaturaEntity asignatura = asignaturas[i % cantidadAsignaturas];

                GrupoEntity grupo = new GrupoEntity();
                grupo.setCodigo("G-" + (i + 1));
                grupo.setMaximoCupos((long) (2 + (i % 4))); // entre 2 y 5
                grupo.setAsignatura(asignatura);

                grupoRepository.save(grupo);
            }

        };
    }
}
