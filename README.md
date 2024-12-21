<img width="1174" alt="Captura de pantalla 2024-12-20 a las 12 12 49" src="https://github.com/user-attachments/assets/4c581711-c4ec-4566-90b7-9e564464eeda" /># soulSong - The no gender dating app

App de citas que relaciona a sus usuarios según sus gustos musicales
y el uso de otras herramientas como la ia

# Estado Actual del Proyecto y Próximos Pasos

## SoulSong API

SoulSong es una API desarrollada con Spring Boot que permite gestionar usuarios, perfiles y canciones en una plataforma inspirada en el poder de la música. El proyecto está diseñado para cumplir con los requisitos técnicos de una prueba, priorizando la funcionalidad, la organización, y la claridad.

## Estado Actual del Proyecto

### 1. Funcionalidades Completadas


#### CRUD Completo:

- Implementado para las entidades User, Profile y Song.
- Endpoints:
  - POST /users, GET /users, PUT /users/{id}, DELETE /users/{id}.
  - Similar para profiles y songs.
- DTOs para entrada/salida de datos.

#### Relaciones entre Entidades:

- Relación 1:N entre User y Profile.
- Relación N:M entre User y Song (usuarios con canciones favoritas).

#### Frontend Básico:

- React para interfaz de usuario.
- CRUD integrado con las APIs para usuarios, perfiles y canciones.
- Diseño UI/UX básico, con navegación y responsividad parcial (pendiente de optimización).

### 2. Funcionalidades Técnicas Implementadas

#### DTOs y Mappers:

- Separación clara entre entidades y datos expuestos a la API.
- Uso de DTOs para transferencia de datos.
- Conversión manual entre DTOs y entidades.

#### Manejo de Excepciones:

- Controlador global con @RestControllerAdvice.
- Respuestas personalizadas para errores comunes (400, 404, 500).

#### Uso de Enumerados y Fechas:

- Enumerado para el estado de usuarios (ACTIVE, INACTIVE).
- Uso de LocalDateTime para fechas de creación y actualización.

#### Documentación Inicial:

- Endpoints parcialmente documentados con Swagger (pendiente de completar).

## Lo que Falta Implementar

### Prioridad Alta

#### Frontend Completo:

- Mejorar el diseño del menú responsive (hamburguesa) en el frontend.
- Armonizar estilos globales para una experiencia UI/UX impactante.
- Finalizar interacción con el backend para perfiles y canciones.

#### Pruebas:

- **Unitarias**: Implementar con JUnit y Mockito para probar la lógica de negocio.
- **Integración**: Usar RestAssured para probar endpoints.

#### Documentación Completa:

- Finalizar documentación de Swagger/OpenAPI.
- Agregar ejemplos de entrada y salida en todos los endpoints.

### Prioridad Media

#### Paginación y Filtros:

- Implementar paginación en los endpoints GET para users, profiles, y songs.
- Añadir filtros (por ejemplo, buscar perfiles por bio).

#### Seguridad:

- Implementar Spring Security con autenticación básica o JWT.
- Roles USER y ADMIN para proteger endpoints sensibles.

#### Docker:

- Crear un archivo Dockerfile para producción.
- Configurar Docker Compose para desarrollo y pruebas.

### Prioridad Baja

#### Gestión de Imágenes:

- Permitir subida de imágenes (avatares para perfiles o portadas de canciones).
- Almacenamiento en local o en un servicio externo como S3.

#### Pipeline CI/CD:

- Configurar un pipeline para integración y despliegue continuo.

## Recomendaciones de Priorización

### Pruebas y Seguridad:

- Las pruebas (unitarias e integración) y la autenticación son esenciales para asegurar la calidad y la protección de la API.

### Frontend Pulido:

- Completar y optimizar el diseño del frontend para ofrecer una experiencia de usuario impecable.

### Documentación:

- Completar Swagger y README antes de la presentación final.

### Dockerización:

- Aunque no es crítico, agrega puntos extra y demuestra dominio técnico.

## Estructura del Proyecto

### Entidades

- **User**: Representa a los usuarios con sus datos básicos.
- **Profile**: Información detallada asociada a un usuario.
- **Song**: Canciones favoritas que los usuarios pueden gestionar.

### Modelo Relacional

#### Relaciones:

- Un User puede tener un Profile (1:1).
- Un User puede tener muchas Songs (N:M).

## Pendientes Técnicos

- **Estilos Responsive**: Mejorar el diseño del menú y las tarjetas en pantallas pequeñas.
- **Swagger Completo**: Detallar cada endpoint.
- **Mejoras de CI/CD**: Configurar pipeline de GitHub Actions para automatizar pruebas y despliegues.

## Pasos para Ejecutar

### Backend:

#### Configurar el entorno:

```bash
mvn spring-boot:run
