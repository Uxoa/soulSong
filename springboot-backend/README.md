# soulSong - The no gender dating app

App de citas no gender que relaciona a sus usuarios según sus gustos musicales
y el uso de otras herramientas como la ia

## Historias de usuario:

### Como usuario admin:
- quiero poder crear usuarios
- quiero poder ver todos los usuarios
- quiero poder borrar un usuario por su id
- quiero poder actualizar un usuario

### Como usuario user:
- quiero poder crear mis datos de usuario
- quiero poder crear un perfil
- quiero poder ver mis datos de usuario
- quiero poder ver mi perfil
- quiero poder borrar mis datos de usuario
- quiero poder borrar mi perfil
- quiero pode actualizar mis datos de usuario
- quiero poder actualizar mi perfil

# SoulSong

SoulSong es una aplicación que utiliza la API de Spotify para analizar y sugerir canciones que se alineen con el estado emocional y las preferencias de los usuarios. La idea principal del proyecto es fomentar conexiones significativas a través de la música, integrándola en la experiencia de SoulTrust para enriquecer el análisis de perfiles y las relaciones entre usuarios.

## Características principales

1. **Integración con la API de Spotify**:
    - Conexión para autenticar usuarios a través de sus cuentas de Spotify.
    - Extracción de datos como listas de reproducción, canciones favoritas y géneros preferidos.
    - Análisis de audio para determinar características como el estado de ánimo (valence), la energía y el tempo de las canciones.

2. **Relaciones entre música y perfiles**:
    - Relación entre el perfil emocional del usuario y las características de las canciones.
    - Sugerencias personalizadas de canciones que fomenten estados emocionales positivos y saludables.
    - Análisis de compatibilidad musical entre usuarios para detectar afinidades.

3. **Análisis emocional**:
    - Creación de un índice de salud emocional basado en las preferencias musicales y el historial de escucha.
    - Inclusión de feedback visual sobre el impacto emocional de las canciones.

## Tecnologías utilizadas

- **Backend**: Java con Spring Boot.
- **Frontend**: React para una interfaz de usuario interactiva y moderna.
- **API de Spotify**: Para la integración y análisis de datos musicales.
- **Base de datos**: PostgreSQL para almacenar información de usuarios, perfiles, relaciones y preferencias musicales.

## Requisitos previos

- Cuenta de desarrollador en Spotify para obtener las credenciales necesarias.
- Entorno de desarrollo configurado con:
    - JDK 17 o superior.
    - IntelliJ IDEA o un IDE similar.
    - Node.js y npm para el frontend.
    - Docker para la base de datos (opcional).

## Instalación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/soulsong.git
   cd soulsong
   ```

2. **Configurar el backend**:
    - Añadir las credenciales de la API de Spotify en un archivo `application.properties` o como variables de entorno.
    - Ejecutar el servidor de desarrollo desde IntelliJ IDEA o con el comando:
      ```bash
      ./mvnw spring-boot:run
      ```

3. **Configurar el frontend**:
    - Navegar al directorio del frontend:
      ```bash
      cd frontend
      ```
    - Instalar dependencias:
      ```bash
      npm install
      ```
    - Iniciar el servidor de desarrollo:
      ```bash
      npm start
      ```

4. **Configurar la base de datos**:
    - Crear la base de datos en PostgreSQL o usar la configuración predeterminada del archivo `application.properties`.

## Uso

- Inicia sesión con tu cuenta de Spotify para sincronizar tus datos musicales.
- Completa el cuestionario para analizar tu perfil emocional.
- Descubre canciones y listas de reproducción sugeridas según tu estado de ánimo.
- Explora compatibilidades musicales con otros usuarios.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, crea un *fork* del repositorio, realiza tus cambios en una rama nueva y envía un *pull request*.

## Licencia

SoulSong está licenciado bajo la [MIT License](https://opensource.org/licenses/MIT).

