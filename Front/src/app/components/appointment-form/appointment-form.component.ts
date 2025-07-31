import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import {Medico, Paciente, TurnoRequest} from '../../models/interfaces';
import {ApiService} from '../../services/api.service';
import * as console from 'node:console';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-appointment-form',
  imports: [
    FormsModule
  ],
  templateUrl: './appointment-form.component.html'
})
export class AppointmentFormComponent implements OnInit, OnDestroy {
  turno: TurnoRequest = {
    paciente_id: 0,
    medico_id: 0,
    fecha: ''
  }
  pacientes: Paciente[] = [];
  medicos: Medico[] = [];
  private apiService = inject(ApiService);
  private subscription: Subscription = new Subscription();


  ngOnInit(): void {
    this.getPacientes();
    this.getMedicos();
  }

  getPacientes(): void {
    this.subscription.add(this.apiService.getPacientes().subscribe({
      next: data => {
        this.pacientes = data;
      },
      error: err => {
        console.log(err);
      }
    }))
  }

  getMedicos(): void {
    this.subscription.add(this.apiService.getMedicos().subscribe({
      next: data => {
        this.medicos = data;
      },
      error: err => {
        console.log(err);
      }
    }))
  }


  saveTurno(turno: NgForm) {
    if(turno.invalid){
      alert("Faltan algunos campos");
      return;
    }
    this.subscription.add(this.apiService.postTurno(this.turno).subscribe({
      next: data => {
        console.log("Turno creado: ",data);
        turno.resetForm();
      },
      error: err => {
        console.log(err);
      }
    }))
  }

  cancelar() {
    this.turno = {
      paciente_id: 0,
      medico_id: 0,
      fecha: ''
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
