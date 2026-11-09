# Lab 04 – Microservicios demo-académico (auth-service + consulta-service)

Solución con dos microservicios Spring Boot 3.2.5 / Java 21:

- **auth-service** (puerto 8081): autentica usuarios guardados en H2, valida contraseñas con BCrypt y genera/valida tokens JWT.
- **consulta-service** (puerto 8082): proyecto base `demo-academico` (https://github.com/ojugama/demo-academico-application) con OpenFeign, Spring Security y un filtro JWT que protege `/api/estudiantes`.

```
microservicios-demoacademico/
├── docker-compose.yml
├── auth-service/       (pom.xml, Dockerfile, src/)
└── consulta-service/   (pom.xml, Dockerfile, src/)
```

## Requisitos

- Java 21
- Maven 3.9+
- Docker Desktop (con WSL 2 en Windows)

## Ejecutar localmente (sin Docker)

Abrir dos terminales.

```bash
cd auth-service
mvn clean package -DskipTests
java -jar target/auth-service-0.0.1-SNAPSHOT.jar
```

```bash
cd consulta-service
mvn clean package -DskipTests
java -jar target/consulta-service-0.0.1-SNAPSHOT.jar
```

Primero se levanta auth-service y después consulta-service. Localmente consulta-service usa `services.auth.url = http://localhost:8081`.

## Ejecutar con Docker Compose

Desde la carpeta `microservicios-demoacademico`:

```bash
cd auth-service && mvn clean package -DskipTests && cd ..
cd consulta-service && mvn clean package -DskipTests && cd ..
docker compose up --build
```

Verificar contenedores:

```bash
docker ps
```

Dentro de Docker, consulta-service llama a auth-service por el nombre del servicio: `SERVICES_AUTH_URL=http://auth-service:8081`.

Para detener: `docker compose down`.

## Variantes de Dockerfile (comparación de enfoques)

En `auth-service/` hay tres formas de construir la imagen:

| Archivo | Enfoque | Tamaño medido |
|---|---|---|
| `Dockerfile.single` | Compila dentro del Dockerfile (Maven + JDK en la imagen final) | 1.07 GB |
| `Dockerfile` | Compilación separada: copia el JAR generado antes con Maven | 831 MB |
| `Dockerfile.multistage` | Multi-stage: etapa build con Maven, etapa runtime solo con JRE | 564 MB |

```bash
cd auth-service
docker build -f Dockerfile.single -t lab04-auth:single .
docker build -f Dockerfile -t lab04-auth:jar .
docker build -f Dockerfile.multistage -t lab04-auth:multistage .
docker images lab04-auth
```

## URLs

| Recurso | URL |
|---|---|
| Swagger auth-service | http://localhost:8081/swagger-ui.html |
| Swagger consulta-service | http://localhost:8082/swagger-ui.html |
| H2 Console auth-service | http://localhost:8081/h2-console (JDBC URL `jdbc:h2:mem:authdb`, usuario `sa`, sin contraseña) |
| H2 Console consulta-service | http://localhost:8082/h2-console (JDBC URL `jdbc:h2:mem:demoacademico`, usuario `sa`, sin contraseña) |

## Usuarios de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| admin | Admin2026* | ADMIN |
| docente | Docente2026* | DOCENTE |
| estudiante1 | Estu2026* | ESTUDIANTE |

## Flujo correcto con el botón Authorize

1. Abrir el Swagger de auth-service y ejecutar `POST /auth/login` con:
   ```json
   { "username": "admin", "password": "Admin2026*" }
   ```
2. Copiar **solo** el valor del campo `token` (sin comillas y sin la palabra `Bearer`).
3. (Opcional) Probar `GET /auth/validate` pegando el token en el parámetro `token`.
4. Abrir el Swagger de consulta-service y pulsar **Authorize**.
5. Pegar solo el token, pulsar **Authorize** y luego **Close**.
6. Ejecutar `GET /api/estudiantes`. Swagger envía `Authorization: Bearer <token>` automáticamente.

Respuestas esperadas:

| Caso | Resultado |
|---|---|
| Token válido | 200 con la lista paginada de estudiantes |
| Sin autorizar | 401 `Token no enviado o formato inválido` |
| Token inválido o vencido | 401 `Token inválido o expirado` |
