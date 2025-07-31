import { Routes } from '@angular/router';
import {AppointmentFormComponent} from './components/appointment-form/appointment-form.component';
import {AppointmentHomeComponent} from './components/appointment-home/appointment-home.component';

export const routes: Routes = [
  {path: "", redirectTo: "turnos", pathMatch: "full"},
  {path: "turnos", component: AppointmentHomeComponent},
  {path: "alta-turno", component: AppointmentFormComponent},
  {path: "**", redirectTo: "turnos"},
];
