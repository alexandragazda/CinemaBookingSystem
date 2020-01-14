import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminComponent} from '../admin/admin.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AdminService} from './admin-service';

@NgModule({
  declarations: [
    AdminComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  providers: [
    [AdminService]]
})
export class AuthModule { }
