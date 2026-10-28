# Karate — Demo API Demoblaze

![Java](https://img.shields.io/badge/Java-17-blue)
![Karate](https://img.shields.io/badge/Karate-1.5.2-green)
![JUnit 5](https://img.shields.io/badge/JUnit-5.11.4-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-9.8.2-darkgreen)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Pruebas de API con [Karate](https://karatelabs.github.io/karate/) contra
[api.demoblaze.com](https://api.demoblaze.com): login y registro, incluidos los casos de error.
Los escenarios se ejecutan en paralelo y el informe HTML lo genera Karate.

## Qué incluye

- **7 escenarios** en dos features: 4 de login y 3 de registro.
- **Validación de respuestas** con `match`, incluyendo mensajes de error de la API.
- **Datos y cabeceras** en JSON reutilizables desde los features.
- **Ejecución en paralelo** con 5 hilos y reporte HTML nativo de Karate.
- **Gradle 9.8.2** con wrapper y toolchain de Java 17.

## Requisitos

- **JDK 17**. El build usa toolchain, así que Gradle necesita un JDK 17 disponible.
- **Gradle**: no hace falta instalarlo; el wrapper va incluido.
- **Acceso a internet**: los tests llaman a la API pública de Demoblaze.

## Cómo se ejecuta

```sh
./gradlew test
```

El informe queda en `build/karate-reports/karate-summary.html`. También se genera el JSON de
Cucumber en `build/karate-reports/`.

Para ejecutar un feature concreto:

```sh
./gradlew test -Dkarate.options="classpath:com/cedaniel200/api/features/login.feature"
```

## Estructura

```text
src/test/java
├── karate-config.js                  # URL base y entorno
└── com/cedaniel200/api
    ├── features
    │   ├── login.feature
    │   ├── signup.feature
    │   ├── data/users.json           # usuarios de prueba
    │   └── headers/header.json       # cabeceras HTTP
    ├── runners/KarateTestSuite.java  # ejecución paralela
    └── utils/UsernameRandom.java     # usuario único para el registro
```

## La API

| Método | Ruta | Caso | Respuesta esperada |
|---|---|---|---|
| `POST` | `/login` | Credenciales correctas | `Auth_token` |
| `POST` | `/login` | Usuario inexistente | `User does not exist.` |
| `POST` | `/login` | Contraseña incorrecta | `Wrong password.` |
| `POST` | `/login` | Falta la contraseña | `Bad parameter, missing username` |
| `POST` | `/signup` | Usuario nuevo | `200` sin `errorMessage` |
| `POST` | `/signup` | Usuario existente | `This user already exist.` |
| `POST` | `/signup` | Faltan datos | `Bad parameter, missing username or password` |

## Ten en cuenta

- **La API es pública y externa.** Si Demoblaze no responde, los tests fallan por red, no por el
  código.
- **El registro crea usuarios reales** en la API con un nombre aleatorio. Es un entorno de demo,
  no uses datos personales.
- **No hay credenciales en el repositorio.** El usuario de login es el de la propia API de ejemplo.

## Licencia

[MIT](LICENSE) © Cesar Daniel
