import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import {AppCommonModule} from '../../app.common.module';
import {LoginRoutingModule} from './login-routing.module';



@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    AppCommonModule,
    CommonModule,
    LoginRoutingModule
  ]
})
export class LoginModule { }
