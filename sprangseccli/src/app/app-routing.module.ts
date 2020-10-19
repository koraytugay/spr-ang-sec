import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FooComponent } from './foo/foo.component';
import { BarComponent } from './bar/bar.component';
import { LoginComponent } from './login/login.component';
import { AuthRouteGuardService } from './auth-route-guard.service';

const routes: Routes = [
  {path: 'foo', component: FooComponent, canActivate: [AuthRouteGuardService]},
  {path: 'bar', component: BarComponent, canActivate: [AuthRouteGuardService]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
