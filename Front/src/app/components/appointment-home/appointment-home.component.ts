import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {AppointmentListComponent} from '../appointment-list/appointment-list.component';
import {Turno, TurnoRequest} from '../../models/interfaces';
import {ApiService} from '../../services/api.service';
import {AppointmentFilterComponent} from '../appointment-filter/appointment-filter.component';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-appointment-home',
  imports: [
    AppointmentListComponent,
    AppointmentFilterComponent
  ],
  templateUrl: './appointment-home.component.html',
  styleUrl: './appointment-home.component.css'
})
export class AppointmentHomeComponent implements OnInit, OnDestroy {
 listaTurnos: Turno[] = [];

 private apiService = inject(ApiService);
 private subscription: Subscription = new Subscription();

  ngOnInit(): void {
    this.subscription.add(this.apiService.getTurnos().subscribe({
      next: data => {
        this.listaTurnos = data;
        console.log(data);
      },
      error: err => {
        console.log(err);
      }
    }))
  }

  filtered(evento: TurnoRequest) {
    this.subscription.add(this.apiService.getTurnos(
      evento.medico_id, evento.paciente_id, evento.fecha)
      .subscribe(res =>{
        this.listaTurnos = res;
        console.log(res);
      }));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
