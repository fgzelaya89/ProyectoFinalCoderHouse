# Proyecto Final - Gestor de Ventas y Stock

Este proyecto es una aplicación backend desarrollada en Java utilizando el framework Spring Boot. Proporciona funcionalidades para gestionar ventas y el stock de productos de una tienda.

## ⚡ Características
- **Gestor de ventas:** Permite la creación, actualización y consulta de ventas realizadas.
- **Manejo de stock:** Permite consultar y actualizar el stock disponible.
- **API REST:** Exposición de servicios REST para integraciones externas.
- **Documentación Swagger:** Documentación automatizada y explorable mediante Swagger.

## 🔧 Tecnologías Utilizadas
- **Java 21**: Lenguaje principal del proyecto.
- **Spring Boot 3.4.0**: Framework para el desarrollo de aplicaciones.
- **Spring Data JPA y JDBC**: Manejo de la persistencia de datos.
- **MySQL**: Base de datos relacional.
- **Hibernate 6.4.4.Final**: ORM para la gestión de entidades.
- **Lombok**: Reducción de código repetitivo.
- **Swagger (SpringDoc OpenAPI)**: Documentación de la API.

## 🔢 Endpoints Disponibles

### Productos
- `GET /api/productos`: Obtener lista de productos.
- `GET /api/productos/{id}`: Consultar información de un producto por su ID.
- `POST /api/productos`: Crear un nuevo producto.
- `PUT /api/productos/{id}`: Actualizar información de un producto.
- `DELETE /api/productos/{id}`: Eliminar un producto.

### Ventas
- `GET /api/ventas`: Obtener lista de ventas.
- `POST /api/ventas`: Registrar una nueva venta.

## 📊 Configuración
### Requisitos
- **Java 21**
- **Maven 3.8 o superior**
- **MySQL Server**

### Instalación
1. Clona el repositorio:
   ```bash
   git clone <URL_REPOSITORIO>
   ```
2. Configura la base de datos en el archivo `application.properties`.
3. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```

## 🔗 Swagger
Accede a la documentación de la API desde:
```
http://localhost:8080/swagger-ui/index.html
```

## 🛠️ Datos de Contacto
- **Desarrollador:** [Fernando Zelaya]
- **Correo:** [fgzelaya89@gmail.com]
- **LinkedIn:** [https://www.linkedin.com/in/fgzelaya/]

## 🛡️ Licencia
Este proyecto está bajo la licencia MIT.

