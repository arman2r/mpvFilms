# Documentación de APIs del Proyecto

## Información General
- **Versión de Java:** 21
- **Versión de Spring Boot:** 3.3.9
- **Base de Datos:** MySQL 8

---

## Pasos para Levantar el Backend con Docker

### Prerrequisitos
1. Tener instalado [Docker](https://www.docker.com/) y [Docker Compose](https://docs.docker.com/compose/).
2. Asegurarse de que los puertos necesarios (por ejemplo, `8080` para el backend y `3306` para MySQL) estén disponibles.

### Configuración
1. Crear un archivo `docker-compose.yml` en la raíz del proyecto si no existe. Este archivo debe contener la configuración para levantar el backend y la base de datos MySQL.
2. Asegúrate de que el archivo `application.properties` o `application.yml` esté configurado para conectarse a la base de datos MySQL del contenedor.

### Comandos para Levantar el Backend
1. Compila el proyecto con maven y asegurate de que se genere el .jar en la caperta target
      bash
   mvn clean package -DskipTests

2. Construir la imagen de Docker para el backend:
   ```bash
   docker-compose up --build
   ```
3. Levantar los servicios con Docker Compose:
   ```bash
   docker-compose up -d
   ```
   Esto levantará tanto el backend como la base de datos MySQL.

4. Verificar que los contenedores estén corriendo:
   ```bash
   docker ps
   ```

### Detener los Servicios
Para detener los servicios, utiliza el siguiente comando:
```bash
docker-compose down
```

---

## 1. Usuarios

### Registrar Usuario
- **URL:** `/api/users/register`
- **Método:** `POST`
- **Request Body:**
  ```json
  {
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "phoneNumber": "string",
    "password": "string",
    "available": 1,
    "rol": "USER" // o "ADMIN"
  }
  ```
- **Descripción:** Registra un nuevo usuario.

### Listar Usuarios con Rol USER
- **URL:** `/api/users/list-users`
- **Método:** `GET`
- **Descripción:** Devuelve una lista de usuarios con el rol `USER`.

### Actualizar Usuario
- **URL:** `/api/users/update/{id}`
- **Método:** `PUT`
- **Path Parameter:**
  - `id` (Long): ID del usuario a actualizar.
- **Request Body:**
  ```json
  {
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "phoneNumber": "string",
    "password": "string",
    "available": 1,
    "rol": "USER" // o "ADMIN"
  }
  ```
- **Descripción:** Actualiza los datos de un usuario.

### Eliminar Usuario
- **URL:** `/api/users/delete/{id}`
- **Método:** `DELETE`
- **Path Parameter:**
  - `id` (Long): ID del usuario a eliminar.
- **Descripción:** Elimina un usuario.

### Obtener Usuario por Email
- **URL:** `/api/users/getByEmail`
- **Método:** `GET`
- **Query Parameter:**
  - `email` (String): Email del usuario.
- **Descripción:** Devuelve los datos de un usuario por su email.

### Login
- **URL:** `/api/users/login`
- **Método:** `POST`
- **Request Body:**
  ```json
  {
    "email": "string",
    "password": "string"
  }
  ```
- **Descripción:** Autentica al usuario y devuelve un token JWT.

---

## 2. Películas

### Crear Película
- **URL:** `/api/film`
- **Método:** `POST`
- **Request Body:**
  ```json
  {
    "title": "string",
    "description": "string",
    "releaseDate": "dd/MM/yyyy",
    "price": 100,
    "stock": 10,
    "available": 1,
    "base64Image": "string"
  }
  ```
- **Descripción:** Crea una nueva película.

### Obtener Película por ID
- **URL:** `/api/film/{id}`
- **Método:** `GET`
- **Path Parameter:**
  - `id` (Integer): ID de la película.
- **Descripción:** Devuelve los datos de una película por su ID.

### Actualizar Película
- **URL:** `/api/film/{id}`
- **Método:** `PUT`
- **Path Parameter:**
  - `id` (Integer): ID de la película a actualizar.
- **Request Body:** Igual que en **Crear Película**.
- **Descripción:** Actualiza los datos de una película.

### Eliminar Película
- **URL:** `/api/film/{id}`
- **Método:** `DELETE`
- **Path Parameter:**
  - `id` (Integer): ID de la película a eliminar.
- **Descripción:** Elimina una película.

### Listar Todas las Películas
- **URL:** `/api/film`
- **Método:** `GET`
- **Descripción:** Devuelve una lista de todas las películas.

### Buscar Películas por Título
- **URL:** `/api/film/search`
- **Método:** `GET`
- **Query Parameter:**
  - `title` (String): Título o parte del título de la película.
- **Descripción:** Busca películas por título.

### Listar Películas Disponibles
- **URL:** `/api/film/available`
- **Método:** `GET`
- **Descripción:** Devuelve una lista de películas disponibles.

### Listar Películas por Precio
- **URL:** `/api/film/price`
- **Método:** `GET`
- **Query Parameter:**
  - `price` (Double): Precio máximo.
- **Descripción:** Devuelve una lista de películas cuyo precio es menor al especificado.

---

## 3. Compras

### Crear Compra
- **URL:** `/api/purchases/purchase/create`
- **Método:** `POST`
- **Request Body:**
  ```json
  {
    "userEmail": "string",
    "items": [
      {
        "filmId": 1,
        "quantity": 2,
        "ticketPrice": 100.0
      }
    ]
  }
  ```
- **Descripción:** Crea una nueva compra.

### Obtener Compras por Usuario
- **URL:** `/api/purchases/purchase/user/{email}`
- **Método:** `GET`
- **Path Parameter:**
  - `email` (String): Email del usuario.
- **Descripción:** Devuelve una lista de compras realizadas por un usuario.

### Confirmar Compra
- **URL:** `/api/purchases/purchase/confirm/{purchaseId}`
- **Método:** `PUT`
- **Path Parameter:**
  - `purchaseId` (Long): ID de la compra.
- **Query Parameter:**
  - `confirmationStatus` (Integer): Estado de confirmación (1 para confirmar).
- **Descripción:** Confirma una compra y envía un correo de confirmación.

### Actualizar Cantidad de Tickets
- **URL:** `/api/purchases/purchase/update-ticket-quantity/{purchaseId}/{filmId}`
- **Método:** `PUT`
- **Path Parameters:**
  - `purchaseId` (Long): ID de la compra.
  - `filmId` (Long): ID de la película.
- **Request Body:**
  ```json
  {
    "newQuantity": 5
  }
  ```
- **Descripción:** Actualiza la cantidad de tickets de una compra no confirmada.

### Eliminar Ítems de una Compra
- **URL:** `/api/purchases/purchase/remove-items-by-film/{purchaseId}`
- **Método:** `DELETE`
- **Path Parameter:**
  - `purchaseId` (Long): ID de la compra.
- **Request Body:**
  ```json
  {
    "filmIds": [1, 2, 3]
  }
  ```
- **Descripción:** Elimina ítems de una compra no confirmada.

### Obtener Ítems de una Compra
- **URL:** `/api/purchases/purchase/{purchaseId}/items`
- **Método:** `GET`
- **Path Parameter:**
  - `purchaseId` (Long): ID de la compra.
- **Descripción:** Devuelve los ítems de una compra.

---

## 4. Imágenes

### Subir Imagen
- **URL:** `/api/upload/file/upload`
- **Método:** `POST`
- **Request Body:** Form-data con el archivo de imagen.
- **Descripción:** Sube una imagen a AWS S3.

### Eliminar Imagen
- **URL:** `/api/upload/file/delete`
- **Método:** `DELETE`
- **Query Parameter:**
  - `url` (String): URL de la imagen a eliminar.
- **Descripción:** Elimina una imagen de AWS S3.