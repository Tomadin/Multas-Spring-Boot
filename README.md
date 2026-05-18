# Sistema de Multas Viales

Aplicación web para la gestión de actas de constatación de infracciones de tránsito. Desarrollada como proyecto integrador para la materia de Programación.

## Tecnologías

**Backend**
- Java 17
- Spring Boot 3.2
- Spring Data JPA (Hibernate)
- MySQL 8

**Frontend**
- React 18 + Vite
- React Router v6

---

## Requisitos previos

- JDK 17 o superior
- Maven 3.8+
- MySQL 8 corriendo en `localhost:3306`
- Node.js 18+ y npm

---

## Configuración

### Base de datos

La base de datos se crea automáticamente al iniciar el backend. Solo asegurate de que MySQL esté corriendo y de que las credenciales en `src/main/resources/application.properties` sean correctas:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_multas?...
spring.datasource.username=root
spring.datasource.password=root
```

### Datos iniciales

Al iniciar por primera vez, el sistema carga automáticamente:
- 15 tipos de infracciones
- 6 autoridades de constatación
- 3 actas de ejemplo

---

## Cómo correr el proyecto

### Backend

```bash
mvn spring-boot:run
```

El servidor queda disponible en `http://localhost:8080`.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

La app queda disponible en `http://localhost:5173`.

---

## Endpoints REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/actas` | Listar todas las actas |
| POST | `/api/actas` | Crear nueva acta |
| PATCH | `/api/actas/{id}/estado` | Cambiar estado (PAGADO / CANCELADO) |
| GET | `/api/autoridades` | Listar autoridades |
| POST | `/api/autoridades` | Registrar autoridad |
| DELETE | `/api/autoridades/{dni}` | Eliminar autoridad |
| GET | `/api/infracciones` | Listar infracciones |
| POST | `/api/infracciones` | Registrar infracción |
| DELETE | `/api/infracciones/{id}` | Eliminar infracción |

---

## Estructura del proyecto

```
├── src/main/java/.../
│   ├── model/          # Entidades JPA
│   ├── repository/     # Spring Data JPA
│   ├── service/        # Lógica de negocio
│   ├── controller/     # Controladores REST
│   └── DataInitializer.java
├── src/main/resources/
│   └── application.properties
└── frontend/
    ├── src/
    │   ├── pages/      # Dashboard, Actas, Autoridades, Infracciones
    │   └── main.jsx
    └── vite.config.js
```
