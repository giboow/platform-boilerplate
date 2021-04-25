import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserlistComponent} from "./pages/admin/userlist/userlist.component";
import {LoginComponent} from "./pages/login/login.component";

const routes: Routes = [
  {path: "login", component: LoginComponent},
  {

    path: "admin", children: [
      {path: "userlist", component: UserlistComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
