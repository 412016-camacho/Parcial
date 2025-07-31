import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Medico, MedicoDto, Paciente, PacienteDto, Turno, TurnoRequest} from '../models/interfaces';
import {map} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private url = "http://localhost:8080/api/v1";
  private http: HttpClient =inject(HttpClient);

  getPacientes(){
    return this.http.get<PacienteDto[]>(`${this.url}/pacientes`).pipe(
      map((res: PacienteDto[]) => res.map(p => ({
        id: p.id,
        nombreCompleto: p.nombre_completo,
        fechaNacimiento: p.fecha_nacimiento
      } as Paciente)))
    );
  }

  getMedicos(){
    return this.http.get<MedicoDto[]>(`${this.url}/medicos`).pipe(
      map((res: MedicoDto[]) => res.map(m => ({
        id: m.id,
        nombreCompleto: m.nombre_completo,
        especialidad: m.especialidad
      } as Medico)))
    );
  }

  postTurno(turno: TurnoRequest){
    return this.http.post<TurnoRequest>(`${this.url}/turnos`, turno);
  }

  getTurnos(medicoId?:number, pacienteId?:number, fecha?:string){
    let params = new HttpParams();
    if (medicoId) params = params.set("medicoId", medicoId);
    if (pacienteId) params = params.set("pacienteId", pacienteId);
    if (fecha) params = params.set("fecha", fecha);

    return this.http.get<Turno[]>(`${this.url}/turnos`, {params});
  }
}
