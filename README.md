# Gestión de Ventas y Control de Stock

Este proyecto fue desarrollado como parte del curso de **Coder House** y está destinado a gestionar las ventas y controlar el stock de productos de manera eficiente. La aplicación permite realizar operaciones relacionadas con clientes, productos, pedidos, y la gestión del stock asociado.

## Funcionalidades Principales

1. **Gestión de Clientes**:
    - Crear nuevos clientes.
    - Consultar clientes por DNI.
    - Listar todos los clientes.

2. **Gestión de Productos**:
    - Crear, actualizar, consultar y eliminar productos.
    - Control del stock de productos.

3. **Gestión de Pedidos**:
    - Crear pedidos asociados a un cliente.
    - Detallar los productos seleccionados dentro del pedido.
    - Descontar automáticamente el stock de los productos al registrar un pedido.

4. **Validaciones y Manejo de Errores**:
    - Validación de datos obligatorios en todas las entidades.
    - Manejo de excepciones como:
        - Cliente ya registrado.
        - Producto con stock insuficiente.
        - Entidades no encontradas.

---

## Tecnologías Utilizadas

### Backend
- **Java 21**
- **Spring Boot**: Framework principal para la creación del backend.
- **Spring Data JPA**: Gestión de la persistencia con Hibernate.
- **Jakarta Persistence API (JPA)**: Para el manejo de entidades y transacciones.
- **Lombok**: Reducción de código boilerplate como getters, setters y constructores.
- **MySQL**: Base de datos relacional para almacenar la información.
- **Hibernate**: ORM para la gestión de las entidades.

### Dependencias Principales
- **Spring Boot Starter Web**: Para construir servicios RESTful.
- **Spring Boot Starter Data JPA**: Gestión de las transacciones y consultas a la base de datos.
- **MySQL Connector**: Driver JDBC para conectar con MySQL.
- **Lombok**: Simplificación del código repetitivo.

---

## Estructura del Proyecto

```plaintext
src/main/java/com/coderhouse
├── controller          # Controladores REST para gestionar las solicitudes HTTP
│   ├── ClienteController.java
│   ├── ProductoController.java
│   ├── PedidoController.java
├── dao                 # Gestión de la persistencia con EntityManager
│   └── DaoFactory.java
├── exception           # Manejo centralizado de excepciones personalizadas
│   ├── GlobalExceptionHandler.java
│   ├── ClienteNotFoundException.java
│   ├── StockInsuficienteException.java
│   ├── DatosInvalidosException.java
├── models              # Entidades que representan las tablas de la base de datos
│   ├── Cliente.java
│   ├── Producto.java
│   ├── Pedido.java
│   ├── DetallePedido.java
├── service             # Lógica de negocio
│   ├── ClienteService.java
│   ├── ProductoService.java
│   ├── PedidoService.java
├── ProyectoFinalApplication.java # Clase principal del proyecto
