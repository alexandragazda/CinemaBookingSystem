import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {UserComponent} from './user/user.component';
import {AdminComponent} from './admin/admin.component';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {RegisterComponent} from './auth/register/register.component';
import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';
import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
    {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'my-account',
    component: UserComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'successful-registration',
    component: SuccessfulRegistrationComponent,
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


// import { NgModule } from '@angular/core';
// import { Routes, RouterModule } from '@angular/router';
// import {LoginComponent} from './auth/login/login.component';
// import {UserComponent} from './user/user.component';
// import {AdminComponent} from './admin/admin.component';
// import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
// import {RegisterComponent} from './auth/register/register.component';
// import {SuccessfulRegistrationComponent} from './auth/successful-registration/successful-registration.component';
// import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';
// import {HomeComponent} from './home/home.component';
// import {GeneralMenuComponent} from './general-menu/general-menu.component';
// import {CommonModule} from '@angular/common';
//
// const routes: Routes = [
//   {
//     path: 'home',
//     component: HomeComponent
//   },
//   // {
//   //   path: '',
//   //   redirectTo: '/home',
//   //   pathMatch: 'full'
//   // },
//   // {
//   //   path: '**',
//   //   redirectTo: '/home',
//   //   pathMatch: 'full'
//   // },
//   // {
//   //   path: 'login',
//   //   loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
//   //   component: LoginComponent
//   // },
//   {
//     path: 'login',
//     loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
//     component: GeneralMenuComponent
//   },
//   {
//     path: 'login',
//     loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
//   },
//   // {
//   //   path: 'login',
//   //   component: LoginComponent
//   // },
//   // {
//   //   path: 'user',
//   //   component: UserComponent
//   // },
//   // {
//   //   path: 'admin',
//   //   component: AdminComponent
//   // },
//   // {
//   //   path: 'forgot-password',
//   //   component: ForgotPasswordComponent
//   // },
//   // {
//   //   path: 'register',
//   //   component: RegisterComponent
//   // },
//   // {
//   //   path: 'successful-registration',
//   //   component: SuccessfulRegistrationComponent,
//   // },
//   // {
//   //   path: 'reset-password',
//   //   component: ResetPasswordComponent,
//   // },
// ];
//
// @NgModule({
//   imports: [
//     CommonModule,
//     RouterModule.forRoot(routes)
//   ],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }
