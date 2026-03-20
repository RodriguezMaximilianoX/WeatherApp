# Weather App — Carreras Grupo Logístico

App móvil Android que muestra el clima actual basándose
en la ubicación del dispositivo, desarrollada como
prueba técnica para Carreras Grupo Logístico.

---

## Requisitos para compilar

- Android Studio Hedgehog o superior
- JDK 11
- Android SDK 36
- Conexión a internet (para cargar el ícono del clima)

## Instrucciones para compilar y ejecutar

1. Clonar el repositorio
   git clone https://github.com/RodriguezMaximilianoX/WeatherApp.git

2. Abrir el proyecto en Android Studio

3. Crear el archivo local.properties en la raíz del proyecto
   y agregar las siguientes variables:
   
   BASE_URL
   API_KEY

   Obtener valores desde la web https://openweathermap.org/api

5. Sincronizar el proyecto con Gradle
   File → Sync Project with Gradle Files

6. Ejecutar en un dispositivo o emulador con API 24+

---

## Versión mínima de Android requerida

- minSdk: 24 (Android 7.0 Nougat)
- targetSdk: 36 (Android 16)

---

## Arquitectura implementada

El proyecto sigue Clean Architecture con MVVM,
separando el código en tres capas independientes:

### Estructura de capas
```
app/
├── data/
│   ├── location/         → LocationDataSource (LocationManager)
│   ├── remote/           → WeatherApiService (Ktor)
│   │   └── dto/          → Modelos de la API
│   ├── mapper/           → Conversión Response → Domain
│   └── repository/       → Implementaciones de repositorios
│
├── domain/
│   ├── model/            → WeatherInfo, UserLocation
│   ├── repository/       → Interfaces de repositorios
│   └── usecase/          → GetWeatherUseCase
│
├── ui/
│   ├── components/       → Composables reutilizables
│   ├── navigation/       → AppNavigation, Screen
│   ├── theme/            → Colores, tipografía, tema
│   ├── SplashScreen
│   ├── WeatherScreen
│   ├── SkeletonScreen
│   ├── ErrorScreen
│   └── PermissionDeniedScreen
│
└── di/
    └── AppModule.kt      → Módulo de inyección de dependencias
```

### Flujo de datos
```
UI → ViewModel → UseCase → Repository → DataSource
                                      → LocationManager
                                      → Ktor (OpenWeather API)
```

### Regla de dependencias

Las capas externas conocen a las internas — nunca al revés.
Data depende de Domain. UI depende de Domain.
Domain no conoce ni Data ni UI.

---

## Decisiones técnicas

### Ktor
Se eligió Ktor por ser el requisito del enunciado.
El HttpClient se instancia una sola vez como singleton
en el módulo de Koin.

### Koin 
Se eligió Koin por ser el requisito del enunciado
y por su simplicidad de configuración.

### Result sobre Either
Se usó el tipo Result nativo de Kotlin para el manejo
de errores en lugar de Either de Arrow.
Permite un manejo limpio con onSuccess/onFailure/getOrElse
sin dependencias externas adicionales.

### Sealed class para estados de UI
WeatherState es una sealed class con los estados:
Loading, Success, Error, GpsDisabled y PermissionDenied.
Esto garantiza que la UI siempre maneje todos los casos
posibles en tiempo de compilación.

### Offline — sin cache local
Dado el alcance de la prueba técnica no se implementó
cache local con Room. En producción se implementaría
una estrategia Cache First para mostrar datos previos
mientras se actualiza en background.

### Lang español en OpenWeather
Se agregó el parámetro lang=es en la llamada a la API
para recibir las descripciones del clima en español
directamente desde el servidor.

---

## Stack técnico

| Categoría | Tecnología |
|-----------|------------|
| Lenguaje | Kotlin |
| UI | Jetpack Compose |
| Arquitectura | MVVM + Clean Architecture |
| DI | Koin 3.5.6 |
| Networking | Ktor 2.3.7 + Kotlin Serialization |
| Geolocalización | LocationManager |
| Imágenes | Coil |
| Navegación | Navigation Compose |
| Testing | JUnit4 + MockK + Coroutines Test |

---

## Tests

El proyecto incluye 13 tests unitarios:

- **WeatherViewModelTest** (8 tests)
  Cubre estados Loading, Success, Error, GpsDisabled,
  PermissionDenied, reintentos y llamadas múltiples.

- **GetWeatherUseCaseTest** (5 tests)
  Cubre el happy path, fallos de ubicación, fallos de API,
  verificación de coordenadas y datos correctos.

Para correr los tests:
```
./gradlew test
```
