export interface Turno {
  id: number,
  paciente: Paciente,
  medico: Medico,
  fecha: string
}

export interface TurnoRequest {
  paciente_id: number,
  medico_id: number,
  fecha: string
}

export interface Paciente {
  id: number,
  nombreCompleto: string,
  fechaNacimiento: string
}

export interface PacienteDto {
  id: number,
  nombre_completo: string,
  fecha_nacimiento: string
}

export interface Medico {
  id: number,
  nombreCompleto: string,
  especialidad: string
}

export interface MedicoDto {
  id: number,
  nombre_completo: string,
  especialidad: string
}
