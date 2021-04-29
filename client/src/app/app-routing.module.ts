import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from './shared/layout/layout.component';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: () => import('src/app/pages/login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'main',
    component: LayoutComponent,
    children: [{
      path: 'dashboard',
      loadChildren: () => import('src/app/pages/dashboard/dashboard.module').then(m => m.DashboardModule),
//      canActivate: [AuthGuard]
    }]
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
