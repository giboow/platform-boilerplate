import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {PanelModule} from 'primeng/panel';
import {ToastModule} from 'primeng/toast';
import {MegaMenuModule} from 'primeng/megamenu';
import {TableModule} from 'primeng/table';
import {MessageModule} from 'primeng/message';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {DynamicDialogModule} from 'primeng/dynamicdialog';
import {SidebarModule} from 'primeng/sidebar';
import {CalendarModule} from 'primeng/calendar';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {ChartModule} from 'primeng/chart';
import {CardModule} from 'primeng/card';


@NgModule({
  exports: [
    InputTextModule,
    ButtonModule,
    PanelModule,
    ToastModule,
    MegaMenuModule,
    TableModule,
    MessageModule,
    CardModule,
    ChartModule,
    ProgressSpinnerModule,
    OverlayPanelModule,
    BreadcrumbModule,
    CalendarModule,
    SidebarModule,
    DynamicDialogModule,
    InputTextareaModule,
  ]
})
export class NgPrimeModule {
}
