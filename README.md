<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-Android-blueviolet" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-brightgreen" />
  <img src="https://img.shields.io/badge/Retrofit-Networking-blue" />
  <img src="https://img.shields.io/badge/Spring%20Boot-Backend-success" />
  <img src="https://img.shields.io/badge/MySQL-Database-informational" />
</p>

## Descripción del proyecto

Clear es una aplicación móvil Android orientada a la organización personal y el bienestar, que permite gestionar tareas, estados de ánimo, diarios personales y hábitos como el consumo de agua, conectándose a un backend propio desarrollado con Spring Boot. Compuesto por:

- **Frontend Android** desarrollado en **Kotlin + Jetpack Compose**
- **Backend REST** desarrollado en **Spring Boot**
- **Base de datos MySQL**
- Comunicación vía **Retrofit**
- Arquitectura limpia y organizada (MVVM en frontend)

La app permite a los usuarios iniciar sesión, gestionar tareas con prioridades y fechas, registrar estados de ánimo, escribir entradas de diario y llevar control de hábitos diarios.

## Características actuales
- Inicio de sesión con backend real
- Gestión de tareas (crear, listar, editar, eliminar)
- Prioridades de tareas (Alta, Media, Baja)
- Registro de estados de ánimo
- Diario personal con fecha y hora
- Seguimiento de consumo de agua
- Persistencia con backend Spring Boot
- Manejo de estados con ViewModel + Coroutines
- UI moderna con Jetpack Compose

## Arquitectura del proyecto

### Frontend (Android)
app/
├── data/
│   ├── model/        Modelos (Usuario, Tarea, Diario, Prioridad)
│   ├── network/      Retrofit, APIs, adapters de fechas
│   └── repository/   Acceso a datos
├── ui/
│   ├── screens/      Pantallas Compose (Login, Tareas, Diario, etc.)
│   ├── components/   Componentes reutilizables
│   └── theme/        Tipografías, colores y estilos
├── viewmodel/        ViewModels (MVVM)
└── session/          Manejo de sesión del usuario


### Backend (Spring Boot)
src/main/java/
├── controller/   Controladores REST
├── service/      Lógica de negocio
├── repository/   JPA Repositories
└── model/        Entidades JPA


## Endpoints principales (Backend)
POST /api/usuarios/login
POST /api/usuarios/guardar
GET /api/usuarios/listar

GET /api/tareas/listar
POST /api/tareas/guardar
PUT /api/tareas/actualizar/{id}
DELETE /api/tareas/eliminar/{id}

GET /api/diario/listar
POST /api/diario/guardar

GET /api/agua/listar
POST /api/agua/guardar


## Tecnologías utilizadas
| Componente | Tecnología |
|----------|------------|
| Lenguaje Android | Kotlin |
| UI | Jetpack Compose |
| Arquitectura | MVVM |
| Networking | Retrofit + Gson |
| Backend | Spring Boot |
| ORM | Spring Data JPA |
| Base de datos | MySQL |
| Seguridad | BCrypt (hash de contraseñas) |
| Async | Kotlin Coroutines |

## Instalación y ejecución

### Backend
1. Clonar el repositorio backend
2. Crear una base de datos MySQL llamada "clear_db"
3. Configurar "application.properties"
4. Ejecutar el proyecto Spring Boot
5. Backend disponible en: http://localhost:9090

### Frontend (Android Studio)
1. Clonar el repositorio Android
2. Abrir en Android Studio
3. Usar emulador Android
4. Asegurarse que el backend esté corriendo
5. Usar la URL: http://10.0.2.2:9090
6. Ejecutar la app desde MainActivity


## Usuario de prueba
email: fran@clear.cl
password: holis123

## Roadmap / Futuras versiones

### Seguridad
- Hash + verificación correcta de contraseñas
- Tokens JWT
- Persistencia de sesión segura

### Backend
- Despliegue en la nube (Render / Railway)
- Validaciones más robustas
- Manejo de errores estandarizado

### Frontend
- Modo oscuro completo
- Animaciones y transiciones
- Mejor feedback visual (snackbars, loaders)
- Persistencia local (Room)

## Contribuciones

Las contribuciones son bienvenidas:
1. Haz fork del proyecto
2. Crea una rama (feature/nueva-funcionalidad)
3. Realiza tus cambios
4. Abre un Pull Request

## Estado del proyecto
| Módulo | Estado |
|------|--------|
| Login | Implementado |
| Tareas | Implementado |
| Diario | Implementado |
| Ánimo | Implementado |
| Consumo de agua | Implementado |
| Seguridad avanzada | En desarrollo |


## Licencia
Proyecto académico desarrollado con fines educativos.
