
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
