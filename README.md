# API de Gestión de Exámenes (Evaluaciones)

Este microservicio permite administrar los exámenes y conectarse con el sistema de usuarios a través de un cliente REST.

---

## Tecnologías Usadas

- Java 17  
- Spring Boot 3+  
- Spring Data JPA  
- MySQL Database  
- Docker  
- Lombok  
- Bean Validation con `@Valid`  
- Spring RestTemplate (para comunicación con microservicio de usuarios)

---

## Endpoints

### Obtener todos los exámenes

**GET** `/api/v1/exams`  
Devuelve una lista de todos los exámenes.

---

### Obtener examen por ID

**GET** `/api/v1/exams/{id}`

- Parámetro: `id` (Long)  
- Devuelve los datos del examen o `404 Not Found` si no existe.

---

### Agregar un examen

**POST** `/api/v1/exams`

- Parámetros opcionales:
  - `userId` (UUID): ID del usuario que crea el examen.
- Body (JSON):

```json
{
  "name": "Examen Matemáticas",
  "description": "Examen de álgebra",
  "examDate": "2024-12-01",
  "maxScore": 100
}
```

- Si `userId` es proporcionado, se consulta el microservicio de usuarios para obtener el email del usuario creador.
- Devuelve el examen creado.
- Valida campos como `name` (no nulo) y `maxScore` (positivo o cero).

---

### Actualizar examen

**PUT** `/api/v1/exams/{id}`

- Parámetro: `id` (Long)  
- Body igual al de POST (los campos se actualizan parcialmente si están presentes).  
- Devuelve el examen actualizado o `404 Not Found` si no existe.

---

### Eliminar examen

**DELETE** `/api/v1/exams/{id}`

- Parámetro: `id` (Long)  
- Devuelve código `204 No Content` si fue eliminado.  
- Devuelve `404 Not Found` si no existe el examen.

---

## Manejo de Errores

- `404 Not Found`: Cuando no se encuentra el recurso solicitado.
- `400 Bad Request`: Para validaciones de entrada no válidas (definido por `BadRequestException`).

Los errores incluyen mensajes descriptivos y timestamp.

---

## Comunicación con Microservicio de Usuarios

- Se utiliza `RestTemplate` para hacer solicitudes HTTP al servicio de usuarios.
- El endpoint del servicio se configura con la propiedad:

```properties
users.api.url=http://localhost:8081
```

---

## 🐳 Docker

### Construir imagen

```bash
docker build -t grades-api .
```

### Correr contenedor

```bash
docker run -p 8084:8084 grades-api
```

---

## Recomendaciones

- Usa `@Valid` para asegurar la integridad de datos en futuras extensiones.
- Configura correctamente el archivo `application.properties` para la base de datos y URL del microservicio de usuarios.

Ejemplo de configuración:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/grades_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

users.api.url=http://localhost:8084
```

---

## Autores

**Nombre:** Leonel Briones / Jaime Loff
