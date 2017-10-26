import {ModuleWithProviders, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterHospitalComponent } from './hospital/register-hospital.component';
import {SearchDoctorComponent} from "./doctor/search-doctor.compoment";
import {HospitalPanelComponent} from "./hospital/hospital-panel.component";
import {LoginComponent} from "./login/login.component";
import {AddDoctorComponent} from "./doctor/add-doctor.component";
import {UpdateDoctorComponent} from "./doctor/update-doctor.component";
import {DoctorProfileComponent} from "./doctor/doctor-profile.component";
import {DoctorGridView} from "./doctor/doctor-grid-view";
import {UpdateHospitalComponent} from "./hospital/update-hospital.component";
import {AdminComponent} from "./admin/admin.component";
import {DoctorDetailsComponent} from "./doctor/doctor-details.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {BloodPostComponent} from "./blood/blood-post.component";
import {EventComponent} from "./event/event.component";

const ROUTES: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: SearchDoctorComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterHospitalComponent },
  { path: 'search-doctor', component: SearchDoctorComponent },
  { path: 'hospital-panel', component: HospitalPanelComponent },
  { path: 'admin', component: AdminComponent },
  {path:'register-doctor', component: AddDoctorComponent},
  {path:'update/:id', component: UpdateDoctorComponent},
  {path:'profile/:id', component: DoctorProfileComponent},
  {path:'blood-post', component: BloodPostComponent},
  {path:'events', component: EventComponent},
  {path:'doctor-view/:doctor', component: DoctorGridView},
  {path:'hospital-update/:username', component: UpdateHospitalComponent},
  {path:'doctor-detail/:id', component: DoctorDetailsComponent},
  {path:'not-found', component: PageNotFoundComponent},
  { path: '**', redirectTo: '/not-found' },
];

// @NgModule({
//   imports: [ RouterModule.forRoot(ROUTES) ],
//   exports: [ RouterModule ]
// })
//
// export class DocseaRoutingModule {
//
// }

export const routesModule: ModuleWithProviders = RouterModule.forRoot(ROUTES, {useHash: true});
