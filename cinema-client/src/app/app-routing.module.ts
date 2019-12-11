import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {UsercComponent} from './userc/userc.component';
import {AdmincComponent} from './adminc/adminc.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'userc',
    component: UsercComponent
  },
  {
    path: 'adminc',
    component: AdmincComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
