import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgPrimeModule} from './app.ngprime.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [],
  exports: [
    NgPrimeModule,
    FormsModule,
    ReactiveFormsModule
  ],
  imports: [
    CommonModule
  ]
})
export class AppCommonModule {
}
