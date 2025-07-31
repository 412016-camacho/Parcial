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