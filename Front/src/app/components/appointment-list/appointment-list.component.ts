import {Component, Input} from '@angular/core';
import {Turno} from '../../models/interfaces';


@Component({
  selector: 'app-appointment-list',
  imports: [],
  templateUrl: './appointment-list.component.html'
})
export class AppointmentListComponent{
  @Input() turnos: Turno[] =[];
}
