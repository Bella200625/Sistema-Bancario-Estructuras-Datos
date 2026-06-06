# Sistema de Gestión Bancaria con Lista, Cola y Pila

Proyecto desarrollado en Java para la asignatura Estructuras de Datos.

## Descripción

Aplicación de consola que simula la gestión de turnos en una entidad bancaria utilizando estructuras de datos personalizadas:

- Lista: registro general de todos los turnos.
- Cola: gestión de clientes pendientes de atención (FIFO).
- Pila: historial de clientes atendidos (LIFO).

## Funcionalidades

- Registrar turnos.
- Visualizar todos los turnos registrados.
- Mostrar clientes pendientes.
- Procesar el siguiente turno.
- Consultar historial de atenciones.
- Buscar turnos por identificador.
- Cancelar turnos pendientes.
- Deshacer la última atención realizada.
- Consultar cantidades almacenadas en cada estructura.

## Estructuras Utilizadas

### Lista
Permite almacenar todos los turnos registrados en el sistema.

### Cola
Gestiona la fila de espera respetando el orden de llegada.

### Pila
Almacena el historial de turnos atendidos y permite deshacer la última operación.

---

# Actividad 3 - Sistema de Gestión Bancaria con Colecciones Java SDK

## Descripción

Evolución del sistema desarrollado en la actividad anterior, reemplazando las estructuras de datos personalizadas por las colecciones estándar del SDK de Java.

## Colecciones Utilizadas

### List
Registro general de todos los turnos creados.

### Queue
Administración de turnos pendientes siguiendo el principio FIFO.

### Deque
Historial de turnos procesados y soporte para deshacer la última atención.

### Map
Búsqueda rápida de turnos mediante el número de identificación.

### Stream
Procesamiento de información para búsquedas, filtros, ordenamientos, agrupamientos y estadísticas.

## Funcionalidades Adicionales

- Buscar turnos por número usando Map.
- Buscar clientes por nombre usando Stream.
- Filtrar turnos por estado.
- Ordenar turnos por nombre.
- Generar estadísticas del sistema.
- Agrupar turnos por tipo de trámite.
- Cancelar turnos pendientes.
- Deshacer la última atención realizada.
- Consultar cantidades almacenadas en las colecciones.

## Tecnologías

- Java
- Programación Orientada a Objetos
- Estructuras de Datos
- Git y GitHub

## Autor

Bella Samai Botello Meza

Universidad de Cartagena
Ingeniería de Software