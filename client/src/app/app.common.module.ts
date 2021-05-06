import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgBulmaModule} from './app.ngbulma.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [],
  exports: [
    NgBulmaModule,
    FormsModule,
    ReactiveFormsModule
  ],
  imports: [
    CommonModule
  ]
})
export class AppCommonModule {
}
