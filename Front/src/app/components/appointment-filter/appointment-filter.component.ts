import {Component, EventEmitter, inject, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Medico, Paciente, TurnoRequest} from '../../models/interfaces';
import {ApiService} from '../../services/api.service';
import {FormsModule} from '@angular/forms';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-appointment-filter',
  imports: [
    FormsModule
  ],
  templateUrl: './appointment-filter.component.html'
})
export class AppointmentFilterComponent implements OnInit, OnDestroy {
  @Output() filtrado = new EventEmitter<TurnoRequest>();

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

  onSubmit() {
    this.filtrado.emit(this.turno);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
