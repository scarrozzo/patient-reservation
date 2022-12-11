import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from "../config/auth.guard";
import {SigninComponent} from "../components/signin/signin";
import {VisitsComponent} from "../components/visits/visits";

const routes: Routes = [
  { path: '', redirectTo: '/log-in', pathMatch: 'full' },
  { path: 'log-in', component: SigninComponent },
  //{ path: 'sign-up', component: SignupComponent }
  //{ path: 'user-profile/:id', component: UserProfileComponent, canActivate: [AuthGuard] }
  { path: 'visits', component: VisitsComponent, canActivate: [AuthGuard] }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
