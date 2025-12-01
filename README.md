# 🏨 Spring Hotels – Technical Test

Este proyecto implementa una API REST para la gestión de hoteles siguiendo
principios de **DDD (Domain-Driven Design)** y **Arquitectura Hexagonal**.
El objetivo es mostrar un diseño limpio, desacoplado y fácilmente extensible,
con una separación clara entre dominio, aplicación y adaptadores.

---

## 📌 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database**
- **JUnit 5 + Mockito**
- **Jakarta Validation**

---

## 🧱 Arquitectura (Hexagonal + DDD)

La estructura del proyecto sigue tres capas principales:


### ✔ Dominio
Contiene toda la lógica empresarial:

- `Hotel` como **Aggregate Root**
- Value Objects: `HotelName`, `HotelAddress`
- Enum: `HotelStars`
- Reglas y validaciones encapsuladas

El dominio **NO depende de Spring ni de ninguna tecnología**.

---

### ✔ Aplicación
Define los **casos de uso**:

- Crear hotel  
- Obtener hotel  
- Buscar hoteles  
- Actualizar hotel  
- Eliminar hotel  

Y sus implementaciones:

- `CreateHotelService`
- `ReadHotelService`
- `UpdateHotelService`
- `DeleteHotelService`

Orquestan dominio + repositorio para ejecutar la lógica.

---

### ✔ Adaptadores
Implementan tecnologías externas:

- **REST Controller** → expone la API HTTP
- **JPA Adapter** → persiste datos
- **Mapper** → convierte Domain ↔ JPA

El dominio nunca conoce las tecnologías externas.

---

## 🔐 Seguridad

Implementada mediante **Spring Security** usando Basic Auth.

| Usuario | Contraseña | Roles |
|---------|------------|--------|
| `user`  | `password` | USER   |
| `admin` | `admin`    | ADMIN  |

### Permisos

| Rol   | Permisos                         |
|-------|----------------------------------|
| USER  | GET /hotels, /city, /id          |
| ADMIN | Todos + DELETE                   |

---

## 🧪 Tests

Incluye pruebas:

- De controller usando MockMvc  
- De servicios usando Mockito  
- De repositorio JPA mediante TestUtils  
- Casos positivos y negativos (404, forbidden)

Ejecutar tests:

```bash
mvn test
