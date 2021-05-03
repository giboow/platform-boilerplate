import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AppCommonModule} from '../../app.common.module';
import {DashboardRoutingModule} from './dashboard-routing.module';
import {DashboardComponent} from './dashboard.component';


@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    AppCommonModule,
    CommonModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule {
}
