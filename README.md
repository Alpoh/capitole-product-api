# Capitole Product API

## Read Me First

Este proyecto utiliza H2 Database como base de datos en memoria. Es importante tener las herramientas necesarias
instaladas para una mejor experiencia al ejecutar el proyecto.

## Requisitos previos

Asegúrate de tener instalado lo siguiente:

- **Java 17**: [Descargar Java](https://adoptopenjdk.net/)
- **Gradle**: [Instalar Gradle](https://gradle.org/install/)
- **Docker**: [Instalar Docker](https://docs.docker.com/get-docker/)
- **Kotlin**: [Instalar Kotlin](https://kotlinlang.org/docs/tutorials/command-line.html)

## Cómo ejecutar el proyecto

### 1. Construcción del proyecto

Desde la raíz del proyecto, ejecuta el siguiente comando para limpiar, construir y empaquetar la aplicación en una
imagen de Docker:

```bash
./gradlew clean build bootBuildImage
```

Este comando generará una imagen de Docker que puedes usar para ejecutar la aplicación.

## 2. Ejecutar la aplicación

Tienes dos opciones para ejecutar el proyecto:

### Con Docker Compose

```bash
docker-compose up
```

Esto iniciará el proyecto junto con sus dependencias, como la base de datos en memoria (H2).

## Con Gradle

```bash
./gradlew bootRun
```

Este comando iniciará la aplicación directamente desde tu entorno local sin necesidad de Docker.

## Base de datos

**Base de datos**: H2 (en memoria)

Puedes acceder a la consola H2 para inspeccionar los datos:

- **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:products_db`
- **Usuario**: `sa`
- **Contraseña**: (sin contraseña)

## Documentación de referencia

### Documentación de herramientas:

- [Documentación oficial de Gradle](https://docs.gradle.org)
- [Guía del plugin Gradle de Spring Boot](https://docs.spring.io/spring-boot/3.4.0/gradle-plugin)
- [Crear una imagen OCI](https://docs.spring.io/spring-boot/3.4.0/gradle-plugin/packaging-oci-image.html)
- [Docker Compose Support](https://docs.spring.io/spring-boot/3.4.0/reference/features/dev-services.html#features.dev-services.docker-compose)
- [Documentación de Spring Boot](https://docs.spring.io/spring-boot/3.4.0/reference/)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.0/reference/using/devtools.html)
- [Spring Web](https://docs.spring.io/spring-boot/3.4.0/reference/web/servlet.html)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

### Guías adicionales:

- [Crear y ejecutar servicios REST](https://spring.io/guides/gs/rest-service/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## Notas finales

Si tienes alguna pregunta o problema, consulta la documentación de las herramientas o ponte en contacto con el equipo de
desarrollo.


