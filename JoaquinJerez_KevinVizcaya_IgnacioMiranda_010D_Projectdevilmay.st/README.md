# devilmay.st 👕🛍️

**devilmay.st** es una plataforma de e-commerce moderna, escalable y dinámica diseñada para la gestión integral de ventas de indumentaria. Su arquitectura está basada en un ecosistema robusto de **6 microservicios independientes**, orquestados eficientemente y enrutados de forma centralizada por un API Gateway, garantizando alta disponibilidad y bajo acoplamiento entre sus componentes de negocio.

## 👥 Integrantes
- Joaquin Jerez
- Kevin Vizcaya
- Ignacio Miranda

## 🚀 Funcionalidades Principales y Microservicios

- **API Gateway (Puerto 8080)**: Actúa como el punto de entrada único de la plataforma. Recibe todas las peticiones de los clientes y se encarga del enrutamiento dinámico hacia los diferentes microservicios internos, ocultando la complejidad del ecosistema.
- **Módulo de Catálogo (8081)**: Gestiona la vitrina digital de prendas. Permite listar, filtrar y visualizar los productos disponibles, sus detalles, géneros y tipos de prendas para que los clientes interactúen.
- **Módulo de Inventario (8082)**: Controla el stock físico de las prendas. Es responsable de la actualización en tiempo real de las cantidades disponibles cada vez que se reserva o se compra un producto, previniendo sobreventas.
- **Módulo de Carrito (8083)**: Maneja el almacenamiento temporal de las sesiones de compra de los usuarios, permitiendo agregar, modificar y eliminar prendas antes del checkout o generación de reserva.
- **Módulo de Reservas (8084)**: Coordina la generación de órdenes y citas de compra. Transforma el contenido del carrito en una reserva en firme, descontando el stock a través de comunicación síncrona con el Inventario.
- **Módulo de Logística (8085)**: Administra la preparación y el despacho de las reservas confirmadas, trazando el viaje del producto desde la bodega hasta el cliente final.

## 🛠️ Stack Tecnológico
- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3.x / Spring Cloud Gateway
- **Base de Datos**: MySQL (XAMPP)
- **Dependencias Clave**: Spring WebFlux (WebClient), Lombok, Spring Data JPA, Spring Boot Validation.
- **Aplicaciones Requeridas**: Postman (para pruebas), XAMPP (servidor MySQL), IDE (IntelliJ/Eclipse/VSCode), Maven.

## 💻 Pasos para Ejecutar

1. **Preparar Base de Datos (XAMPP)**: 
   Abre el panel de control de XAMPP e inicia los servicios de Apache y MySQL. No necesitas crear las bases de datos manualmente, ya que Hibernate (`createDatabaseIfNotExist=true`) creará automáticamente `db_catalogo`, `db_inventario`, `db_carrito`, `db_reservas` y `db_logistica`.

2. **Clonar y Abrir el Repositorio**:
   ```bash
   git clone <url-del-repositorio>
   cd DevilMay.stV5/JoaquinJerez_KevinVizcaya_IgnacioMiranda_010D_Projectdevilmay.st
   ```

3. **Levantar los Microservicios**:
   Abre una terminal por cada microservicio y utiliza Maven para compilar y ejecutar en el siguiente orden (idealmente iniciando los servicios base primero y al final el Gateway):
   ```bash
   # Terminal 1: Catálogo
   cd service-catalogo/service_catalogo && mvn spring-boot:run
   
   # Terminal 2: Inventario
   cd service-inventario/service-inventario && mvn spring-boot:run
   
   # Terminal 3: Carrito
   cd service-carrito/service-carrito && mvn spring-boot:run
   
   # Terminal 4: Reservas
   cd service-reservas/service-reservas && mvn spring-boot:run
   
   # Terminal 5: Logística
   cd service-logistica/service-logistica && mvn spring-boot:run
   
   # Terminal 6: API Gateway
   cd api-gateway && mvn spring-boot:run
   ```
   *Nota: Todos los servicios estarán accesibles unificadamente a través de `http://localhost:8080/api/v1/...` (o `v2` en el caso de logística).*

## 💡 Nota sobre Arquitectura

- **Puentes Síncronos**: La comunicación entre microservicios se realiza de forma síncrona mediante `WebClient` (del stack WebFlux), garantizando la consistencia inmediata de los datos críticos (como actualización de despachos y catálogos) y un rendimiento no bloqueante.
- **Desacoplamiento de IDs**: Para evitar un alto acoplamiento de red y cruce indebido de modelos, los microservicios se referencian entre sí enviando y recibiendo únicamente identificadores (`IDs`) de entidades en lugar de objetos completos.
- **Global Exception Handler y DTOs**: El sistema estandariza respuestas de error uniformes (como 400 Bad Request o 409 Conflict) utilizando `@RestControllerAdvice`, y aplica patrones DTO (`CarritoDTO`, `DespachoDTO`) para separar la capa de presentación de la persistencia de datos.

## 🧪 Datos de Prueba (Postman)

Para probar los microservicios a través del API Gateway (`localhost:8080`), puedes usar estos ejemplos de cuerpo JSON:

**1. Crear un Carrito (POST `/api/v1/carritos`)**
```json
{
  "idProducto": 105,
  "cantidad": 2
}
```

**2. Crear un Despacho Logístico (POST `/api/v2/logistica/despacho`)**
```json
{
  "nro_Orden": 1001,
  "idEntrega": 500,
  "estadoDespacho": {
    "nombre_Estado": "EN_PREPARACION"
  }
}
```
**3. Probar Exclusividad de Stock (POST `/api/v1/carritos`) - BAD PATH**
Para probar la validación DTO y el Global Exception Handler, intenta pedir más de una unidad de un artículo exclusivo. El sistema detendrá la transacción y devolverá un `400 Bad Request`.
```json
{
  "idProducto": 105,
  "cantidad": 2
}
