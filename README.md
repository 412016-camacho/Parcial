[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/g_HiRGk-)
# 🧩 ENUNCIADO GENERAL

## Sistema de Gestión de Turnos para una Clínica Médica

Se debe desarrollar un **backend en Java con Spring Boot** para gestionar los _turnos entre pacientes y médicos_. El
sistema permitirá:

- Registrar pacientes y médicos.
- Visualizar turnos registrados, con posibilidad de aplicar filtros.
- Agendar un nuevo turno asociando un paciente con un médico, en una fecha y hora específica.

---

# 🚀 BACKEND – Java Spring Boot

## Requisitos Generales

- Utilizar Spring Boot 3+.
- Seguir una arquitectura por capas: `Entity`, `DTO`, `Repository`, `Service`, `Controller`.
- Usar Spring Data JPA.
- Base de datos: H2 en memoria o MySQL (a elección).
- Validación con anotaciones de Jakarta Bean Validation (`@NotNull`, `@NotBlank`, etc.).
- Manejo correcto de errores: debe devolver respuestas claras y con los códigos HTTP adecuados.
- ⚠️ **Muy importante**: se debe **respetar estrictamente el contrato definido** a continuación, tanto en el _formato de
  las requests y responses_, como en las _URLs de los endpoints_. Cualquier desviación puede invalidar la integración
  del sistema.
- **opcional:** Usar `ModelMapper` para convertir entre entidades y DTOs.

---

## 📄 Entidades

### Paciente

| Campo            | Tipo   | Obligatorio |
| ---------------- | ------ | ----------- |
| id               | Long   | No          |
| nombre_completo  | String | Sí          |
| fecha_nacimiento | String | No          |

**Validaciones**:

- `nombreCompleto`: no debe ser nulo ni estar en blanco.

---

### Medico

| Campo           | Tipo   | Obligatorio |
| --------------- | ------ | ----------- |
| id              | Long   | No          |
| nombre_completo | String | Sí          |
| especialidad    | String | Sí          |

**Validaciones**:

- `nombreCompleto`: no debe ser nulo ni estar en blanco.
- `especialidad`: no debe ser nulo ni estar en blanco.

---

### Turno

| Campo         | Tipo      | Obligatorio |
| ------------- | --------- | ----------- |
| id            | Long      | No          |
| paciente      | Paciente  | Sí          |
| medico        | Medico    | Sí          |
| fechaTurno    | LocalDate | Sí          |
| observaciones | String    | No          |

**Validaciones**:

- `paciente` y `medico` deben estar correctamente registrados (existir en la base).
- `fechaTurno` debe ser una fecha y hora válida y no nula.
- `observaciones` es opcional.

---

### data.sql

```
INSERT INTO medicos (id, nombre_completo, especialidad)
VALUES (1, 'Juan Pérez', 'Cardiología'),
       (2, 'María Gómez', 'Pediatría'),
       (3, 'Carlos López', 'Traumatología'),
       (4, 'Ana García', 'Neurología');

INSERT INTO pacientes (id, nombre_completo, fecha_nacimiento)
VALUES (1, 'Pedro González', '1990-01-01'),
       (2, 'María Fernández', '1985-02-02'),
       (3, 'Luis Martínez', '1970-03-03'),
       (4, 'Sofía Rodríguez', '1995-04-04');

INSERT INTO turnos (id, fecha_turno, observaciones, medico_id, paciente_id)
VALUES (100, '2023-01-01', 'Consulta general', 1, 1),
       (101, '2023-01-02', 'Control pediátrico', 2, 2),
       (102, '2023-01-03', 'Dolor en rodilla', 3, 3),
       (103, '2023-01-04', 'Chequeo neurológico', 4, 4);
```

---

# 📡 ENDPOINTS A IMPLEMENTAR

Este documento especifica los endpoints REST que deben desarrollarse para el sistema de gestión de turnos de una clínica
médica. Es fundamental respetar:

- Las **rutas exactas**.
- El **formato de las requests y responses**.
- El **manejo de errores adecuado**.
- El uso correcto de los **tipos de datos y validaciones**.

---

## 📘 Pacientes

### `GET /api/v1/pacientes`

- Devuelve todos los pacientes registrados.
- **Response**: lista de objetos con los campos `id`, `nombre_completo`, y `fecha_nacimiento`.

#### Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "nombre_completo": "Juan Perez",
    "fecha_nacimiento": "22-08-1996"
  },
  {
    "id": 2,
    "nombre_completo": "Ana Garcia",
    "fecha_nacimiento": "05-08-1993"
  }
]
```

---

## 🩺 MÉDICOS

### `GET /api/v1/medicos`

- Devuelve una lista de médicos.
- Cada médico incluye: `id`, `nombre` y `especialidad`.

#### Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "nombre_completo": "Amalia López",
    "especialidad": "Pediatría"
  },
  {
    "id": 2,
    "nombre_completo": "Enzo Fernández",
    "especialidad": "Dermatología"
  }
]
```

---

## 📆 Listar TURNOS

### `GET /api/v1/turnos`

- **Parámetros opcionales (query params):**
  - `medico_id` (Long): filtra los turnos por ID del médico.
  - `paciente_id` (Long): filtra los turnos por ID del paciente.
  - `fecha` (String, formato `yyyy-MM-dd`): filtra los turnos por fecha (solo día, sin hora).

---

#### Ejemplo de respuesta

```json
[
  {
    "id": 10,
    "paciente": {
      "id": 2,
      "nombre_completo": "Ana Garcia",
      "fecha_nacimiento": "05-08-1993"
    },
    "medico": {
      "id": 2,
      "nombre_completo": "Enzo Fernández",
      "especialidad": "Dermatología"
    },
    "fecha": "2025-06-20"
  },
  {
    "id": 11,
    "paciente": {
      "id": 1,
      "nombre_completo": "Juan Perez",
      "fecha_nacimiento": "22-08-1996"
    },
    "medico": {
      "id": 1,
      "nombre_completo": "Amalia López",
      "especialidad": "Pediatría"
    },
    "fecha": "2025-06-20"
  }
]
```

---

## ➕ Creación TURNO

### `POST /api/v1/turnos`

### 🔸 Request Body – JSON esperado

```json
{
  "paciente_id": 1,
  "medico_id": 2,
  "fecha": "2025-06-20"
}
```

---

## ❗ MANEJO DE ERRORES

Es **obligatorio** implementar un manejo adecuado y estructurado de los errores en el backend. Todos los errores deben
estar claramente definidos, y las respuestas deben:

- Ser en formato **JSON**.
- Incluir un **código de estado HTTP apropiado**.
- Proporcionar un **mensaje claro y específico**.

---

### 🔹 Requisitos generales

- No devolver errores sin procesar ni stacktraces al usuario.
- Toda respuesta de error debe ser en **formato JSON**.

---

### 🔸 Códigos HTTP esperados

| Código | Situación                                                           |
| ------ | ------------------------------------------------------------------- |
| 400    | Datos inválidos en la request (campos faltantes, mal formato, etc.) |
| 404    | Recurso no encontrado (ej: paciente o médico inexistente)           |
| 500    | Error interno del servidor (excepción no controlada)                |

---

### 🔸 Ejemplo de error 400 – Validación fallida

```json
{
  "status": 500,
  "message": "Ocurrió un error inesperado al procesar la solicitud"
}
```

---

## 🧪 TESTING

Se requiere incluir **tests automatizados** para verificar el correcto funcionamiento de los endpoints desarrollados.

---

### 🔹 Alcance mínimo obligatorio

- **Tests unitarios** para:
  - **Servicios** (`@Service`)
  - **Controllers** (`@RestControllers`)

---

## ✅ CRITERIOS DE EVALUACIÓN POR ENDPOINT

El sistema será evaluado en función del correcto funcionamiento y estructura de los endpoints solicitados. Cada uno debe
respetar el contrato de API especificado (rutas, métodos, formato JSON y manejo de errores).

| Endpoint                   | Puntos |
|----------------------------| ------ |
| `GET /api/v1/pacientes`    | 10     |
| `GET /api/v1/medicos`      | 10     |
| `POST /api/v1/turnos`      | 20     |
| `GET /api/v1/turnos`       | 30     |
| Testing                    | 20     |
| Manejo adecuado de errores | 10     |

**Total: 100 puntos**

---

### 📌 Notas adicionales

- Se evaluará el cumplimiento exacto de:
  - La **ruta** del endpoint.
  - El **método HTTP** utilizado.
  - El **formato del JSON** de entrada y salida.
  - Las **validaciones** requeridas.
  - El **manejo correcto de errores** con códigos HTTP y mensajes claros.
- Se penalizará si se modifica el contrato de la API (por ejemplo, cambiar nombres de campos, rutas o
  estructuras de datos).
- La claridad del código y la separación en capas también influirá en la nota final, aunque no se asigna puntaje
  específico.

---

# 🖥️ FRONTEND

Desarrollar el **frontend en Angular 19**, utilizando **Tailwind CSS** para los estilos, que consuma la API REST provista por el backend para gestionar turnos médicos.  
La aplicación debe permitir registrar turnos, ver el listado de turnos y filtrarlos de manera eficiente.

---

## 📌 REQUISITOS

- Utilizar **Angular 19** y **Tailwind CSS**.
- Routing para navegar entre vistas principales.
- Consumo de APIs mediante **servicios Angular con Observables**.
- **Formularios template-driven** para:
  - Alta de turnos
  - Filtros de turnos
- **Combos de pacientes y médicos** cargados dinámicamente mediante los endpoints indicados.
- El filtrado de turnos debe implementarse usando **comunicación entre componentes**
- Todo el estilado debe ser realizado con **Tailwind CSS**.

---

## 📡 ENDPOINTS QUE DEBEN UTILIZARSE

- `GET /api/v1/pacientes`  
  Poblar combos de pacientes en formularios y filtros.
- `GET /api/v1/medicos`  
  Poblar combos de médicos en formularios y filtros.
- `POST /api/v1/turnos`  
  Guardar/agendar un nuevo turno.
- `GET /api/v1/turnos`  
  Obtener el listado de turnos y aplicar filtros según los parámetros necesarios.

---

## 📝 FUNCIONALIDADES REQUERIDAS

1. **Formulario de Alta de Turnos**

   - Template-driven form para agendar un turno (combo dinámico de pacientes/médicos y fecha).
   - Guarda el turno por POST a `/api/v1/turnos`.

2. **Formulado de Filtro de Turnos (componente independiente)**

   - Template-driven form para criteria de filtrado (paciente, médico, fecha).
   - Al aplicar el filtro, debe emitir los criterios al componente padre usando `@Output`.

3. **Listado de Turnos (componente independiente)**

   - Recibe los criterios de filtro a través de `@Input`.
   - Consulta `/api/v1/turnos` con esos filtros y muestra los resultados en una grilla.

4. **Routing**
   - La aplicación debe contar con dos rutas principales, gestionadas mediante Angular Routing, que reflejen las funcionalidades clave del sistema.

## 🌐 RUTAS REQUERIDAS

### `/turnos`

- Página principal que muestra el **listado de turnos en una tabla**.
- Incluye un **formulario de filtrado** (componente independiente) que permite buscar por paciente, médico o fecha.
- Los filtros se comunican con la grilla utilizando `@Output` y `@Input`.
- Por defecto, debe abrir esta página.

### `/alta-turno`

- Página que contiene el **formulario para crear un nuevo turno**.
- Utiliza **template-driven forms**.
- Incluye combos de pacientes y médicos, fecha y permite guardar el turno mediante un `POST`.

## ✅ CRITERIOS DE EVALUACIÓN FRONTEND

El frontend será evaluado en función del correcto funcionamiento y estructura de los componentes solicitados. Cada uno debe respetar los requerimientos especificados.

| Componente                          | Puntos Total | Funcionalidad                                   | Puntos Indiv. |
| ----------------------------------- | ------------ | ----------------------------------------------- | ------------- |
| ⭐ **FORMULARIO DE ALTA DE TURNOS** | **30**       | Template-driven form implementado correctamente | 10            |
|                                     |              | Combos dinámicos funcionando                    | 10            |
|                                     |              | POST exitoso y manejo de errores                | 10            |
| ⚙️ **FILTRADO DE TURNOS**           | **35**       | Componente de filtro independiente              | 10            |
|                                     |              | Comunicación @Input/@Output implementada        | 15            |
|                                     |              | Filtros funcionando correctamente               | 10            |
| 📋 **LISTADO DE TURNOS**            | **25**       | Grilla con datos correctos                      | 10            |
|                                     |              | Actualización dinámica con filtros              | 15            |
| 🔧 **ASPECTOS TÉCNICOS**            | **10**       | Routing implementado correctamente              | 5             |
|                                     |              | Servicios con Observables                       | 5             |

**Total: 100 puntos**

### 📌 Notas adicionales Frontend

- Se evaluará el cumplimiento exacto de:
  - La **estructura de componentes** solicitada
  - La **comunicación entre componentes** usando @Input/@Output
  - El **uso de formularios template-driven**
  - La **integración correcta con la API**
  - La **implementación de estilos**
- Se penalizará:
  - Uso de servicios globales para comunicación entre componentes
  - Implementación incorrecta de formularios template-driven
  - Falta de manejo de errores en llamadas a la API

---
